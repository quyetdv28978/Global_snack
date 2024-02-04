package com.dutn.be_do_an_vat;

import com.dutn.be_do_an_vat.repositoty.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class BeDoAnVatApplication{
    @Autowired
    private IInitDB iInitDB;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
