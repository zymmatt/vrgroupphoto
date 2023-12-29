package com.example.admin.controller;

import com.example.admin.entity.User.*;
import com.example.admin.entity.Response.ResponseObject;
import com.example.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 管理员平台获取所有用户
    @RequestMapping(value="/findAll", method= RequestMethod.GET)
    public ResponseObject findAll() {
        return ResponseObject.success(userService.findAll());
    }

    // 获取所有正在排队的
    @RequestMapping(value="/findAllWaiting", method= RequestMethod.GET)
    public ResponseObject findAllWaiting() {
        return ResponseObject.success(userService.findAllWaiting());
    }

    // 叫号,下一个人
    @RequestMapping(value="/getNext", method= RequestMethod.GET)
    public ResponseObject getNext() {
        return ResponseObject.success(userService.getNext());
    }

    // 注册新用户
    @RequestMapping(value="/createUser", method= RequestMethod.POST)
    public ResponseObject createUser(@RequestBody User user) {
        int id = userService.createUser(user);
        if (id>0){
            return ResponseObject.success(id);
        }
        else{
            return ResponseObject.fail("duplicated");
        }
    }

    // 管理员平台删除用户信息
    @RequestMapping(value="/deleteUser", method= RequestMethod.DELETE)
    public ResponseObject deleteUser(int id) {
        userService.deleteUser(id);
        return ResponseObject.success("成功删除新用户");
    }

    // 更新用户信息
    @RequestMapping(value="/updateUser", method= RequestMethod.PUT)
    public ResponseObject updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseObject.success("成功更新新用户");
    }

    // 某个用户拍完了提交资料
    @RequestMapping(value="/submitPhoto", method = RequestMethod.POST)
    public ResponseObject submitPhoto(@RequestBody MultipartFile file, String userid) throws IOException {
        userService.submitPhoto(file, userid);
        return ResponseObject.success("提交成功");
    }

}
