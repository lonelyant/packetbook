package com.zcx.service.impl;

import com.zcx.dao.RepaymentMapper;
import com.zcx.model.Repayment;
import com.zcx.model.User;
import com.zcx.service.RepaymentService;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/03 17:31
 * @Description:
 */
@Service("RepaymentService")
public class RepaymentServiceImpl implements RepaymentService {
    @Autowired
    private RepaymentMapper repaymentMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public boolean addRepayment(Repayment repayment) {
        try {
            repaymentMapper.addRepayment(repayment);
            List<User> userList = userService.findAllUser();
            double total = userList.size();
            //System.out.println(total);
            int i = 0;
            for (User user : userList) {
                if (user.getUser_id().equals(repayment.getWhoAreYou())) {
                    user.setRich(user.getRich()+repayment.getRepaymentMoney());
                    userService.updateUser(user);
                    i++;
                } else if(user.getUser_id().equals(repayment.getReturnToWho())) {
                    user.setRich(user.getRich()-repayment.getRepaymentMoney());
                    userService.updateUser(user);
                    i++;
                }
            }
            if(i != 2)throw new Exception("还款数据错误，操作失败！");
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Repayment> findByPage(int page) {
        page = (page-1) * 10;
        return repaymentMapper.findByPage(page);
    }
}
