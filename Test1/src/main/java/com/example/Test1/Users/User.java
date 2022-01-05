package com.example.Test1.Users;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private double balance;
    private double lonaAmount;

    public User() {
    }

    public User(int id, String userName, String email, String password, double balance, double lonaAmount) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.lonaAmount = lonaAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLonaAmount() {
        return lonaAmount;
    }

    public void setLonaAmount(double lonaAmount) {
        this.lonaAmount = lonaAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
