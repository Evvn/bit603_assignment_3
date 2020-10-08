package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity {

    // error message reusable function
    public void alertDialog(String message) {
        // Init dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);
        // Set characteristics
        builder.setMessage(message)
                .setTitle("Add item error");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // OK button was clicked
            }
        });
        // Get AlertDialog
        AlertDialog dialog = builder.create();
        // Show dialog
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // declare elements
        final EditText textUsername = findViewById(R.id.textUsername);
        final EditText textPassword = findViewById(R.id.textPassword);
        final EditText textDob = findViewById(R.id.textDob);
        final EditText textEmployeeNumber = findViewById(R.id.textEmployeeNumber);
        final EditText textPhoneNumber = findViewById(R.id.textPhoneNumber);
        final EditText textAddress = findViewById(R.id.textAddress);
        final Button buttonCancel = findViewById(R.id.buttonCancelUser);
        final Button buttonAddUser = findViewById(R.id.buttonAddUserSubmit);

        // cancel button event listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate back to user mgmt
                Intent i = new Intent(getApplicationContext(), UserManagementActivity.class);
                startActivity(i);
            }
        });

        // add item button event listener
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if field is empty, show relevant error
                if (textUsername.getText().toString().isEmpty()) {
                    alertDialog("Please add a username.");
                    return;
                }
                if (textPassword.getText().toString().isEmpty()) {
                    alertDialog("Please add a password.");
                    return;
                }
                if (textDob.getText().toString().isEmpty()) {
                    alertDialog("Please add a DOB.");
                    return;
                }
                if (textEmployeeNumber.getText().toString().isEmpty()) {
                    alertDialog("Please add an employee number.");
                    return;
                }
                if (textPhoneNumber.getText().toString().isEmpty()) {
                    alertDialog("Please add a phone number.");
                    return;
                }
                if (textAddress.getText().toString().isEmpty()) {
                    alertDialog("Please add an address.");
                    return;
                }

                // add user to database
                // Obtain data from the interface
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();
                String dob = textDob.getText().toString();
                int employeeNumber = Integer.parseInt(textEmployeeNumber.getText().toString());
                int phoneNumber = Integer.parseInt(textPhoneNumber.getText().toString());
                String address = textAddress.getText().toString();

                // Create new customer object
                User u = new User();
                u.setUsername(username);
                u.setPassword(password);
                u.setDob(dob);
                u.setEmployeeNumber(employeeNumber);
                u.setPhoneNumber(phoneNumber);
                u.setAddress(address);

                // Insert customer into database
                MainActivity.inventoryDatabase.dao().addUser(u);

                // Display a message
                Toast.makeText(getBaseContext(),"New user added successfully!", Toast.LENGTH_SHORT).show();

                // navigate to user mgmt page
                Intent userMgmt = new Intent(getApplicationContext(), UserManagementActivity.class);
                startActivity(userMgmt);
            }
        });
    }
}