package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.service.LoSanPhamSer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${project.endpont.v1}/lo")
@Tag(name = "Lô sản phẩm", description = "Lô sản phẩm APIs")
@CrossOrigin("*")
public class LoSanPhamController {
    @Autowired
    private LoSanPhamSer loSanPhamSer;

    @Operation(summary = "API xem lô sản phẩm theo id sản phẩm và trạng thái", description = "trả về lô sản phẩm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping("{idsp}/{trangThai}")
    public ResponseEntity showLoSanPhams(@PathVariable(name = "idsp") Long idsp, @PathVariable(name = "trangThai") Integer trangThai) {
        return ResponseEntity.ok().body(loSanPhamSer.loSanPhams(idsp, trangThai));
    }
}
