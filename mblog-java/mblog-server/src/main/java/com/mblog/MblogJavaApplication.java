package com.mblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MblogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MblogJavaApplication.class, args);
        System.out.println("\n"+"⠀⠀⠀ ⠰⢷⢿⠄\n" +
                "⠀⠀⠀⠀⠀⣼⣷⣄\n" +
                "⠀⠀⣤⣿⣇⣿⣿⣧⣿⡄\n" +
                "⢴⠾⠋⠀⠀⠻⣿⣷⣿⣿⡀\n" +
                "\uD83C\uDFC0 ⠀⢀⣿⣿⡿⢿⠈⣿\n" +
                "⠀⠀⠀⢠⣿⡿⠁⠀⡊⠀⠙\n" +
                "⠀⠀⠀⢿⣿⠀⠀⠹⣿\n" +
                "⠀⠀⠀⠀⠹⣷⡀⠀⣿⡄\n" +
                "⠀⠀⠀⠀⣀⣼⣿⠀⢈⣧"+"\n唱、跳、Rap、篮球\n服务运行成功！！！");
    }

}
