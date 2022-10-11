package org.example.jta.order.service.impl;

import org.example.jta.entity.TOrder;
import org.example.jta.order.mapper.OrderMapper;
import org.example.jta.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanct
 * @since 1.2
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(TOrder order) {
        return orderMapper.insert(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrder(String id) {
        return orderMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrder(TOrder order) {
        return orderMapper.updateById(order);
    }
}
