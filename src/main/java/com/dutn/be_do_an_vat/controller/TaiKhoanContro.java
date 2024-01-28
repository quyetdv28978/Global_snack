package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.entity.TaiKhoan;
import com.dutn.be_do_an_vat.service.base_service.ITaiKhoanSer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Tai khoan", description = "tai khoan APIs")
@RequestMapping("api/v1/taikhoan")
public class TaiKhoanContro {
    @Autowired
    private ITaiKhoanSer taiKhoanSer;

    @Operation(summary = "API them tai khoan", description = "trả về tai khoan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả ve tai khoan"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @PostMapping
    public ResponseEntity themTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        return ResponseEntity.ok().body(taiKhoanSer.themTaiKhoan(taiKhoan));
    }
}
