package com.tuling.datasource.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Fox
 */
@Data
public class Order {
    private Integer id;
    
    private String userId;
    /** ååįžå· */
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;
    
    private Integer status;
}
