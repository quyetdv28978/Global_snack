package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.service.DonHangSer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Don hang", description = "Don hang APIs")
@RequestMapping("${project.endpont.v1}/don-hang")
public class DonHangController {
    @Autowired
    private DonHangSer donHangSer;

    @Operation(summary = "API them Don hang", description = "trả về Don hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping("{iduser}")
    public ResponseEntity themDonHang(@RequestBody List<DTOGioHang> dtoDonHangs, @PathVariable Long iduser) {
        return ResponseEntity.ok().body(donHangSer.themDonHang(iduser, dtoDonHangs));
    }

    @Operation(summary = "API sua Don hang", description = "trả về Don hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PutMapping("{idDonhang}")
    public ResponseEntity suaDonHang(@RequestBody List<DTOGioHang> dtoDonHangs, @PathVariable Long idDonhang) {
        return ResponseEntity.ok().body(donHangSer.updateDonHang(idDonhang, dtoDonHangs));
    }

    @Operation(summary = "API lay Don hang theo trang thai", description = "trả về Don hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @GetMapping("{tt}")
    public ResponseEntity getDonHangBy(@PathVariable Integer tt) {
        return ResponseEntity.ok().body(donHangSer.getAllDonHangBy(tt));
    }


    @Operation(summary = "API update Don hang theo trang thai cua khach hang", description = "trả về Don hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @GetMapping("{tt}/{iddh}")
    public ResponseEntity updateDonHangByTT(@PathVariable Integer tt, @PathVariable Long iddh) {
        donHangSer.updateTrangThaiDonHang(iddh, tt);
        return ResponseEntity.ok().body("susscess");
    }
}
