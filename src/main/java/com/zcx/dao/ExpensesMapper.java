package com.zcx.dao;

import com.zcx.model.Expenses;
import org.apache.ibatis.annotations.Param;
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
    List<Expenses> findByCateId(@Param("id") String id);
    List<Expenses> findByCateIdAndFromWho(@Param("id") String id,@Param("fromWho") String fromWho);
}
