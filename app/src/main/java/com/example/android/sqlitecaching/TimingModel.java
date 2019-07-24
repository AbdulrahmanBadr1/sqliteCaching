package com.example.android.sqlitecaching;

public class TimingModel {
    private String tableName;
    private String lastUpdateDate;

    public TimingModel(String tableName, String lastUpdateDate) {
        this.tableName = tableName;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
