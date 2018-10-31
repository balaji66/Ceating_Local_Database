package com.durga.balaji66.ceatinglocaldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    UserModelClass modelClass =new UserModelClass();
    String name ="Balaji";
    String phone ="9912310102";
    String email ="b@gmail.com";
    String password ="12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelClass.setName(name);
        modelClass.setPhone(phone);
        modelClass.setEmail(email);
        modelClass.setPassword(password);
        if(!databaseHelper.checkCandidateIsRegisteredOrNot(phone))
        {
            databaseHelper.AddNewUser(modelClass);
            Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getApplicationContext()," Your Mobile Number Already Registered",Toast.LENGTH_LONG).show();

        }




    }
}
