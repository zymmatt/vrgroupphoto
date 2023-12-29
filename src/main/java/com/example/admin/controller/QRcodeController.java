package com.example.admin.controller;

import com.example.admin.service.QRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("/QRcode")
public class QRcodeController {
    @Autowired
    QRcodeService QRcodeService;

    @RequestMapping(value="/getRegisterQRCode", method = RequestMethod.GET)
    public void getRegisterQRCode(HttpServletResponse response) throws IOException {
        BufferedImage image = QRcodeService.getQRCode("https://vrstate.inventec.com/vrphoto-admin/#/register",450,450);
        if (image != null) {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
            os.flush();
            os.close();
        }
    }
    // 根据用户ID获取用户的二维码图像
    @RequestMapping(value="/getPhotoQRCode", method = RequestMethod.GET)
    public void getPhotoQRCode(String userid, HttpServletResponse response) throws IOException {
        String QRcodeUrl = String.format("https://vrstate.inventec.com/vrphoto-admin/#/user/%s.png/",userid);
        BufferedImage image = QRcodeService.getQRCode(QRcodeUrl,450,450);
        if (image != null) {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
            os.flush();
            os.close();
        }
    }


}
