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
import java.util.Random;

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
        //创建10个用户
//        for(int i=1;i<=10;++i) {
//            String name = String.format("name%d",i);
//            String password = "19950627";
//            String salt = String.format("salt%d",i);
//            addUser(name,password,salt);
//        }
        //创建10个问题
        for(int i=1;i<=10;++i) {
            int commentCount = 10;
            String content = String.format("这是问题%d的内容",i);
            String title = String.format("salt%d",i);
            int userId = i;
            addQuestion(commentCount,content,title,userId);
        }



    }
    public void addUser(String name,String password,String salt) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setSalt(salt);
        //更新密码
        //userDAO.updatePassword(user);
        userDAO.addUser(user);

    }
    public void addQuestion(int commentCount,String content, String title,int userId) {
        Question  question = new Question();
        question.setCommentCount(commentCount);
        question.setContent(content);
        question.setCreatedDate(new Date());
        question.setTitle(title);
        question.setUserId(userId);
        questionDAO.addQuestion(question);
        //打印获取的问题
        //List<Question> a = questionDAO.selectLatestQuestions(1,0,1);
        //System.out.println(a.get(0).getTitle());

    }


}
