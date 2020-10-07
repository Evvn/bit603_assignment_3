package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UserManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        // user mgmt buttons
        final Button buttonViewUser = findViewById(R.id.buttonViewUser);
        final Button buttonDeleteUser = findViewById(R.id.buttonDeleteUser);
        final Button buttonAddUser = findViewById(R.id.buttonAddUser);

        // nav buttons
        final ImageView iconInventory = findViewById(R.id.usersIconInventory);
        final ImageView iconLogOut = findViewById(R.id.usersIconLogOut);

        // view user button listener
        buttonViewUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to view user page
                Intent i = new Intent(getApplicationContext(), ViewUserActivity.class);
                startActivity(i);
            }
        });

        // delete user button listener
        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to delete user page
                Intent i = new Intent(getApplicationContext(), DeleteUserActivity.class);
                startActivity(i);
            }
        });

        // add user button listener
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to add user page
                Intent i = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivity(i);
            }
        });

        // logout button listener
        iconLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Init dialog to confirm logout
                AlertDialog.Builder builder = new AlertDialog.Builder(UserManagementActivity.this);
                // Set characteristics
                builder.setMessage("Are you sure you want to log out?")
                        .setTitle("Log out");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // logout
                        // return to sign in page
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancel log out
                    }
                });
                // Create AlertDialog
                AlertDialog dialog = builder.create();
                // Show dialog
                dialog.show();
            }
        });
        // end logout code

        // navigate to inventory
        iconInventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // return to sign in page
                Intent i = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(i);
            }
        });
    }
}