package com.example.demo.core.khachHang.controller;

import com.example.demo.core.Admin.model.response.AdminSanPhamChiTietResponse;
import com.example.demo.core.khachHang.model.response.DetailSanPhamResponse;
import com.example.demo.core.khachHang.repository.DetailSPCTTRepository;
import com.example.demo.core.khachHang.service.KHDetailService.DetailSizeService;
import com.example.demo.core.khachHang.service.KHDetailService.ImageServie;
import com.example.demo.core.khachHang.service.KHDetailService.DetaiService;
import com.example.demo.entity.SanPhamChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khach-hang/detail")
@CrossOrigin(origins = {"*"})
public class Detail {

    @Autowired
    private DetaiService detaiService;

    @Autowired
    private DetailSizeService sizeService;

    @Autowired
    private ImageServie servie;

    @Autowired
    DetailSPCTTRepository detailRepo;

//    @Autowired
//    KHSizeCTRepository sizeCTRepo;
//
//    @Autowired
//    KHMauSacCTRepository mausacCTRepo;


    @GetMapping("/{idctsp}")
    public DetailSanPhamResponse getDetailCTSP(@PathVariable("idctsp") Integer idctsp) {
        DetailSanPhamResponse sanPhamChiTiet = detailRepo.getDetailCTSP(idctsp);
        return sanPhamChiTiet;
    }
    @GetMapping("/find-spct-id")
    public ResponseEntity<?> getSanPhamChiTietByTrongLuong(@RequestParam(required = false) String idtl,@RequestParam Integer idSP) {
        return ResponseEntity.ok(detaiService.getSanPhamChiTietByTrongLuong(idtl,idSP));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer idctsp) {
        return ResponseEntity.ok(detaiService.findById(idctsp));
    }

    @GetMapping("/find-trong-luong/{idctsp}")
    public ResponseEntity<?> listMauSacCT(@PathVariable("idctsp") Integer idctsp){
        return ResponseEntity.ok(detaiService.findTrongLuong(idctsp));
    };

    @GetMapping("/find-spct-by-idSP/{id}")
    public ResponseEntity<?> getSpctByIdSp(@PathVariable("id") Integer idctsp) {
        return ResponseEntity.ok(detaiService.getAllByIdSp(idctsp));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        AdminSanPhamChiTietResponse sanPhamChiTiet = detaiService.get(id);
        return ResponseEntity.ok(sanPhamChiTiet);
    }

    @GetMapping("/findByImage/{id}")
    public ResponseEntity<?> findByImage(@PathVariable Integer id) {
        return ResponseEntity.ok(detaiService.findImage(id));

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getList() {
        List<SanPhamChiTiet> lisst = detaiService.getAlls();
        return ResponseEntity.ok(lisst);
    }

    @GetMapping("/getSLTon/{idctsp}")
    public ResponseEntity<?> getSLTon(@PathVariable("idctsp") Integer idctsp) {
        return ResponseEntity.ok(detailRepo.getSLTonTongByIDCT(idctsp));
    }

    @GetMapping("/getSanPhamSelected/{idctsp}")
    public ResponseEntity<?> getSanPhamSelected(@PathVariable("idctsp") Integer idctsp, @RequestParam("idms") Integer idms, @RequestParam("idsizect") Integer idsizect) {

        return ResponseEntity.ok(detailRepo.getSanPhamSelected(idctsp, idms, idsizect));
    }
}