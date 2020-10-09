package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static InventoryDatabase inventoryDatabase;

    public static boolean isAdmin = false;
    public static boolean isLoggedIn = false;

    // error message reusable function
    public void alertDialog(String title, String message) {
        // Init dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Set characteristics
        builder.setMessage(message)
                .setTitle(title);

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
        setContentView(R.layout.activity_main);

        // initialise user db
        // init inventory db
        inventoryDatabase = Room.databaseBuilder(getApplicationContext(), InventoryDatabase.class, "userdb").allowMainThreadQueries().build();

        // create hard-coded admin user who is able to manage users
        final String adminUsername = "Admin";
        final String adminPassword = "CookieManagement84";

        // sign in button logic
        final Button buttonSignIn = findViewById(R.id.buttonSignIn);
        final EditText textUsername = findViewById(R.id.textInputName);
        final EditText textPassword = findViewById(R.id.textInputPassword);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // set intent for user mgmt page for after a successful admin login
                Intent mgmt = new Intent(getApplicationContext(), UserManagementActivity.class);

                // set intent for inventory (main) page for after a successful login
                Intent i = new Intent(getApplicationContext(), InventoryActivity.class);

                // check if user is admin
                if (textUsername.getText().toString().equals(adminUsername)) {
                    // now, check if password is admin password
                    if (textPassword.getText().toString().equals(adminPassword)) {
                        // if credentials are correct, nav to main Inventory activity screen
                        isAdmin = true;
                        isLoggedIn = true;
                        startActivity(mgmt);
                    } else {
                        // admin password is not correct, display error message
                        alertDialog("Sign in error", "Password does not match, please try again!");
                    }

                } else {
                    // check if user exists in user db
                    // Pull users table from db
                    List<User> users = inventoryDatabase.dao().getUsers();
                    Boolean userExists = false;
                    String checkPassword = "";
                    for(User u : users) {
                        if (u.getUsername().equals(textUsername.getText().toString())) {
                            userExists = true;
                            checkPassword = u.getPassword();
                        }
                    }
                    if (userExists) {
                        // check user password matches
                        if (textPassword.getText().toString().equals(checkPassword)) {
                            // username and password are correct, redirect to inventory page
                            startActivity(i);
                        } else {
                            // password does not match, show error
                            alertDialog("Sign in error", "Password does not match!");
                            textPassword.setText("");
                        }
                    } else {
                        // username is not registered, make sure it's capitalised?
                        alertDialog("Sign in error", "Username does not exist, make sure it's capitalised!");
                        textUsername.setText("");
                        textPassword.setText("");
                    }
                }
            }
        });
    }
}