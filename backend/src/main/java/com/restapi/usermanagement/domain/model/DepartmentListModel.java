package com.restapi.usermanagement.domain.model;

public class DepartmentListModel {
    private Long id;
    private String name;
    private Boolean hasUsers;

    public DepartmentListModel() {
    }

    public DepartmentListModel(Long id, String name, Boolean hasUsers) {
        this.id = id;
        this.name = name;
        this.hasUsers = hasUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasUsers() {
        return hasUsers;
    }

    public void setHasUsers(Boolean hasUsers) {
        this.hasUsers = hasUsers;
    }
}
