package org.example.jta.order.service;

import org.example.jta.entity.TOrder;

/**
 * @author yuanct
 * @since 1.2
 */
public interface OrderService {
    int insertOrder(TOrder order);
    int deleteOrder(String id);
    int updateOrder(TOrder order);
}
