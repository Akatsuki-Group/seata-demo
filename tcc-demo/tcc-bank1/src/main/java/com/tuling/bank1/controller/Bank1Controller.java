package com.tuling.bank1.controller;

import com.tuling.bank1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank1")
public class Bank1Controller {
    @Autowired
    AccountService accountService;
    
    /**
     * 转账接口
     * @param from_accountNo  转出账户
     * @param to_accountNo   转入账户
     * @param amount        转账的金额
     * @return
     */
    @RequestMapping("/transfer")
    public Boolean transfer(@RequestParam("from_accountNo") String from_accountNo,
            @RequestParam("to_accountNo") String to_accountNo,
            @RequestParam("amount") Double amount) {
        accountService.transfer(from_accountNo,to_accountNo, amount);
        return true;
    }

}