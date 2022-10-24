package com.example.demo.enums;

public enum UserType {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String description;

    private UserType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static UserType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (UserType x : UserType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
