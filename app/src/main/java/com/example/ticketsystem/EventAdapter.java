package com.example.ticketsystem;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ticketsystem.Events.Events;
import com.example.ticketsystem.Users.AppDatabase;
import com.example.ticketsystem.Users.Model;
import com.example.ticketsystem.Users.UserDao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import papaya.in.sendmail.SendMail;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.WordViewHolder>{

    private final LayoutInflater mInflater;
    private List<Events> Data;

    public EventAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.vetdatashow_recycler_row, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (Data != null) {
            Events current = Data.get(position);
            holder.name.setText(current.getTeam1());
            holder.dept.setText(current.getTeam2());
            holder.time.setText(current.getTime_venue());

            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(holder.card.getContext(), "yess", Toast.LENGTH_SHORT).show();
                    Dialog d;
                    d=new Dialog(holder.card.getContext());
                    TextView tex;
                    d.setContentView(R.layout.confirm);
                    tex=d.findViewById(R.id.mes);
                    tex.setText("Enter Your NID Number For Book");

                    Button ok=d.findViewById(R.id.add);
                    d.show();

                    ok.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                            EditText tittle=d.findViewById(R.id.tittle);
                            EditText mail=d.findViewById(R.id.email);
                            String nhi=tittle.getText().toString();
                            AppDatabase db= Room.databaseBuilder(holder.card.getContext(),AppDatabase.class,"tbuser_dd").allowMainThreadQueries().build();
                            UserDao userDao=db.userDao();
                            if (!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) {
                                mail.setError("Invalid Mail");
                                mail.requestFocus();
                                return;
                            }
                            if(userDao.exist(nhi))
                            {

                                Model model=userDao.singleitem(nhi);
                               // Toast.makeText(holder.card.getContext(), "Booking Successfull", Toast.LENGTH_SHORT).show();

                                //Creating a notification channel
                                NotificationChannel channel= null;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    channel = new NotificationChannel("channel1",
                                            "Booking Successfull",
                                            NotificationManager.IMPORTANCE_HIGH);
                                }
                                NotificationManager manager=(NotificationManager) holder.card.getContext().getSystemService(NOTIFICATION_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    manager.createNotificationChannel(channel);
                                }

                                //Creating the notification object
                                NotificationCompat.Builder notification=new NotificationCompat.Builder(holder.card.getContext(),"channel1");
                                //notification.setAutoCancel(true);
                                notification.setContentTitle("Successful");
                                notification.setContentText("Booking Successful");
                                notification.setSmallIcon(R.drawable.ic_launcher_background);

                                //make the notification manager to issue a notification on the notification's channel
                                manager.notify(121,notification.build());

                                ViewModel viewModel;

                                viewModel=new ViewModelProvider((ViewModelStoreOwner) holder.card.getContext()).get(ViewModel.class);
                                try{
                                    SendMail mai = new SendMail("chulchul831@gmail.com", "chulchul",
                                            mail.getText().toString(),
                                            "Booking",
                                            "Your Ticket is confirm\n  "+Data.get(position).toString());
                                    mai.execute();}catch (Exception e){

                                    Toast.makeText(holder.card.getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                                viewModel.delete(Data.get(position));





                                ArrayList<String>
                                        list=model.v;

                                list.add(Data.get(position).toString());

                                list=removeDuplicates(list);
                                model.v=list;
                                userDao.update(model);






                                delete(position);
                                d.dismiss();
                                return;
                            }
                            else
                            {
                                Toast.makeText(holder.card.getContext(), "Please Complete Registration\nIf you are not Registered", Toast.LENGTH_SHORT).show();
                                tittle.setError("Invalid NHI ");
                                tittle.requestFocus();
                            }
                        }
                    });

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (Data != null)
            return Data.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,dept,time;
        CardView card;

        private WordViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dept= itemView.findViewById(R.id.department);
            time=itemView.findViewById(R.id.time);
            card=itemView.findViewById(R.id.root);


        }
    }

    void setWords(List<Events> words){
        Data = words;
        notifyDataSetChanged();
    }

    void delete(int position)
    {
        Data.remove(position);
        notifyDataSetChanged();
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        // return the list
        return list;
    }

    void Note(){

    }



}
