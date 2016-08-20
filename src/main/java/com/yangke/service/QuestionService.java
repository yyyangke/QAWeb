package com.yangke.service;

import com.yangke.dao.QuestionDAO;
import com.yangke.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangke on 16/8/17.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDao;

    public List<Question>  getLatestQuestion(int userId, int offset,int limit) {
        return questionDao.selectLatestQuestions(userId,offset,limit);
    }
}
