package com.tuling.datasource.entity;

import lombok.Data;

/**
 * @author Fox
 */
@Data
public class Order {
    private Long id;
    
    private String userId;
    /** ååįžå· */
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;
    
    private Integer status;
}
