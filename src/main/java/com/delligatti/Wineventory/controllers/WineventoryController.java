package com.delligatti.Wineventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.delligatti.Wineventory.entities.User;
import com.delligatti.Wineventory.entities.Item;
import com.delligatti.Wineventory.services.UserRepository;
import com.delligatti.Wineventory.services.ItemRepository;

import javax.annotation.PostConstruct;

@RestController
public class WineventoryController {
    @Autowired
    UserRepository users;

    @Autowired
    ItemRepository items;

    @PostConstruct
    public void init(){

    }
}
