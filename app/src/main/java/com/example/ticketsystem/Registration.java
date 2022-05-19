package com.example.ticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ticketsystem.Users.AppDatabase;
import com.example.ticketsystem.Users.Model;
import com.example.ticketsystem.Users.UserDao;

import java.util.ArrayList;
import java.util.Calendar;
public class Registration extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton,Registration;
    EditText f_name,l_name,NHI_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        Registration=findViewById(R.id.registration);
        dateButton.setText(getTodaysDate());

        f_name=findViewById(R.id.user_name);
        l_name=findViewById(R.id.last_name);
        NHI_number=findViewById(R.id.nhi_number);

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userregi();
            }
        });

    }
    private void userregi() {
        String first,last,nhi,DOB;


        //long count = someString.chars().filter(ch -> ch == 'e').count();
        ArrayList<String> t=new ArrayList<>();
        first=f_name.getText().toString();
        last=l_name.getText().toString();
        nhi=NHI_number.getText().toString();
        DOB=dateButton.getText().toString();

        int tf=0;

        if(first.length()<=0)
        {
            f_name.setError("Invalid Name");
            f_name.requestFocus();
            tf=1;
        }
        if(last.length()<=0)
        {
            l_name.setError("Invalid Name");
            l_name.requestFocus();
            tf=1;
        }
        if(nhi.length()<5)
        {
            NHI_number.setError("Invalid Number");
            NHI_number.requestFocus();
            tf=1;
        }

        if(tf>0)
        {
            return;
        }

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"tbuser_dd").allowMainThreadQueries().build();
        UserDao userDao=db.userDao();
        if(userDao.exist(nhi))
        {
            Toast.makeText(this, "User Already Registered", Toast.LENGTH_SHORT).show();
            return;
        }

        Model model=new Model(0,first,last,DOB,nhi,t);
        userDao.insert(model);
        Toast.makeText(this, "User Successfully Registered", Toast.LENGTH_LONG).show();

        f_name.setText("");
        l_name.setText("");
        NHI_number.setText("");
        dateButton.setText(getTodaysDate());




    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}