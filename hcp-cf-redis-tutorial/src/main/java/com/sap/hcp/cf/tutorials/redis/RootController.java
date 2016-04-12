package com.sap.hcp.cf.tutorials.redis;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.hcp.cf.tutorials.redis.model.RedisObject;
import com.sap.hcp.cf.tutorials.redis.model.Result;

@Controller
@RequestMapping("/")
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Result onRootAccess() {

        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        log.info("Setting key/value pair 'hello'/'world'");
        redisConnection.set("hello".getBytes(), "world".getBytes());

        log.info("Retrieving value for key 'hello': ");
        final String value = new String(redisConnection.get("hello".getBytes()));
        Result result = new Result();
        result.setStatus("Succesfully connected to Redis and retrieved the key/value pair that was inserted");
        RedisObject redisObject = new RedisObject();
        redisObject.setKey("hello");
        redisObject.setValue(value);
        final ArrayList<RedisObject> redisObjects = new ArrayList<RedisObject>();
        redisObjects.add(redisObject);
        result.setRedisObjects(redisObjects);
        return result;
    }
}
