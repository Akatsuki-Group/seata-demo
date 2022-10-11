package org.example.jta.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author yuanct
 * @since 1.2
 */
@TableName("t_stock")
public class TStock {
    /**
     * 库存表主键
     */
    @TableId
    private String id;

    /**
     * 库存名称
     */
    private String name;

    /**
     * 获取库存表主键
     *
     * @return id - 库存表主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置库存表主键
     *
     * @param id 库存表主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取库存名称
     *
     * @return name - 库存名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置库存名称
     *
     * @param name 库存名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
