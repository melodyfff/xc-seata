package com.xinchen.account.service;

import com.xinchen.account.client.OrderClient;
import com.xinchen.account.repository.AccountDao;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountDao accountDao;
    private final OrderClient orderClient;

    public AccountServiceImpl(AccountDao accountDao, OrderClient orderClient) {
        this.accountDao = accountDao;
        this.orderClient = orderClient;
    }

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
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
