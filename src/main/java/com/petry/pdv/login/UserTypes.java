package com.petry.pdv.login;

public enum UserTypes {
    CLIENTE("Cliente"),
    DONO("Dono"),
    FUNCIONARIO("Funcionário"),
    ADMIN("Administrador");

    private String label;

    UserTypes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}