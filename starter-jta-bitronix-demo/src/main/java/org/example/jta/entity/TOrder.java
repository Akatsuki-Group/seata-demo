package org.example.jta.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author yuanct
 * @since 1.2
 */
@TableName("t_order")
public class TOrder {
    /**
     * 订单表主键
     */
    @TableId
    private String id;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 获取订单表主键
     *
     * @return id - 订单表主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置订单表主键
     *
     * @param id 订单表主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取订单名称
     *
     * @return name - 订单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置订单名称
     *
     * @param name 订单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
