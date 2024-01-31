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
import java.io.Reader;

@SpringBootApplication
public class BeDoAnVatApplication implements CommandLineRunner {
    @Autowired
    private IInitDB iInitDB;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode(1+""));
    }

    //    @Override
//    public void run(String... args) throws Exception {
//        if (roleRes.findByRole(E_Role.ROLE_ADMIN.name()) == null) {
//           Role role = roleRes.save(Role.builder()
//                            .role(E_Role.ROLE_USER.name())
//                    .build());
//
//                permisionDetailRes.save(PermisionDetail.builder()
//                        .permision(Permision.builder()
//                                .url("/gio-hang/**")
//                                .method(E_Permision_Method.POST)
//                                .build())
//                                .role(role)
//                        .build());
//
//            permisionDetailRes.save(PermisionDetail.builder()
//                    .permision(Permision.builder()
//                            .url("/gio-hang/**")
//                            .method(E_Permision_Method.GET)
//                            .build())
//                    .role(role)
//                    .build());
//
//            permisionDetailRes.save(PermisionDetail.builder()
//                    .permision(Permision.builder()
//                            .url("/gio-hang/**")
//                            .method(E_Permision_Method.PUT)
//                            .build())
//                    .role(role)
//                    .build());
//
//            permisionDetailRes.save(PermisionDetail.builder()
//                    .permision(Permision.builder()
//                            .url("/don-hang/**")
//                            .method(E_Permision_Method.POST)
//                            .build())
//                    .role(role)
//                    .build());
//
//            permisionDetailRes.save(PermisionDetail.builder()
//                    .permision(Permision.builder()
//                            .url("/don-hang/**")
//                            .method(E_Permision_Method.GET)
//                            .build())
//                    .role(role)
//                    .build());
//
//            permisionDetailRes.save(PermisionDetail.builder()
//                    .permision(Permision.builder()
//                            .url("/don-hang/**")
//                            .method(E_Permision_Method.PUT)
//                            .build())
//                    .role(role)
//                    .build());
//
//           Role roleAd = roleRes.save(Role.builder()
//                    .role(E_Role.ROLE_ADMIN.name())
//                    .build());
//
//           TaiKhoan tkadmin =  taiKhoanRes.save(TaiKhoan.builder()
//                            .taiKhoan("admin")
//                            .matKhau(passwordEncoder.encode("1"))
//                    .build());
//
//            TaiKhoan tkuser =  taiKhoanRes.save(TaiKhoan.builder()
//                    .taiKhoan("user")
//                    .matKhau(passwordEncoder.encode("1"))
//                    .build());
//
//            roleDetailRes.save(Role_Detail.builder()
//                            .taiKhoan(tkadmin)
//                            .role(roleAd)
//                    .build());
//
//            roleDetailRes.save(Role_Detail.builder()
//                    .taiKhoan(tkuser)
//                    .role(role)
//                    .build());
//        }
//    }
}
