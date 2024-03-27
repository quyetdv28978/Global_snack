package com.example.demo.core.Admin.controller;

import com.example.demo.core.Admin.model.request.AdminLosanPhamRequest;
import com.example.demo.core.Admin.model.request.AdminSanPhamChiTietRequest;
import com.example.demo.core.Admin.model.request.AdminSanPhamRepuest2;
import com.example.demo.core.Admin.service.impl.SanPham.LoSanPhamSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/lo-san-pham")
public class LoSanPhamController {
    @Autowired
    private LoSanPhamSer loSanPhamSer;


    @GetMapping
    public ResponseEntity getAllLoSanPhams() {
        return ResponseEntity.ok().body(loSanPhamSer.getAllLoSanPham());
    }

    @GetMapping("/by-san-pham-ct/{idctsp}")
    public ResponseEntity getAllLoSanPhamBySPCTOrLoNull(@PathVariable int idctsp) {
        return ResponseEntity.ok().body(loSanPhamSer.getAllLoSanPhamBySP(idctsp));
    }

    @GetMapping("/by-san-pham-ct-not-null/{idctsp}")
    public ResponseEntity getAllLoSanPhamBySPCT(@PathVariable int idctsp) {
        return ResponseEntity.ok().body(loSanPhamSer.getAllLoSanPhamBySPNotNull(idctsp));
    }

    @GetMapping("lo-san-pham-het-han-by-mount/{mount}")
    public ResponseEntity showLoSanPhamByMount(@PathVariable Integer mount) {
        System.out.println(mount);
        return ResponseEntity.ok().body(loSanPhamSer.loSanPhamHanSuDungs(mount));
    }

    @GetMapping("{trangThai}")
    public ResponseEntity getAllLoSanPhams(@PathVariable int trangThai) {
        return ResponseEntity.ok().body(loSanPhamSer.getAllLoSanPhamByTrangThai(trangThai));
    }

    @PostMapping("/add-lo")
    public ResponseEntity themLo(@RequestBody AdminLosanPhamRequest loSanPham) {
        return ResponseEntity.ok().body(loSanPhamSer.addLoSanPham(loSanPham));
    }

    @PutMapping("update-lo-san-pham-by-idctsp/{idLSP}/{idCtsp}")
    public ResponseEntity updateLoSanPhamByIdCtsp(@PathVariable Long idLSP, @PathVariable Integer idCtsp) {
        return ResponseEntity.ok(loSanPhamSer.updateLoSanPhamByIdCtSP(idLSP, idCtsp));
    }

    @PostMapping("add-lo-san-pham/{id}")
    public ResponseEntity themLoSanPhams(@PathVariable Integer id, @RequestBody AdminSanPhamChiTietRequest sanPhamRequest) {
        return ResponseEntity.ok(loSanPhamSer.addLoSanPhamSanPhams(sanPhamRequest, id));
    }

}
