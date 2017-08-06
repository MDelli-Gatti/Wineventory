package com.delligatti.Wineventory.controllers;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.delligatti.Wineventory.entities.User;
import com.delligatti.Wineventory.entities.Item;
import com.delligatti.Wineventory.services.UserRepository;
import com.delligatti.Wineventory.services.ItemRepository;
import javax.annotation.PostConstruct;
import java.sql.SQLException;

@RestController
public class WineventoryController {
    @Autowired
    UserRepository users;

    @Autowired
    ItemRepository items;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer("-webPort", "1337").start();
    }
}
