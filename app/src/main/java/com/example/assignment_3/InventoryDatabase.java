package com.example.assignment_3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class InventoryDatabase extends RoomDatabase {
    public abstract Dao dao();
}
