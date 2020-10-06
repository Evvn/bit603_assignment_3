package com.example.assignment_2;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item") // "Item" = table name
public class Item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "ItemName")
    private String itemName;

    @ColumnInfo(name = "Quantity")
    private int quantity;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
