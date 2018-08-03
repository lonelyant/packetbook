package com.zcx.service.impl;

import com.zcx.dao.ExpensesMapper;
import com.zcx.dao.UserDao;
import com.zcx.model.Expenses;
import com.zcx.model.User;
import com.zcx.service.ExpensesService;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 13:45
 * @Description:
 */
@Service("ExpensesService")
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    private ExpensesMapper expensesMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public boolean addExpenses(Expenses expenses) {
        try {
            expensesMapper.addExpenses(expenses);
            List<User> userList = userService.findAllUser();
            double total = userList.size();
            //System.out.println(total);
            for (User user : userList) {
                if (user.getUser_id().equals(expenses.getFromWho())) {
                    user.setRich(Math.round((user.getRich() + (expenses.getMoney() * (total - 1) / total)) * 100) * 0.01);
                    userService.updateUser(user);
                } else {
                    user.setRich(Math.round((user.getRich() - (expenses.getMoney() * 1 / total)) * 100) * 0.01);
                    userService.updateUser(user);
                }
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Expenses> findByPage(int page) {
        int begin = (page-1)*10;
        return expensesMapper.findByPage(begin);
    }

    @Override
    public int getTotal() {
        return expensesMapper.getTotal();
    }

    @Override
    public List<Expenses> findByCateId(String id) {
        return expensesMapper.findByCateId(id);
    }

    @Override
    public double calcMoneyByCateId(String id) {
        List<Expenses> expensesList = findByCateId(id);
        double total = 0.0;
        for (Expenses expenses : expensesList) {
            total += expenses.getMoney();
        }
        return total;
    }

    @Override
    public List<Expenses> findByCateIdAndFromWho(String id, String fromWho) {
        return expensesMapper.findByCateIdAndFromWho(id, fromWho);
    }

    @Override
    public double calcMoneyByCateIdAndFromWho(String id, String fromWho) {
        List<Expenses> expensesList = findByCateIdAndFromWho(id, fromWho);
        double total = 0.0;
        for (Expenses expenses : expensesList) {
            total += expenses.getMoney();
        }
        return total;
    }
}
