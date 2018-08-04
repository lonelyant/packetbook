package com.zcx.service;

import com.zcx.model.Expenses;
import com.zcx.model.Repayment;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 13:44
 * @Description:
 */
public interface RepaymentService {
    boolean addRepayment(Repayment repayment);
    List<Repayment> findByPage(int page);

}
