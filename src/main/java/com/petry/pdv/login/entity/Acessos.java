package com.petry.pdv.login.entity;

public enum Acessos {
    TodosOsAcessos("dashboard,produtos,estoque,funcionario");
    private String label;

    Acessos(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}