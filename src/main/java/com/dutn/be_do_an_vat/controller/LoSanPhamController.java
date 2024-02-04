package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.service.LoSanPhamSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${project.endpont.v1}/lo")
public class LoSanPhamController {
    @Autowired
    private LoSanPhamSer loSanPhamSer;

    @GetMapping("{idsp}/{trangThai}")
    public ResponseEntity showLoSanPhams(@PathVariable(name = "idsp") Long idsp, @PathVariable(name = "trangThai") Integer trangThai) {
        return ResponseEntity.ok().body(loSanPhamSer.loSanPhams(idsp, trangThai));
    }
}
