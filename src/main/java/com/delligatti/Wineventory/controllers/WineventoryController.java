package com.delligatti.Wineventory.controllers;

import com.delligatti.Wineventory.services.WineRepository;
import com.delligatti.Wineventory.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.delligatti.Wineventory.entities.User;
import com.delligatti.Wineventory.entities.Wine;
import com.delligatti.Wineventory.services.UserRepository;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class WineventoryController {

    @Autowired
    UserRepository users;

    @Autowired
    WineRepository wines;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        if (users.count() == 0) {
            User user = new User("Michael", PasswordStorage.createHash("Nitro"));
            users.save(user);
        }
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null){
            return "login";
        }
        User user = users.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            Iterable<Wine> ws;
            ws = wines.findAll();
            model.addAttribute("wines", ws);
        }
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findByUsername(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-wine", method = RequestMethod.POST)
    public String addWine(String wineName, double cost, double minimum, double stock){
        Wine wine = new Wine(wineName, cost, minimum, stock);
        wines.save(wine);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-wine", method = RequestMethod.DELETE)
    public String delete(int id){
        wines.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/update-wine", method = RequestMethod.POST)
    public String update(int id, double stock){
        Wine wine = wines.findOne(id);
        wine.setStock(stock);
        wines.save(wine);
        return "redirect:/";
    }

    @RequestMapping(path = "/find-needs", method = RequestMethod.GET)
    public String needs(Model model){
        ArrayList<Wine> needs = new ArrayList<>();
        for (Wine wine: wines.findAll()){
            if (wine.getMinimum() > wine.getStock()){
                needs.add(wine);
            }
            model.addAttribute("needs", needs);
        }
        return "needs";
    }
}
