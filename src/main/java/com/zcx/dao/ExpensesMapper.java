package com.zcx.dao;

import com.zcx.model.Expenses;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 13:43
 * @Description:
 */
@Repository("ExpensesMapper")
public interface ExpensesMapper {
    void addExpenses(Expenses expenses);
    List<Expenses> findByPage(int begin);
    int getTotal();
}
