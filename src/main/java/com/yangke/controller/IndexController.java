package com.yangke.controller;


import com.yangke.model.Question;
import com.yangke.model.User;
import com.yangke.model.ViewObject;
import com.yangke.service.QuestionService;
import com.yangke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangke on 16/8/15.
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = {"/user/{userId}"},method = RequestMethod.GET)
    public String userIndex(Model model,@PathVariable("userId") int userId) {
        getQuestion(model,userId,0,10);
        return "index";

    }

    @RequestMapping(path ={"/","/index"},method = {RequestMethod.GET})
    public String index(Model model) {
        getQuestion(model,0,0,10);
        return "index";
    }

    public void getQuestion(Model model, int userId,int offset, int limit) {
        List<Question> questions = questionService.getLatestQuestion(userId,offset,limit);
        List<ViewObject> vos = new ArrayList<ViewObject>();
        for(Question question : questions) {
            ViewObject vo = new ViewObject();
            vo.set("question",question);
            vo.set("user",userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("vos",vos);
    }

}
