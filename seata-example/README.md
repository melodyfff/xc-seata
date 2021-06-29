# 简单seata demo

### Reference Documentation

[Seata-基本配置-git](https://github.com/seata/seata/tree/1.3.0/script)

[Seata-基本配置-gitee](https://gitee.com/mirrors/Seata/tree/v1.3.0/script)

[seata/seata-samples](https://github.com/seata/seata-samples/tree/master/springcloud-nacos-seata)

[Springboot-Notebook/springboot-seata-transaction](https://gitee.com/melodyfff/Springboot-Notebook/tree/master/springboot-seata-transaction)

[看了 5种分布式事务方案，我司最终选择了 Seata，真香！](https://zhuanlan.zhihu.com/p/315164700)

### Guides

#### 初始化
先执行init里面的sql

nacos-server(1.4.2): https://nacos.io/zh-cn/docs/quick-start.html

seata-server(1.4.2): http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html

#### 启动

**SeataOrder、SeataAccount、SeataStorage**

#### 模拟异常
**AccountServiceImpl**中
```java
   @Override
public void decrease(Long userId, BigDecimal money) {

        String xid = RootContext.getXID();
        LOGGER.info("全局事务 xid： {}", xid);

        LOGGER.info("------->扣减账户开始account中");
        //模拟超时异常，全局事务回滚
        try {
        Thread.sleep(30*1000);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        accountDao.decrease(userId,money);
        LOGGER.info("------->扣减账户结束account中");

        //修改订单状态，此调用会导致调用成环
        LOGGER.info("修改订单状态开始");
        String mes = orderClient.update(userId, money.multiply(new BigDecimal("0.09")),0);
        LOGGER.info("修改订单状态结束：{}",mes);
        }
}
```

#### 访问
```bash
curl http://localhost:8180/order/create?userId=1&productId=1&count=10&money=100
```
