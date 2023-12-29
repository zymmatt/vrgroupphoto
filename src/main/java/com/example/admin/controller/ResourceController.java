package com.example.admin.controller;


import com.example.admin.entity.Response.ResponseObject;
import com.example.admin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @RequestMapping(value="/getVRphoto", method = RequestMethod.GET)
    public ResponseObject getVRphoto(String name) {
        return ResponseObject.success(resourceService.getVRphoto(name));
    }

    // 上传抠背之后的原始图像
    @RequestMapping(value="/submitCleanPhoto", method = RequestMethod.POST)
    public ResponseObject submitCleanPhoto(@RequestBody MultipartFile file, String userid) throws IOException {
        resourceService.submitCleanPhoto(file, userid);
        return ResponseObject.success("提交原图成功");
    }

    @RequestMapping(value="/downloadPhoto", method = RequestMethod.GET)
    public String downloadPhoto(int id){
        return resourceService.downloadPhoto(id);
    }
}
