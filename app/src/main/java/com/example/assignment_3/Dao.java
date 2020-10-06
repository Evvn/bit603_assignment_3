package com.example.assignment_3;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    public void addItem(Item i);

    @Query("SELECT * FROM Item")
    public List<Item> getItems();
}
