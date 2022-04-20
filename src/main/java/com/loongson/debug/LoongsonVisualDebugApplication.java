package com.loongson.debug;

import com.loongson.debug.exception.UploadAddressFormatException;
import com.loongson.debug.grpc.OnlineDebugServer;
import com.loongson.debug.helper.UploadAddressFactory;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class LoongsonVisualDebugApplication {

    public static void main(String[] args) throws Exception {
        //支持命令行输入文件上传地址，以便在打包部署时可以更改文件目录
        UploadAddressFactory instance = UploadAddressFactory.getInstance();
        try {
            if (args.length == 0) {
                instance.setUploadAddress("/home/liuxp/文档/毕设/后端/demo/upload");
            } else {
                String[] split = args[0].split("=");
                if (split[0].equals("upload") && split[1] != null) {
                    instance.setUploadAddress(split[1]);
                } else {
                    throw new UploadAddressFormatException("Wrong format of upload address. Using 'upload=/home/...' instead");
                }
            }
        } catch (UploadAddressFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Update upload folder, current folder: " + instance.getUploadAddress());
        File folder = new File(instance.getUploadAddress());

        if (!folder.exists()) {
            System.out.println("Upload folder doesn't exist,trying to create new folder.");
            boolean mkdirs = folder.mkdirs();
            if (mkdirs) {
                System.out.println("Upload folder created.");
            } else {
                System.out.println("Creating upload folder failed.");
            }
        } else {
            System.out.println("Upload folder detected.");
        }
        SpringApplication.run(LoongsonVisualDebugApplication.class, args);
        Server server = ServerBuilder.forPort(50071)
                .addService(new OnlineDebugServer())
                .build();
        server.start();
    }

}
