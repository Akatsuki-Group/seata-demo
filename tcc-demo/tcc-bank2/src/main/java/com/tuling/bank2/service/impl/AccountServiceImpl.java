package com.tuling.bank2.service.impl;

import com.tuling.bank2.common.TransactionEnum;
import com.tuling.bank2.mapper.AccountMapper;
import com.tuling.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fox
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    AccountMapper accountMapper;
    
    @Override
    @Hmily(confirmMethod="confirmMethod", cancelMethod="cancelMethod")
    public void transferTo(String accountNo, Double amount) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 try begin 开始执行...xid:{}",transId);
    }
    
    /**
     * confirm方法:
     * 	1.confirm幂等校验
     * 	2.正式增加金额
     * @param accountNo
     * @param amount
     */
    @Transactional
    public void confirmMethod(String accountNo, Double amount){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 confirm begin 开始执行...xid:{}",transId);
        if(accountMapper.isExistTransactionLogByType(transId,TransactionEnum.CONFIRM.getValue())>0){
            log.info("bank2 confirm 已经执行，无需重复执行...xid:{}",transId);
            return ;
        }
        //增加金额
        accountMapper.addAccountBalance(accountNo,amount);
        //增加一条confirm日志，用于幂等校验
        accountMapper.addTransactionLog(transId,TransactionEnum.CONFIRM.getValue());
        log.info("bank2 confirm end 结束执行...xid:{}",transId);
    }
    
    
    
    /**
     * @param accountNo
     * @param amount
     */
    public void cancelMethod(String accountNo, Double amount){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank2 cancel begin 开始执行...xid:{}",transId);
        
    }
}
