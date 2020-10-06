package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize users
        final User jason = new User();
        jason.setUsername("Jason");
        jason.setPassword("Sword");
        jason.setFavoriteColor("red");

        final User billy = new User();
        billy.setUsername("Billy");
        billy.setPassword("Dinosaur");
        billy.setFavoriteColor("blue");

        final User zack = new User();
        zack.setUsername("Zack");
        zack.setPassword("Elephant");
        zack.setFavoriteColor("black");

        final User trini = new User();
        trini.setUsername("Trini");
        trini.setPassword("Tiger");
        trini.setFavoriteColor("yellow");

        final User kimberly = new User();
        kimberly.setUsername("Kimberly");
        kimberly.setPassword("Bird");
        kimberly.setFavoriteColor("pink");

        // create set of usernames for validation
        final Set<String> usersSet = new HashSet<String>(Arrays.asList("Jason", "Billy", "Zack", "Trini", "Kimberly"));

        // sign in button logic
        final Button buttonSignIn = findViewById(R.id.buttonSignIn);
        final EditText textUsername = findViewById(R.id.textInputName);
        final EditText textPassword = findViewById(R.id.textInputPassword);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // set intent for inventory (main) page for after a successful login
                Intent i = new Intent(getApplicationContext(), InventoryActivity.class);

                // check if username is a registered user
                if (usersSet.contains(textUsername.getText().toString())) {
                    // username is valid
                    // now, check if password matches
                    // check all users
                    if (textPassword.getText().toString().equals(jason.getPassword())) {
                        // check password matches, if so set user
                        currentUser = jason;
                        // if credentials are correct, nav to main Inventory activity screen
                        startActivity(i);
                    } else if (textPassword.getText().toString().equals(billy.getPassword())) {
                        // check password matches, if so set user
                        currentUser = billy;
                        // if credentials are correct, nav to main Inventory activity screen
                        startActivity(i);
                    } else if (textPassword.getText().toString().equals(zack.getPassword())) {
                        // check password matches, if so set user
                        currentUser = zack;
                        // if credentials are correct, nav to main Inventory activity screen
                        startActivity(i);
                    } else if (textPassword.getText().toString().equals(trini.getPassword())) {
                        // check password matches, if so set user
                        currentUser = trini;
                        // if credentials are correct, nav to main Inventory activity screen
                        startActivity(i);
                    } else if (textPassword.getText().toString().equals(kimberly.getPassword())) {
                        // check password matches, if so set user
                        currentUser = kimberly;
                        // if credentials are correct, nav to main Inventory activity screen
                        startActivity(i);
                    } else {
                        // password is not correct, display error message
                        // Init dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        // Set characteristics
                        builder.setMessage("Password does not match, please try again!")
                                .setTitle("Sign in error");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // OK button was clicked
                                // clear password
                                textPassword.setText("");
                            }
                        });
                        // Get AlertDialog
                        AlertDialog dialog = builder.create();
                        // Show dialog
                        dialog.show();
                    }

                } else {
                    // username is not registered, make sure it's capitalised?
                    // Init dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    // Set characteristics
                    builder.setMessage("Username does not exist, make sure it's capitalised!")
                            .setTitle("Sign in error");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // OK button was clicked
                            // clear password
                            textPassword.setText("");
                        }
                    });
                    // Get AlertDialog
                    AlertDialog dialog = builder.create();
                    // Show dialog
                    dialog.show();
                }
            }
        });
    }
}