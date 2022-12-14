package com.tuling.bank1.common;

/**
 * @author Fox
 */
public enum TransactionEnum {
    
    TRY(1),
    CONFIRM(2),
    CANCEL(3);
    
    private final int value;
    
    TransactionEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
