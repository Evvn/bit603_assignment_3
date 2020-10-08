package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // init page assets
        final Button buttonBack = findViewById(R.id.buttonCloseViewUsers);
        final TextView usersOutput = findViewById(R.id.textUsersOutput);

        // back button listener
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to user mgmt page
                Intent i = new Intent(getApplicationContext(), UserManagementActivity.class);
                startActivity(i);
            }
        });

        // write users table to output
        List<User> users = MainActivity.inventoryDatabase.dao().getUsers();
        String output = "";
        for(User u : users) {
            output += "Username: " + u.getUsername()  + "\nPassword: " + u.getPassword() + "\nDOB: " + u.getDob() + "\nEmployee #: " + u.getEmployeeNumber() + "\nPhone #: " + u.getPhoneNumber() + "\nAddress: " + u.getAddress() + "\n\n";
        }
        usersOutput.setText(output);
    }
}