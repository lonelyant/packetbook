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
import java.text.DateFormat;
import java.text.ParseException;
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


    @RequestMapping(value = "/test")
    public String test(HttpSession session) {
        session.setAttribute("userList", userService.findAllUser());
        return "hello";
    }

    @RequestMapping(value = "/")
    public String index(HttpSession session) {
        // 记账模态框随机标题
        String[] bookkeeping_title = {"都这么穷了还花钱？", "越花越穷！", "我劝你看看余额", "看看上面你的负债！", "死鬼就知道花钱~"};
        session.setAttribute("bookkeeping_title", bookkeeping_title[new Random().nextInt(bookkeeping_title.length)]);
        // 底部时间显示
        session.setAttribute("nowtime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        // 记账分类
        List<Category> categoryList = categoryService.findCategoryList();
        session.setAttribute("categoryList", categoryList);
        // 得到所有用户
        List<User> userList = userService.findAllUser();
        session.setAttribute("userList", userList);
        // 得到消费记录列表
        selectUsers(null, session);
        return "index";
    }

    /**
     * 记账
     *
     * @param session
     * @param expenses
     * @return
     */
    @RequestMapping(value = "/bookkeeping")
    public String bookkeeping(HttpSession session, Expenses expenses) {
        boolean isFromMethod = true;
        String er_id = UUID.randomUUID().toString();
        expenses.setEr_id(er_id);
        boolean b = expensesService.addExpenses(expenses);
        if (b) {
            session.setAttribute("isSuccess", "success");
        } else {
            session.setAttribute("isSuccess", "fail");
        }

        return "redirect:/";
    }

    /**
     * 前端ajax请求此方法移除消息提示框状态
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/removeIsSuccess")
    public @ResponseBody
    String removeIsSuccess(HttpSession session) {
        //System.out.println("+++++++++");
        session.removeAttribute("isSuccess");
        return "";
    }

    /**
     * 记录列表显示 TODO 逻辑处理部分移动到Service层中
     *
     * @param page
     * @return
     */
    @RequestMapping("/getExpensesList")
    public @ResponseBody
    Map<String, Object> selectUsers(Integer page, HttpSession session) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (page == null) page = 1;//页面第一次加载，默认为首页
        List<Expenses> expensesList = expensesService.findByPage(page);
        for (Expenses expens : expensesList) {
            expens.setCategory(categoryService.findCateById(expens.getCategory()).getCategory());
            expens.setFromWho(userService.findById(expens.getFromWho()).getUsername());
            try {
                expens.setCreateTime(format.format(format.parse(expens.getCreateTime())));
            } catch (ParseException e) {
                expens.setCreateTime("time parse error");
            }
        }
        session.setAttribute("expensesList", expensesList);
        Integer total = expensesService.getTotal();//总记录数
        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println(exString);
        map.put("expenses", expensesList);
        map.put("total", total);
        return map;
    }

    /**
     * 跳转统计页面
     *
     * @return
     */
    @RequestMapping("/show")
    public String show() {
        return "show";
    }

    /**
     * 消费分类占比显示数据 TODO 逻辑处理部分移动到Service层中
     *
     * @return
     */
    @RequestMapping("/show/expenses")
    public @ResponseBody
    List<Map> show_expenses() {
        Map<String, String> map = null;
        List<Map> result = new ArrayList<>();
        List<Category> categoryList = categoryService.findCategoryList();
        for (Category category : categoryList) {
            double money = expensesService.calcMoneyByCateId(category.getCategory_id());
            map = new HashMap<>();
            map.put("name", category.getCategory());
            map.put("value", Double.toString(money));
            result.add(map);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 个人支出详情数据 TODO 逻辑处理部分移动到Service层中
     * @return
     */
    @RequestMapping("/show/personDetail")
    public @ResponseBody Map<String, Object> show_personDetail() {
        Map<String,Object> map = new HashMap<>();
        // 组建消费分类数据
        List<Category> categoryList = categoryService.findCategoryList();
        List<String> stringList1 = new ArrayList<>();
        for (Category category : categoryList) {
            stringList1.add(category.getCategory());
        }
        map.put("categoryList",stringList1);
        // 组建用户列表数据
        List<String> stringList2 = new ArrayList<>();
        List<User> users = userService.findAllUser();
        for (User user : users) {
            stringList2.add(user.getUsername());
        }
        map.put("userList",stringList2);
        // 组建主体数据
        List<Map> mapList = new ArrayList<>();
        Map<String,Object> dataMap = null;
        for (Category category : categoryList) {
            dataMap = new HashMap<>();
            dataMap.put("name",category.getCategory());
            dataMap.put("type","bar");
            dataMap.put("stack","总量");
            List<Double> list =  new ArrayList<>();
            for (User user : users) {
                double money = expensesService.calcMoneyByCateIdAndFromWho(category.getCategory_id(), user.getUser_id());
                list.add(money);
            }
            dataMap.put("data",list);
            mapList.add(dataMap);
        }
        map.put("data",mapList);
        System.out.println(map);
        return map;
    }
}
