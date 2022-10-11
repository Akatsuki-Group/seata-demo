package org.example.jta.stock.service.impl;

import org.example.jta.entity.TStock;
import org.example.jta.stock.mapper.StockMapper;
import org.example.jta.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanct
 * @since 1.2
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStock(TStock stock) {
        return stockMapper.insert(stock);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStock(String id) {
        return stockMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStock(TStock stock) {
        return stockMapper.updateById(stock);
    }
}
