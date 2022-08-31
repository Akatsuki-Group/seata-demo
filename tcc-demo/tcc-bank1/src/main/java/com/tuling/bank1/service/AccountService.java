package com.tuling.bank1.service;

public interface AccountService {
    
    /**
     * 转账
     * @param from_accountNo  转出账户
     * @param to_accountNo   转入账户
     * @param amount   转账的金额
     */
    public void transfer(String from_accountNo,String to_accountNo, Double amount);
}
