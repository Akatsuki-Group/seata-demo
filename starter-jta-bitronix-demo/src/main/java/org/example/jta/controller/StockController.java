package org.example.jta.controller;

import org.example.jta.entity.TStock;
import org.example.jta.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanct
 * @since 1.2
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/insert")
    public String insertStock(@RequestBody TStock stock) {
        stockService.insertStock(stock);
        return "success";
    }

    @DeleteMapping("/delete")
    public String deleteStock(@RequestParam String id) {
        stockService.deleteStock(id);
        return "success";
    }

    @PutMapping("/update")
    public String updateStock(@RequestBody TStock stock) {
        stockService.updateStock(stock);
        return "success";
    }
}
