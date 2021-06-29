package com.xinchen.account.controller;

import com.xinchen.account.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountServiceImpl;

    public AccountController(AccountService accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     * @return String
     */
    @RequestMapping("decrease")
    public String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountServiceImpl.decrease(userId,money);
        return "Account decrease success";
    }
}
