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
    private String activationCode;


    public static class Builder{

        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private boolean isActive;
        private boolean mailings;
        private String avatarPath;
        private double balance;
        private String activationCode;
        private Role role;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setMailings(boolean mailings) {
            this.mailings = mailings;
            return this;
        }

        public Builder setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
            return this;
        }

        public Builder setActivationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setRole(String role) {
            this.role = Role.valueOf(role.trim().toUpperCase());
            return this;
        }

        public User build() {
            return new User(id, name, surname, email, password, isActive, mailings, avatarPath, balance, role, activationCode);
        }
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setMailings(boolean mailings) {
        this.mailings = mailings;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isMailings() {
        return mailings;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public double getBalance() {
        return balance;
    }

    public Role getRole() {
        return role;
    }

    private User(int id, String name, String surname, String email, String password, boolean isActive, boolean mailings, String avatarPath, double balance, Role role, String activationCode) {
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
        this.activationCode = activationCode;
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
