package com.xinchen.order.repository;

import com.xinchen.order.model.Order;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OrderDao {

  /**
   * 创建订单
   * @param order
   * @return
   */
  void create(Order order);

  /**
   * 修改订单金额
   * @param userId
   * @param money
   */
  void update(@Param("userId") Long userId,@Param("money") BigDecimal money, @Param("status") Integer status);
}
