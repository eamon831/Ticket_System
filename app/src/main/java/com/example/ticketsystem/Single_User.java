package com.example.ticketsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketsystem.Users.AppDatabase;
import com.example.ticketsystem.Users.Model;
import com.example.ticketsystem.Users.UserDao;

import java.util.ArrayList;

public class Single_User extends AppCompatActivity {
    EditText t;
    Button b;
    String nhi;
    ArrayList<String> list;
    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        t=findViewById(R.id.nhi);
        b=findViewById(R.id.sub);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"tbuser_dd").allowMainThreadQueries().build();
                UserDao userDao=db.userDao();
                nhi=t.getText().toString();
                //Toast.makeText(Single_User.this, nhi, Toast.LENGTH_SHORT).show();
                if(userDao.exist(nhi))
                {
                    int tf=0;
                    Model model=userDao.singleitem(nhi);
                    list=model.v;
                    list.remove(0);
                    if(list.size()==0)
                    {
                        Toast.makeText(Single_User.this, "No Booking Found", Toast.LENGTH_SHORT).show();
                        return;

                    }


                    r=findViewById(R.id.my_booking);
                    r.setLayoutManager(new LinearLayoutManager(Single_User.this));
                    AdapterForSingleUser adapter=new AdapterForSingleUser(list);
                    r.setAdapter(adapter);


                }
                else
                {
                    try {
                        if (r.getAdapter() != null) {
                            r.setAdapter(null);
                        }
                    }catch (Exception e)
                    {

                    }
                    t.setError("NID Not Found");
                    t.requestFocus();
                    t.setText("");
                }

            }
        });
    }
}