package com.dutn.be_do_an_vat;

import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.entity.base_entity.E_Permision_Method;
import com.dutn.be_do_an_vat.entity.base_entity.E_Role;
import com.dutn.be_do_an_vat.repositoty.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class BeDoAnVatApplication implements CommandLineRunner {
    @Autowired
    private iRole roleRes;
    @Autowired
    private ITaiKhoan taiKhoanRes;
    @Autowired
    private IPermision permisionRes;
    @Autowired
    private IPermisionDetail permisionDetailRes;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IRoleDetail roleDetailRes;

    public static void main(String[] args) {
        SpringApplication.run(BeDoAnVatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRes.findByRole(E_Role.ROLE_ADMIN.name()) == null) {
           Role role = roleRes.save(Role.builder()
                            .role(E_Role.ROLE_USER.name())
                    .build());

                permisionDetailRes.save(PermisionDetail.builder()
                        .permision(Permision.builder()
                                .url("/gio-hang/**")
                                .method(E_Permision_Method.POST)
                                .build())
                                .role(role)
                        .build());

            permisionDetailRes.save(PermisionDetail.builder()
                    .permision(Permision.builder()
                            .url("/gio-hang/**")
                            .method(E_Permision_Method.GET)
                            .build())
                    .role(role)
                    .build());

            permisionDetailRes.save(PermisionDetail.builder()
                    .permision(Permision.builder()
                            .url("/gio-hang/**")
                            .method(E_Permision_Method.PUT)
                            .build())
                    .role(role)
                    .build());

            permisionDetailRes.save(PermisionDetail.builder()
                    .permision(Permision.builder()
                            .url("/don-hang/**")
                            .method(E_Permision_Method.POST)
                            .build())
                    .role(role)
                    .build());

            permisionDetailRes.save(PermisionDetail.builder()
                    .permision(Permision.builder()
                            .url("/don-hang/**")
                            .method(E_Permision_Method.GET)
                            .build())
                    .role(role)
                    .build());

            permisionDetailRes.save(PermisionDetail.builder()
                    .permision(Permision.builder()
                            .url("/don-hang/**")
                            .method(E_Permision_Method.PUT)
                            .build())
                    .role(role)
                    .build());

           Role roleAd = roleRes.save(Role.builder()
                    .role(E_Role.ROLE_ADMIN.name())
                    .build());

           TaiKhoan tkadmin =  taiKhoanRes.save(TaiKhoan.builder()
                            .taiKhoan("admin")
                            .matKhau(passwordEncoder.encode("1"))
                    .build());

            TaiKhoan tkuser =  taiKhoanRes.save(TaiKhoan.builder()
                    .taiKhoan("user")
                    .matKhau(passwordEncoder.encode("1"))
                    .build());

            roleDetailRes.save(Role_Detail.builder()
                            .taiKhoan(tkadmin)
                            .role(roleAd)
                    .build());

            roleDetailRes.save(Role_Detail.builder()
                    .taiKhoan(tkuser)
                    .role(role)
                    .build());
        }
    }
}
