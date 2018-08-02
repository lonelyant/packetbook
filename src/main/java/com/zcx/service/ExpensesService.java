package com.zcx.service;

import com.zcx.model.Expenses;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 13:44
 * @Description:
 */
public interface ExpensesService {
    boolean addExpenses(Expenses expenses);
    List<Expenses> findByPage(int page);
    int getTotal();
    List<Expenses> findByCateId(String id);
    double calcMoneyByCateId(String id);
}
