package org.example.jta.service;

import org.example.jta.entity.TOrder;
import org.example.jta.entity.TStock;
import org.example.jta.order.service.OrderService;
import org.example.jta.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuanct
 * @since 1.2
 */
@Service
public class JtaTestService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StockService stockService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> test(){
        TOrder order = new TOrder();
        order.setId(UUID.randomUUID().toString());
        order.setName("order0001");
        orderService.insertOrder(order);

        TStock stock = new TStock();
        stock.setId(UUID.randomUUID().toString());
        stock.setName("stock0001");
        stockService.insertStock(stock);
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("state","success");
        resultMap.put("message","分布式事务同步成功");
        return resultMap;
    }

    @Transactional(transactionManager = "tm", propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
    public Map<String,Object> test01(){
        TOrder order = new TOrder();
        order.setId(UUID.randomUUID().toString());
        order.setName("order0002");
        orderService.insertOrder(order);

        TStock stock = new TStock();
        stock.setId(UUID.randomUUID().toString());
        stock.setName("stock0002");
        stockService.insertStock(stock);

        System.out.println(1/0);
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("state","success");
        resultMap.put("message","分布式事务同步成功");
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> test02() {

        TOrder order = new TOrder();
        order.setId(UUID.randomUUID().toString());
        order.setName("order0003");
        orderService.insertOrder(order);

        TStock stock = new TStock();
        stock.setId(UUID.randomUUID().toString());
        stock.setName("stock0003");
        stockService.insertStock(stock);

        System.out.println(1/0);
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("state","success");
        resultMap.put("message","分布式事务同步成功");
        return resultMap;
    }
}
