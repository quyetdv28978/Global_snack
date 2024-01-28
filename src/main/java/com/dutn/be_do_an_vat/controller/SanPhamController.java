package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.service.SerSanPham;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.ConstFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@Tag(name = "San pham", description = "San pham APIs")
@RequestMapping("${project.endpont.v1}/san-pham")
public class SanPhamController {
    @Autowired
    private SerSanPham serSanPham;

    @Operation(summary = "API them San pham", description = "trả về San pham")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @PostMapping
    public ResponseEntity themSanPham(@RequestPart DTOSanPham dtoSanPham, @RequestPart MultipartFile[] multipartFiles) {
        ConstFile.updateLoadFile(multipartFiles);
        dtoSanPham.setImages(Arrays.stream(multipartFiles).map(i -> i.getOriginalFilename()).collect(Collectors.toSet()));
        return ResponseEntity.ok().body(serSanPham.themSanPham(dtoSanPham));
    }

    @Operation(summary = "API sua San pham", description = "trả về San pham")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "400", description = "Không tìm thấy sản phẩm")
    })
    @PutMapping("/{idsp}")
    public ResponseEntity suaSanPham(
            @PathVariable Long idsp,
            @RequestPart DTOSanPham dtoSanPham,
            @RequestPart MultipartFile[] multipartFiles) {
        ConstFile.updateLoadFile(multipartFiles);
        dtoSanPham.setImages(Arrays.stream(multipartFiles).map(i -> i.getOriginalFilename()).collect(Collectors.toSet()));
        return ResponseEntity.ok().body(serSanPham.updateSanPham(idsp, dtoSanPham));
    }

    @Operation(summary = "API xoa San pham", description = "trả về susscess")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @DeleteMapping("/{idsp}")
    public ResponseEntity xoaSanPham(@PathVariable Long idsp) {
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
