package com.example.assignment_3;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert void addUser(User u);

    @Insert
    public void addItem(Item i);

    @Query("SELECT * FROM Item")
    public List<Item> getItems();

    @Query("SELECT * FROM User")
    public List<User> getUsers();

    @Query("DELETE FROM Item")
    public void deleteItems();

    @Delete
    public void deleteUser(User u);
}
