package com.biz.lession.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.lession.util.OssUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileuploadController {
    //多文件上传，利用单文件上传修改而来
    @RequestMapping("/filesUpload")
    @ResponseBody
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("file") MultipartFile[] files,
                              HttpServletRequest request) throws IllegalStateException, IOException {
        String imgUrl = "";
        //这里的files 每次都只有一个文件，因为layui上传的时候文件是一个一个上传的
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                imgUrl = OssUtil.uploadImg(file);//上传到OSS，返回上传的图片地址，访问该地址就可以看见图片
            }
        }
        JSONObject res = new JSONObject();//生成一个json对象返回前端
        res.put("code", 0);
        res.put("msg", "");
        res.put("data", imgUrl);//存放图片路径
        return res.toJSONString();
    }
}
