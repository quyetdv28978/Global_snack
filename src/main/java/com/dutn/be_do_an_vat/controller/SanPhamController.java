package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.service.SerSanPham;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.ConstFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "API lấy danh sách San pham", description = "trả về danh sách San pham")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @Parameter(name = "sl", description = "Số lượng sản phẩm trong 1 trang")
    @Parameter(name = "page", description = "Trang cần hiển thị")
    @GetMapping("{sl}/{page}")
    public ResponseEntity showAllSanPhams(@PathVariable Integer sl, @PathVariable Integer page) {
        return ResponseEntity.ok().body(serSanPham.getSanPhamBy(sl, page));
    }
    @Operation(summary = "API lấy danh sách San pham chưa có khuyến mãi", description = "trả về danh sách San pham")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping("discount")
    public ResponseEntity showSanPhamsDiscount() {
        return ResponseEntity.ok().body(serSanPham.getSanPhamDiscount());
    }

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
