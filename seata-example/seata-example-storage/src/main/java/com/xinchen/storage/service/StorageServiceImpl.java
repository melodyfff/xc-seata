package com.xinchen.storage.service;

import com.xinchen.storage.repository.StorageDao;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    private final StorageDao storageDao;

    public StorageServiceImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        String xid = RootContext.getXID();
        LOGGER.info("全局事务 xid： {}", xid);
        LOGGER.info("------->扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("------->扣减库存结束");
    }
}
