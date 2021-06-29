package com.xinchen.order.service;

import com.xinchen.order.client.AccountClient;
import com.xinchen.order.client.StorageClinet;
import com.xinchen.order.repository.OrderDao;
import com.xinchen.order.model.Order;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
class OrderServiceImpl implements OrderService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private StorageClinet storageClinet;
  @Autowired
  private AccountClient accountClient;


  /**
   * 创建订单
   *
   * @param order
   * @return 测试结果：
   * 1.添加本地事务：仅仅扣减库存
   * 2.不添加本地事务：创建订单，扣减库存
   */
  @Override
  @GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
  public void create(Order order) {

    String xid = RootContext.getXID();

    //LOGGER.info("------->交易开始");
    //本地方法
    orderDao.create(order);

    //远程方法 扣减库存
    storageClinet.decrease(order.getProductId(), order.getCount());

    //远程方法 扣减账户余额
    //LOGGER.info("------->扣减账户开始order中");
    accountClient.decrease(order.getUserId(), order.getMoney());
    //LOGGER.info("------->扣减账户结束order中");

    //LOGGER.info("------->交易结束");
    LOGGER.info("全局事务 xid： {}", xid);
  }

  /**
   * 修改订单状态
   */
  @Override
  public void update(Long userId, BigDecimal money, Integer status) {
    LOGGER.info("修改订单状态，入参为：userId={},money={},status={}", userId, money, status);
    orderDao.update(userId, money, status);
  }

}
