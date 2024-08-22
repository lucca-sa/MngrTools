package com.restapi.usermanagement.domain.model;

public class PaginationResponseModel {
    private Integer page;
    private Long totalItems;
    private Integer totalPage;
    private Object data;

    public PaginationResponseModel(Integer page, Long totalItems, Integer totalPage, Object data) {
        this.page = page;
        this.totalItems = totalItems;
        this.totalPage = totalPage;
        this.data = data;
    }

    public PaginationResponseModel() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
