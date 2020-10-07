package com.example.assignment_3;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "User") // "User" = table name
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Dob")
    private Date dob;

    @ColumnInfo(name = "EmployeeNumber")
    private int employeeNumber;

    @ColumnInfo(name = "PhoneNumber")
    private int phoneNumber;

    @ColumnInfo(name = "Address")
    private String address;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
