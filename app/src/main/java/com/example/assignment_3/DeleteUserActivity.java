package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class DeleteUserActivity extends AppCompatActivity {

    // error message reusable function
    public void alertDialog(String title, String message) {
        // Init dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUserActivity.this);
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
        setContentView(R.layout.activity_delete_user);

        // declare elements
        final EditText textUsername = findViewById(R.id.editTextDeleteUsername);
        final Button buttonCancel = findViewById(R.id.buttonCancelDelete);
        final Button buttonDelete = findViewById(R.id.buttonDeleteUser);

        // cancel button event listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to user mgmt page
                Intent i = new Intent(getApplicationContext(), UserManagementActivity.class);
                startActivity(i);
            }
        });

        // delete button event listener
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if username is empty, show error
                if (textUsername.getText().toString().isEmpty()) {
                    alertDialog("Delete user error", "Please enter a username!");
                    return;
                }

                // Obtain data from the interface
                String username = textUsername.getText().toString();

                // select users from db
                List<User> allUsers = MainActivity.inventoryDatabase.dao().getUsers();

                // check if user input exists in list
                int counter = 1;
                for (User u : allUsers) {
                    if (textUsername.getText().toString().equals(u.getUsername())) {
                        // delete user
                        // Init dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUserActivity.this);
                        // Set characteristics
                        builder.setMessage("Are you sure you want to remove user " + u.getUsername() + "?")
                                .setTitle("Clear items");

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                        final User userToDelete = u;

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // OK button was clicked
                                MainActivity.inventoryDatabase.dao().deleteUser(userToDelete);

                                // Display a success message
                                Toast.makeText(getBaseContext(),"User deleted!", Toast.LENGTH_SHORT).show();

                                // navigate back to user mgmt page
                                Intent i = new Intent(getApplicationContext(), UserManagementActivity.class);
                                startActivity(i);
                            }
                        });
                        // Get AlertDialog
                        AlertDialog dialog = builder.create();
                        // Show dialog
                        dialog.show();
                    }
                    counter++;
                    if (counter == allUsers.size()) {
                        // user does not exist
                        alertDialog("Delete user error", "User does not exist, check the username!");
                    }
                }
            }
        });

    }
}