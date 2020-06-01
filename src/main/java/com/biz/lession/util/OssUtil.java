package com.biz.lession.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class OssUtil {

    private static String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    private static String ACCESSKEYID = "LTAI4GK6vMLoK3o3RM58VreT" ;
    private static String ACCESSKEYSECRET = "sRSbzhgJ1NjmZVhLJYYa4SCA2MDgZX" ;
    private static String BUCKET = "zhaomingda";

    public static String uploadImg(MultipartFile file) throws IOException {
        String imgFileName = "img/";//保存到阿里云的文件夹名
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssUtil.ENDPOINT;
        String accessKeyId = OssUtil.ACCESSKEYID;
        String accessKeySecret = OssUtil.ACCESSKEYSECRET;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = file.getInputStream();
        String originName = file.getOriginalFilename();//上传的文件名
        String substring = originName.substring(originName.lastIndexOf(".") + 1);
        String name="touxiang"+"."+substring;
        System.out.println("图片名称："+originName);
        String fileName =  imgFileName+name;//保存路径为文件夹+文件名
        ossClient.putObject(OssUtil.BUCKET,fileName, inputStream);
        ossClient.shutdown();
        //前面的内容，阿里云文档上都有
        //上传成功后，获取文件路径+文件名；
        String urlName = URLEncoder.encode(fileName, "UTF-8");//防止中文和特殊字符乱码
        String imgUrl =  "https://" + BUCKET+"."+ENDPOINT+ File.separator  +urlName;//返回的图片路径，oss根目录加上阿里云的ENDPOINT地址，File.separator =\
        return imgUrl;//将图片路径返回
    }


}