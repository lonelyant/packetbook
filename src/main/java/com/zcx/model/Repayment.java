package com.zcx.model;

/**
 * @author: Ant
 * @Date: 2018/08/03 17:23
 * @Description:
 */
public class Repayment {
    private String rr_id;
    private String whoAreYou;
    private String returnToWho;
    private String createTime;
    private double repaymentMoney;

    public double getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(double repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public String getRr_id() {
        return rr_id;
    }

    public void setRr_id(String rr_id) {
        this.rr_id = rr_id;
    }

    public String getWhoAreYou() {
        return whoAreYou;
    }

    public void setWhoAreYou(String whoAreYou) {
        this.whoAreYou = whoAreYou;
    }

    public String getReturnToWho() {
        return returnToWho;
    }

    public void setReturnToWho(String returnToWho) {
        this.returnToWho = returnToWho;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Repayment{" +
                "rr_id='" + rr_id + '\'' +
                ", whoAreYou='" + whoAreYou + '\'' +
                ", returnToWho='" + returnToWho + '\'' +
                ", createTime='" + createTime + '\'' +
                ", repaymentMoney=" + repaymentMoney +
                '}';
    }
}
