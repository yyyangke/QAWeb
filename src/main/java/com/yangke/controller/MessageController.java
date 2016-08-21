package com.yangke.controller;

import com.yangke.model.HostHolder;
import com.yangke.model.Message;
import com.yangke.model.User;
import com.yangke.model.ViewObject;
import com.yangke.service.MessageService;
import com.yangke.service.UserService;
import com.yangke.util.GetJSONString;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangke on 16/8/21.
 */
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(value = "/msg/list",method = {RequestMethod.GET})
    public String getConversationList(Model model) {
        try {
            if (hostHolder.getUser() == null) {

            }
            int localUserId = hostHolder.getUser().getId();
            List<ViewObject> conversations = new ArrayList<ViewObject>();
            List<Message> conversationList = messageService.getConversationList(localUserId, 1, 10);
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", msg);
                int targetId = msg.getFromId() == localUserId ? msg.getToId() : msg.getFromId();
                User user = userService.getUser(targetId);
                vo.set("user", user);
                vo.set("unread", messageService.getConvesationUnreadCount(localUserId, msg.getConversationId()));
                conversations.add(vo);
            }
            model.addAttribute("conversations", conversations);
        }catch(Exception e) {
            System.out.println("访问失败"+e.getMessage());
        }
        return "letter";
    }

    @RequestMapping(value = "/msg/detail",method = RequestMethod.GET)
    public String getConversationDetail(Model model,@Param("conversationId") String conversationId) {
        try{
        List<Message> messageList = messageService.getConversationDetail(conversationId,1,10);
        List<ViewObject> messages = new ArrayList<ViewObject>();
        for(Message message :messageList) {
            ViewObject vo = new ViewObject();
            vo.set("message",message);
            vo.set("user",userService.getUser(message.getFromId()));
            messages.add(vo);
        }
        model.addAttribute("messages",messages);
    }catch(Exception e) {
        System.out.println("访问失败"+e.getMessage());
    }
        return "letterDetail";

    }

    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            if (hostHolder.getUser() == null) {
                return GetJSONString.getJSONString(999, "未登录");
            }
            User user = userService.selectByName(toName);
            if (user == null) {
                return GetJSONString.getJSONString(1, "用户不存在");
            }

            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(hostHolder.getUser().getId());
            msg.setToId(user.getId());
            msg.setHasRead(0);
            msg.setCreatedDate(new Date());
            msg.setConversationId(hostHolder.getUser().getId(),user.getId());
            messageService.addMessage(msg);
            return GetJSONString.getJSONString(0);
        } catch (Exception e) {
            System.out.println("增加站内信失败" + e.getMessage());
            return GetJSONString.getJSONString(1, "插入站内信失败");
        }
    }
}
