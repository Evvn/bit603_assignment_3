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

public class AddItemActivity extends AppCompatActivity {

    // error message reusable function
    public void alertDialog(String title, String message) {
        // Init dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
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
        setContentView(R.layout.activity_add_item);

        // declare elements
        final EditText textItemName = findViewById(R.id.editTextItemName);
        final EditText textItemNumber = findViewById(R.id.editTextItemNumber);
        final Spinner spinnerItemType = findViewById(R.id.spinnerItemType);
        final Button buttonCancelAdd = findViewById(R.id.buttonCancelItem);
        final Button buttonAddItem = findViewById(R.id.buttonAddItem);

        // set spinner values to item type options
        final String[] itemTypes = new String[] {"Biscuit", "Cookie", "Cake", "Ingredient", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemType.setAdapter(adapter);


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
                    alertDialog("Add item error", "Please add an item name");
                    return;
                }
                // if quantity is empty, show error
                if (textItemNumber.getText().toString().isEmpty()) {
                    alertDialog("Add item error", "Please add an item quantity");
                    return;
                }

                // Obtain data from the interface
                String itemName = textItemName.getText().toString();
                int itemQuantity = Integer.parseInt(textItemNumber.getText().toString());
                String itemType = spinnerItemType.getSelectedItem().toString();

                // Create new item object
                Item i = new Item();
                i.setItemName(itemName);
                i.setQuantity(itemQuantity);
                i.setItemType(itemType);

                // Insert item into database
                MainActivity.inventoryDatabase.dao().addItem(i);

                // Display a message
                Toast.makeText(getBaseContext(),"New item added successfully!", Toast.LENGTH_SHORT).show();

                // navigate to inventory page
                Intent inv = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(inv);
            }
        });
    }
}