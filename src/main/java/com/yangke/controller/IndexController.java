package com.yangke.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangke on 16/8/15.
 */
@Controller
public class IndexController {
    @RequestMapping(path ={"/","/index/{userId}"},method = {RequestMethod.GET})
    @ResponseBody
    public String index(@PathVariable("userId") int userId,
                        @RequestParam(value="type",defaultValue="1",required = false)  int type) {
        return String.format("is %d %d",userId,type);
    }

}
