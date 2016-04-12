package com.sap.hcp.cf.tutorials.postgres;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.hcp.cf.tutorials.postgres.model.Contact;
import com.sap.hcp.cf.tutorials.postgres.model.Result;

@Controller
@RequestMapping("/")
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Result onRootAccess() {
        log.info("Creating table");

        jdbcTemplate.execute("DROP TABLE IF EXISTS contacts");
        jdbcTemplate.execute("CREATE TABLE contacts(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        log.info("Inserting data in table");
        jdbcTemplate.update("INSERT INTO contacts(first_name, last_name) VALUES ('John','Wright')");

        log.info("Retrieving data from table");
        final List<Contact> contacts = jdbcTemplate.query("SELECT id, first_name, last_name FROM contacts",
                new BeanPropertyRowMapper<Contact>(Contact.class));

        Result result = new Result();
        result.setStatus("Successfully connected to PostgreSQL. Retrieved data from contacts table");
        result.setContacts(contacts);
        return result;
    }
}
