package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // declare elements
        final EditText textItemName = findViewById(R.id.editTextItemName);
        final EditText textItemNumber = findViewById(R.id.editTextItemNumber);
        final Button buttonCancelAdd = findViewById(R.id.buttonCancelItem);
        final Button buttonAddItem = findViewById(R.id.buttonAddItem);

        // cancel button event listener
        buttonCancelAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // navigate to inventory page
                Intent i = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(i);
            }
        });

        // add item button event listener
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if item name is empty, show error
                if (textItemName.getText().toString().isEmpty()) {
                    // Init dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
                    // Set characteristics
                    builder.setMessage("Please add an item name.")
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
                    return;
                }
                // if quantity is empty, show error
                if (textItemNumber.getText().toString().isEmpty()) {
                    // Init dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
                    // Set characteristics
                    builder.setMessage("Please add an item quantity.")
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
                    return;
                }

                // add item to database
                // Obtain data from the interface
                String itemName = textItemName.getText().toString();
                int itemQuantity = Integer.parseInt(textItemNumber.getText().toString());

                // Create new customer object
                Item i = new Item();
                i.setItemName(itemName);
                i.setQuantity(itemQuantity);

                // Insert customer into database
                InventoryActivity.inventoryDatabase.dao().addItem(i);

                // Display a message
                Toast.makeText(getBaseContext(),"New item added successfully!", Toast.LENGTH_SHORT).show();

                // navigate to inventory page
                Intent inv = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(inv);
            }
        });
    }
}