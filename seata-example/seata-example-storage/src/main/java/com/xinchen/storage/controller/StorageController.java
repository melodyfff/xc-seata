package com.xinchen.storage.controller;

import com.xinchen.storage.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageServiceImpl;

    public StorageController(StorageService storageServiceImpl) {
        this.storageServiceImpl = storageServiceImpl;
    }

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @RequestMapping("decrease")
    public String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageServiceImpl.decrease(productId,count);
        return "Decrease storage success";
    }
}
