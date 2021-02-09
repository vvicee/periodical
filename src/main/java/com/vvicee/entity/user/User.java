package com.vvicee.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isActive;
    private boolean mailings;
    private String avatarPath;
    private double balance;
    private Role role;

    public User() {

    }

    public User(String name, String surname, String email, String password, boolean mailings) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.mailings = mailings;
    }

    public User(int id, String name, String surname, String email, String password, boolean isActive, boolean mailings, String avatarPath, double balance, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.mailings = mailings;
        this.avatarPath = avatarPath;
        this.balance = balance;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isMailings() {
        return mailings;
    }

    public void setMailings(boolean mailings) {
        this.mailings = mailings;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase().trim());
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "surname: " + surname + "\n"
                + "email: " + email + "\n"
                + "balance: " + "\n";
    }
}
