package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    public int startingIndex = 1;

    // display items reusable function
    public String printItems(List<Item> items, int startingIndex) {
        String output = "";
        int index = 1;
        int counter = 1;
        for(Item i : items) {
            // only show 5, count and make sure index is within startingIndex + 5
            if (counter <= 5 && index >= startingIndex && index <= startingIndex + 5) {
                output += i.getQuantity() + "x " + i.getItemName() + " " + i.getItemType() + "\n";
                counter++;
            }
            index++;
        }
        return output;
    }

    public boolean showPrev(int startingIndex) {
        boolean show = false;

        if (startingIndex > 1) {
            show = true;
        }

        return show;
    }

    public boolean showNext(List<Item> items, int startingIndex) {
        boolean show = false;

        if (startingIndex < items.size()) {
            show = true;
        }

        return show;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        final TextView textHeading = findViewById(R.id.textInventoryHeading);
        final ImageView iconInventory = findViewById(R.id.iconInventory);
        final ImageView iconAddItem = findViewById(R.id.iconAddItem);
        final ImageView iconLogOut = findViewById(R.id.iconLogOut);
        final TextView inventoryOutput = findViewById(R.id.inventoryOutput);
        final Button buttonPrev = findViewById(R.id.buttonPrev);
        final Button buttonNext = findViewById(R.id.buttonNext);
        final Button buttonClear = findViewById(R.id.buttonClear);
        final Button buttonTest = findViewById(R.id.buttonAddTestItems);

        // list db to something
        final List<Item> items = MainActivity.inventoryDatabase.dao().getItems();

        // show or hide next and prev buttons on load if needed
        if (showPrev(startingIndex)) {
            buttonPrev.setVisibility(View.VISIBLE);
        } else {
            buttonPrev.setVisibility(View.INVISIBLE);
        }

        if (showNext(items, startingIndex)) {
            buttonNext.setVisibility(View.VISIBLE);
        }  else {
            buttonNext.setVisibility(View.INVISIBLE);
        }

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

        // show first 5 items
        inventoryOutput.setText(printItems(items, startingIndex));

        // prev 5 items
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (startingIndex - 5 > 1) {
                    startingIndex = startingIndex - 5;
                    // reload output
                    inventoryOutput.setText(printItems(items, startingIndex));
                } else {
                    startingIndex = 1;
                    // reload output
                    inventoryOutput.setText(printItems(items, startingIndex));
                }
                // show or hide controls based on counter
                if (showPrev(startingIndex)) {
                    buttonPrev.setVisibility(View.VISIBLE);
                } else {
                    buttonPrev.setVisibility(View.INVISIBLE);
                }
                if (showNext(items, startingIndex)) {
                    buttonNext.setVisibility(View.VISIBLE);
                }  else {
                    buttonNext.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getBaseContext(),"Showing items " + startingIndex + " of " + items.size(), Toast.LENGTH_SHORT).show();
            }
        });

        // next 5 items
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (startingIndex + 5 <= items.size()) {
                    startingIndex = startingIndex + 5;
                    // reload output
                    inventoryOutput.setText(printItems(items, startingIndex));
                } else {
                    startingIndex = items.size() - 5;
                }
                // show or hide controls based on counter
                if (showPrev(startingIndex)) {
                    buttonPrev.setVisibility(View.VISIBLE);
                } else {
                    buttonPrev.setVisibility(View.INVISIBLE);
                }
                if (showNext(items, startingIndex)) {
                    buttonNext.setVisibility(View.VISIBLE);
                }  else {
                    buttonNext.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getBaseContext(),"Showing items " + startingIndex + " of " + items.size(), Toast.LENGTH_SHORT).show();
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

        // clear inventory button
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Init dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);
                // Set characteristics
                builder.setMessage("Are you sure you want to remove all items?")
                        .setTitle("Clear items");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // OK button was clicked
                        MainActivity.inventoryDatabase.dao().deleteItems();

                        // Display a message
                        Toast.makeText(getBaseContext(),"All items removed!", Toast.LENGTH_SHORT).show();
                        inventoryOutput.setText(R.string.inventory_output);

                        // reload page
                        finish();
                        startActivity(getIntent());
                    }
                });
                // Get AlertDialog
                AlertDialog dialog = builder.create();
                // Show dialog
                dialog.show();
            }
        });

        // add 20 test items on button click
        buttonTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Init dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);
                // Set characteristics
                builder.setMessage("Are you sure you want to add 20 test items?")
                        .setTitle("Add test items");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // OK button was clicked
                        // make 20 test items
                        for (int limit = 0; limit < 20; limit++) {
                            // Create new item object
                            Item i = new Item();
                            i.setItemName("TestItem " + limit);
                            i.setQuantity(limit);
                            i.setItemType("Other");

                            // Insert item into database
                            MainActivity.inventoryDatabase.dao().addItem(i);
                        }
                        // Display a message
                        Toast.makeText(getBaseContext(),"Test items added successfully!", Toast.LENGTH_SHORT).show();

                        // reload page
                        finish();
                        startActivity(getIntent());
                    }
                });
                // Get AlertDialog
                AlertDialog dialog = builder.create();
                // Show dialog
                dialog.show();
            }
        });
    }
}