package com.zcx.dao;

import com.zcx.model.Expenses;
import com.zcx.model.Repayment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 13:43
 * @Description:
 */
@Repository("RepaymentMapper")
public interface RepaymentMapper {
    void addRepayment(Repayment repayment);
    List<Repayment> findByPage(int begin);
}
