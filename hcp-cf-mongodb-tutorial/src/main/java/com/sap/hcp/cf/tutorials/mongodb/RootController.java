package com.sap.hcp.cf.tutorials.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.sap.hcp.cf.tutorials.mongodb.model.DBObject;
import com.sap.hcp.cf.tutorials.mongodb.model.Result;

@Controller
@RequestMapping("/")
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Result onRootAccess() {

        DBCollection collection = mongoTemplate.getCollection("test");
        long count = collection.getCount();
        log.info("Object count in 'test' collection before insert: " + count + "<br/> Inserting one object.<br/>");

        BasicDBObject dBObject = new BasicDBObject();
        dBObject.put("hello", "world");
        collection.insert(dBObject);
        count = collection.count();
        log.info("Object count in test collection after insert:" + count);

        Result result = new Result();
        List<DBObject> dbObjects = new ArrayList<DBObject>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            com.mongodb.DBObject obj = cursor.next();
            final String value = (String) obj.get("hello");
            DBObject object = new DBObject();
            object.setKey("hello");
            object.setValue(value);
            dbObjects.add(object);
        }
        result.setDbObjects(dbObjects);
        result.setStatus(
                "Successfully accessed Mongodb service. Retrieving the data object inserted in test collection.");
        collection.drop();
        return result;
    }
}
