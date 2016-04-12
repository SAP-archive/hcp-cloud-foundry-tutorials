package com.sap.hcp.cf.tutorials.mongodb.model;

import java.util.List;

public class Result {

    private String status;

    private List<DBObject> dbObjects;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DBObject> getDbObjects() {
        return dbObjects;
    }

    public void setDbObjects(List<DBObject> dbObjects) {
        this.dbObjects = dbObjects;
    }

}
