package com.eresko.entity.user;

public enum Role {
    USER("USER"), ADMIN("ADMIN");

    private final String type;

    Role(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
