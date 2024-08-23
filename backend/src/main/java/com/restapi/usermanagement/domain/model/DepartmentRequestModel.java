package com.restapi.usermanagement.domain.model;

public class DepartmentRequestModel {
    private String name;

    public DepartmentRequestModel(String name) {
        this.name = name;
    }

    public DepartmentRequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
