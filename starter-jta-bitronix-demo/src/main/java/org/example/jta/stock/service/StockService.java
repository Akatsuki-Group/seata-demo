package org.example.jta.stock.service;

import org.example.jta.entity.TStock;

/**
 * @author yuanct
 * @since 1.2
 */
public interface StockService {
    int insertStock(TStock stock);
    int deleteStock(String id);
    int updateStock(TStock stock);
}
