package com.example.demo;

import com.example.demo.core.Admin.model.response.AdminLoSanPhamOutDate;
import com.example.demo.reponsitory.ILoSanPhamRes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        List<AdminLoSanPhamOutDate> spOutDate=context.getBean(ILoSanPhamRes.class).timeOutDate();
        String [] tenLo = Arrays.copyOf(spOutDate.stream().map(AdminLoSanPhamOutDate::getTenLo).toArray(),
                spOutDate.stream().map(AdminLoSanPhamOutDate::getTenLo).toArray().length, String[].class);
        System.out.println(String.join(",", tenLo));

    }
}