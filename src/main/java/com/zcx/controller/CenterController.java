package com.zcx.controller;

import com.zcx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:02
 * @Description:
 */
@Controller
public class CenterController {

    @Autowired
    private TestService testService;

    @RequestMapping(value="/test")
    public String test(HttpSession session){
        session.setAttribute("userList",testService.findAllUser());
        return "hello";
    }

    @RequestMapping(value = "/")
    public String index(HttpSession session){
        String[] bookkeeping_title = {"都这么穷了还花钱？","越花越穷！","我劝你看看余额","看看上面你的负债！","死鬼就知道花钱~"};
        session.setAttribute("nowtime",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        session.setAttribute("bookkeeping_title",bookkeeping_title[new Random().nextInt(bookkeeping_title.length)]);
        return "index";
    }
}
