package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    public static InventoryDatabase inventoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        final TextView textHeading = findViewById(R.id.textInventoryHeading);
        final ImageView iconInventory = findViewById(R.id.iconInventory);
        final ImageView iconAddItem = findViewById(R.id.iconAddItem);
        final ImageView iconLogOut = findViewById(R.id.iconLogOut);
        final TextView inventoryOutput = findViewById(R.id.inventoryOutput);

        // inventory screen heading - personalise to current user
        String customHeading = "Hi, " + MainActivity.currentUser.getUsername();
        // set message
        textHeading.setText(customHeading);
        // set color
        int favoriteColor = Color.parseColor(MainActivity.currentUser.getFavoriteColor());
        textHeading.setTextColor(favoriteColor);

        // logout button listener
        iconLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Init dialog to confirm logout
                AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);
                // Set characteristics
                builder.setMessage("Are you sure you want to log out?")
                        .setTitle("Log out");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // OK button was clicked
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

                // Get AlertDialog
                AlertDialog dialog = builder.create();
                // Show dialog
                dialog.show();
            }
        });
        // end logout code

        // init inventory db
        inventoryDatabase = Room.databaseBuilder(getApplicationContext(), InventoryDatabase.class, "inventorydb").allowMainThreadQueries().build();

        // list db to something
        List<Item> items = inventoryDatabase.dao().getItems();
        String output = "";
        for(Item i : items) {
            output += i.getQuantity() + "x " + i.getItemName() + "\n";
        }
        inventoryOutput.setText(output);

        // reload inventory db on icon click
        iconInventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Item> items = inventoryDatabase.dao().getItems();
                String output = "";
                for(Item i : items) {
                    output += i.getQuantity() + "x " + i.getItemName() + "\n";
                }
                 inventoryOutput.setText(output);
            }
        });

        // add item icon event listener
        iconAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // navigate to add item page
                Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(i);
            }
        });

    }
}