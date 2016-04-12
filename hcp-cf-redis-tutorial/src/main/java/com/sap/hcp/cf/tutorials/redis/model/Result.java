package com.sap.hcp.cf.tutorials.redis.model;

import java.util.List;

public class Result {

    private String status;

    private List<RedisObject> redisObjects;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RedisObject> getRedisObjects() {
        return redisObjects;
    }

    public void setRedisObjects(List<RedisObject> redisObjects) {
        this.redisObjects = redisObjects;
    }

}
