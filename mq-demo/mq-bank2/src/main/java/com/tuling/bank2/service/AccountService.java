package com.tuling.bank2.service;

import com.tuling.bank2.entity.AccountChangeEvent;

/**
 * @author Fox
 */
public interface AccountService {
    
    /**
     * 更新账户，增加金额
     */
    public void addAccountBalance(AccountChangeEvent accountChangeEvent);
    
}
