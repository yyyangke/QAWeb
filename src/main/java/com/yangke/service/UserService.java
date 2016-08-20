package com.yangke.service;

import com.yangke.dao.LoginTicketDAO;
import com.yangke.dao.UserDAO;
import com.yangke.model.LoginTicket;
import com.yangke.model.User;
import com.yangke.util.MD5;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yangke on 2016/8/15.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public Map<String,String> login(String username,String password) {
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isBlank(username)) {
            map.put("msg","用户名为空");
            return map;
        }
        if(StringUtils.isBlank(password)) {
            map.put("msg","密码为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if(user==null) {
            map.put("msg","用户名不存在");
            return map;
        }

        if(!MD5.GetMD5Code(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg","密码错误");
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }

    public Map<String,String> register(String username, String password) {
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isBlank(username)) {
            map.put("msg","用户名为空");
            return map;
        }
        if(StringUtils.isBlank(password)) {
            map.put("msg","密码为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if(user!=null) {
            map.put("msg","用户名存在");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl("../images/res0cf21546298ad1ed3cb64be61d822c27_m.jpg");
        user.setPassword(MD5.GetMD5Code(password+user.getSalt()));

        userDAO.addUser(user);
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }
    public User getUser(int id) {
        return userDAO.selectById(id);
    }

    public String addLoginTicket(int userId) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();
        now.setTime(1000*3600*24+now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(loginTicket);
        return loginTicket.getTicket();

    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket,1);
    }

}
