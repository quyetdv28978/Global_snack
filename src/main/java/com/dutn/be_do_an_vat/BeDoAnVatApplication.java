package com.dutn.be_do_an_vat;

import com.dutn.be_do_an_vat.config.MailConfi;
import com.dutn.be_do_an_vat.repositoty.*;
import com.dutn.be_do_an_vat.utility.ConstMail;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootApplication
public class BeDoAnVatApplication implements CommandLineRunner {
    @Autowired
    private IInitDB iInitDB;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private MailConfi mailConfi;

    @Autowired
    private ILoSanPhamRes loSanPhamRes;

    public static void main(String[] args) {
        SpringApplication.run(BeDoAnVatApplication.class, args);
    }

    @PostConstruct
    public void init() {
        if (iInitDB.findAll().isEmpty()) {
            Resource resource = new ClassPathResource("data.sql");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim(); // Xóa khoảng trắng đầu/cuối
                    if (!line.isEmpty()) { // Bỏ qua các dòng trống
                        jdbcTemplate.execute(line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read SQL file", e);
            }
        }
    }

    @Override
    public void run(String... args) {
//        List<Object[]> productsOut7Day = loSanPhamRes.showOutDate7Day();
//        if (productsOut7Day.isEmpty()) {
//            List<Object[]> productsOutDay = loSanPhamRes.showDateMin();
//            ConstMail.time = (long) ((int)productsOut7Day.get(0)[0] - 7) * 24 * 3600 * 1000;
//            ConstMail.threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//            ConstMail.threadPoolTaskScheduler.initialize();
//            mailConfi.schedledTaskAutoMail(ConstMail.time, ConstMail.threadPoolTaskScheduler,
//                    null, productsOutDay);
//        } else {
//            ConstMail.time = (long) ((int)productsOut7Day.get(0)[0]) * 24 * 3600 * 1000;
//            ConstMail.threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//            ConstMail.threadPoolTaskScheduler.setThreadNamePrefix("quyet");
//            ConstMail.threadPoolTaskScheduler.initialize();
//            mailConfi.schedledTaskAutoMail(ConstMail.time, ConstMail.threadPoolTaskScheduler,
//                    null, productsOut7Day);
//        }
    }
}
