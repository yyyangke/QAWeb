package com.yangke.controller;

import com.sun.deploy.net.HttpResponse;
import com.yangke.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yangke on 16/8/18.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;


    @RequestMapping(value = {"/reg/"},method = RequestMethod.POST)
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "next",required = false) String next,
                      HttpServletResponse response) {

            try {
                Map<String, String> regStatus = userService.register(username, password);
                if (regStatus.containsKey("ticket")) {
                    Cookie cookie = new Cookie("ticket",regStatus.get("ticket"));
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    if(!StringUtils.isBlank(next)) {
                        return "redirect:"+next;
                    }
                    return "redirect:/";
                } else {
                    model.addAttribute("msg", regStatus.get("msg"));
                    return "login";
                }

            } catch(Exception e) {
                System.out.println(e.getMessage());
                return "login";
            }
    }




    @RequestMapping(value = {"/login/"},method = RequestMethod.POST)
    public String login(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                        @RequestParam(value = "next",required = false) String next,
                        @RequestParam(value = "rememberme",defaultValue = "false") boolean rememberme,
                      HttpServletResponse response) {

        try {
            Map<String, String> loginStatus = userService.login(username, password);
            if (loginStatus.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",loginStatus.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                if(!StringUtils.isBlank(next)) {
                    return "redirect:"+next;
                }
                return "redirect:/";
            } else {
                model.addAttribute("msg", loginStatus.get("msg"));
                return "login";
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "login";
        }

    }



    @RequestMapping(value = {"/reglogin"},method = RequestMethod.GET)
    public String reglogin(Model model
            ,@RequestParam(value = "next",required = false) String next
    ) {
        model.addAttribute("next",next);
        return "login";

    }

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/";
    }




}
