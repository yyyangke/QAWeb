package com.yangke.service;

import com.yangke.dao.QuestionDAO;
import com.yangke.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by yangke on 16/8/17.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public int addQuestion(Question question) {
        //敏感词过滤
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        return questionDAO.addQuestion(question)>0?question.getId():0;
    }

    public Question getById(int qid) {
        return questionDAO.selectQuestionById(qid);
    }

    public int updateCommentCount(int entityId,int count) {
        return questionDAO.updateCommentCount(entityId,count);
    }

    public List<Question>  getLatestQuestion(int userId, int offset,int limit) {
        return questionDAO.selectLatestQuestions(userId,offset,limit);
    }
}
