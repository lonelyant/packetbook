package com.zcx.controller;

import com.zcx.model.Category;
import com.zcx.model.Expenses;
import com.zcx.model.User;
import com.zcx.service.CategoryService;
import com.zcx.service.ExpensesService;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:02
 * @Description:
 */
@Controller
public class CenterController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExpensesService expensesService;


    @RequestMapping(value="/test")
    public String test(HttpSession session){
        session.setAttribute("userList", userService.findAllUser());
        return "hello";
    }

    @RequestMapping(value = "/")
    public String index(HttpSession session){
        // 记账模态框随机标题
        String[] bookkeeping_title = {"都这么穷了还花钱？","越花越穷！","我劝你看看余额","看看上面你的负债！","死鬼就知道花钱~"};
        session.setAttribute("bookkeeping_title",bookkeeping_title[new Random().nextInt(bookkeeping_title.length)]);
        // 底部时间显示
        session.setAttribute("nowtime",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        // 记账分类
        List<Category> categoryList = categoryService.findCategoryList();
        session.setAttribute("categoryList",categoryList);
        // 得到所有用户
        List<User> userList = userService.findAllUser();
        session.setAttribute("userList",userList);
        return "index";
    }


    @RequestMapping(value = "/bookkeeping")
    public  String bookkeeping(HttpSession session, Expenses expenses){
        boolean isFromMethod = true;
        String er_id = UUID.randomUUID().toString();
        expenses.setEr_id(er_id);
        boolean b = expensesService.addExpenses(expenses);
        if(b){
            session.setAttribute("isSuccess","success");
        }else{
            session.setAttribute("isSuccess","fail");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/removeIsSuccess")
    public @ResponseBody String removeIsSuccess(HttpSession session){
        //System.out.println("+++++++++");
        session.removeAttribute("isSuccess");
        return "";
    }

    /**
     * 分页
     * @param page
     * @return
     */
    @RequestMapping("getExpensesList")
    public @ResponseBody Map<String,Object> selectUsers(Integer page){
        if(page==null)page=1;//页面第一次加载，默认为首页
        List<Expenses> expenses = expensesService.findByPage(page);
        List<String> exString = new ArrayList<>();
        for (Expenses expens : expenses) {
            exString.add(" - <span style='color:red'>"+expens.getFromWho()+"</span> 在 " +expens.getCategory()+" 上花了 "+"<span style='color:green'>"+expens.getMoney()+"<br>");
        }
        Integer total = expensesService.getTotal();//总记录数
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(exString);
        map.put("expenses",exString);
        map.put("total",total);
        return map;
    }
}
