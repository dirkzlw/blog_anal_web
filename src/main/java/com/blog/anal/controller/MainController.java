package com.blog.anal.controller;

import com.blog.anal.domain.User;
import com.blog.anal.service.BlogService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ranger
 * @create 2020-01-08 15:18
 */
@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    /**
     * 跳转至主页
     * @return
     */
    @GetMapping("/")
    public String toI(){
        return "index";
    }

    /**
     * 接收上传的文件
     * @param file
     * @return
     */
    @PostMapping("/fs/update")
    @ResponseBody
    public User updateFile(MultipartFile file) {

        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        User user;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(file.getInputStream()));
            user = blogService.anal(reader,reader2);
            System.out.println("user = " + user);
        } catch (IOException e) {
            throw new RuntimeException("MultipartFile获取输入流异常！");
        }

        return user;
    }
}
