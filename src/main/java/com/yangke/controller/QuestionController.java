package com.yangke.controller;

import com.yangke.dao.QuestionDAO;
import com.yangke.model.*;
import com.yangke.service.CommentService;
import com.yangke.service.QuestionService;
import com.yangke.service.UserService;
import com.yangke.util.GetJSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangke on 16/8/20.
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    @RequestMapping(value = "/question/{qid}", method = {RequestMethod.GET})
    public String questionDetail(Model model, @PathVariable("qid") int qid) {
        Question question = questionService.getById(qid);
        model.addAttribute("question", question);
        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> comments = new ArrayList<ViewObject>();
        for(Comment comment:commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            vo.set("user",userService.getUser(comment.getUserId()));
            comments.add(vo);
        }
        model.addAttribute("comments", comments);

        return "detail";
    }



    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            question.setCommentCount(0);
            question.setAttentionCount(0);
            question.setUserId(hostHolder.getUser().getId());

            if (questionService.addQuestion(question) > 0) {
                return GetJSONString.getJSONString(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return GetJSONString.getJSONString(1, "失败");
    }


}
