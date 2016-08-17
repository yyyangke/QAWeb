package com.yangke;

import com.yangke.dao.QuestionDAO;
import com.yangke.dao.UserDAO;
import com.yangke.model.Question;
import com.yangke.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QaWebApplication.class)
//@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void contextLoads() {

        User user = new User();
        user.setName("yangke");
        user.setPassword("1995");
        user.setSalt("e");
        user.setId(1);
    //    userDAO.updatePassword(user);
    //    userDAO.addUser(user);

        Question  question = new Question();
        question.setCommentCount(1);
        question.setContent("hwhwh");
        question.setCreatedDate(new Date());
        question.setTitle("pp");
        question.setUserId(1);
        //questionDAO.addQuestion(question);
        List<Question> a = questionDAO.selectLatestQuestions(1,0,1);
        System.out.println(a.get(0).getTitle());
    }


}
