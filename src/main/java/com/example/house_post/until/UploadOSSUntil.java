package com.example.house_post.until;

import com.aliyun.oss.OSSClient;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class UploadOSSUntil {

    public static String upload(MultipartFile file){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String yourBucketName = ConstantPropertiesUtil.BUCKETNAME;

        try {
            //1 获取到上传文件 MultipartFile file
            //2 获取上传文件名称，获取上传文件输入流
            String filename = file.getOriginalFilename();
            //在文件名称之前添加uuid值，报纸文件名称不重复
            String uuid = UUID.randomUUID().toString();
            filename = uuid+filename;
            //获取当前日期  2019/04/13
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //拼接文件完整名称
            //  2019/04/13/agdfegafadafe1.txt
            filename = filePath+"/"+filename;
            InputStream in = file.getInputStream();
            //3 把上传文件存储到阿里云oss里面
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            //第一个参数BucketName，第二个参数文件名称，第三个参数文件输入流
            ossClient.putObject(yourBucketName, filename , in);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回上传之后oss存储路径
            //http://edu-teacher1111.oss-cn-beijing.aliyuncs.com/04/13/01.jpg
            String path = "http://"+yourBucketName+"."+endpoint+"/"+filename;

            return path;
        }catch(Exception e) {
            e.printStackTrace();
           return null;
        }

    }
}
