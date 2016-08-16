package com.yangke.controller;


import com.yangke.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangke on 16/8/15.
 */
@Controller
public class IndexController {


    @RequestMapping(path ={"/","/index"},method = {RequestMethod.GET})
    public String index(Model model) {
        model.addAttribute("h1","j2");
        List<String> l = Arrays.asList(new String[] {"hong","huang"});
        model.addAttribute("colors",l);
        User u = new User("huhu");
        model.addAttribute("h3",u);
        return "header";
    }

}
