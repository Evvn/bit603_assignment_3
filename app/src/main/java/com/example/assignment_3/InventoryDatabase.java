package com.example.assignment_3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Item.class}, version = 1)
public abstract class InventoryDatabase extends RoomDatabase {
    public abstract Dao dao();
}
