package com.ciel.pocket.user.controller;

import com.ciel.pocket.infrastructure.dto.web.ReturnModel;
import com.ciel.pocket.infrastructure.utils.ReturnUtils;
import com.ciel.pocket.user.domain.Account;
import com.ciel.pocket.user.domain.User;
import com.ciel.pocket.user.infrastructure.exceptions.FriendlyException;
import com.ciel.pocket.user.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

/**
 * @Author Ciel Qian
 * @CreateDate 2018/8/15
 * @Comment
 */
@Api("用户账号相关api")
@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @ApiOperation("创建账号")
    @RequestMapping(method = RequestMethod.POST)
    public ReturnModel<Account> create(@RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new FriendlyException(bindingResult.getFieldError().getDefaultMessage());
        }
        Account account = accountService.create(user);
        logger.info("create account");
        return ReturnUtils.ok("创建成功",account);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ReturnModel delete(@PathVariable("userId") Long userId){
        accountService.delete(userId);
        return ReturnUtils.ok("删除成功");
    }

    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public ReturnModel<Account> current(Principal principal){
        Account account = accountService.findByName(principal.getName());
        return ReturnUtils.ok("查询成功",account);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Account query(@PathVariable("id") Long id){
        return accountService.queryById(id);
    }
}
