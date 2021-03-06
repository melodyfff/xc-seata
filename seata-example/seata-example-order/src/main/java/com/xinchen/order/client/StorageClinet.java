package com.xinchen.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@FeignClient(value = "seata-example-storage")
public interface StorageClinet {
  /**
   * 扣减库存
   * @param productId
   * @param count
   * @return
   */
  @GetMapping(value = "/storage/decrease")
  String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
