package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.entity.TaiKhoan;
import com.dutn.be_do_an_vat.service.base_service.ITaiKhoanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${project.endpont.version.v1}/tai-khoan")
public class TaiKhoanContro {
    @Autowired
    private ITaiKhoanSer taiKhoanSer;

    @PostMapping
    public ResponseEntity themTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        return ResponseEntity.ok().body(taiKhoanSer.themTaiKhoan(taiKhoan));
    }
}
