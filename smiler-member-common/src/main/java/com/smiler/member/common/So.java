package com.smiler.member.common;

import java.io.Serializable;
import java.util.List;

public class So implements Serializable {

    private static final long serialVersionUID = 7481664466130486565L;

    private int currentPage = 1;
    private int pageSize = 10;
    private boolean enableCount = true;
    /**
     * true:不分页，不count，仅做排序,
     */
    private boolean orderByOnly;
    private List<Sort> sortList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isEnableCount() {
        return enableCount;
    }

    public void setEnableCount(boolean enableCount) {
        this.enableCount = enableCount;
    }

    public boolean isOrderByOnly() {
        return orderByOnly;
    }

    public void setOrderByOnly(boolean orderByOnly) {
        this.orderByOnly = orderByOnly;
    }

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }
}