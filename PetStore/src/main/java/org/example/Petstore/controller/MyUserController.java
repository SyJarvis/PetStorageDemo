package org.example.Petstore.controller;

import cn.hutool.core.lang.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.Petstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户操作", tags = {"用户操作api"})
@RestController
@RequestMapping("user")
public class MyUserController {

    @Autowired
    UserService us = new UserService();

    @ApiOperation(value = "智能合约 register接口", notes = "智能合约 register接口")
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public Dict register(@RequestParam("address") String address) {
        System.out.println("-------------");
        System.out.println("调用register");
        return us.register(address);
    }

    @ApiOperation(value = "智能合约 login接口", notes = "智能合约 login接口")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Dict login(@RequestParam("address") String address) {
        System.out.println("-------------");
        System.out.println("调用login");
        return us.login(address);
    }
}
