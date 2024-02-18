package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTODanhMuc;
import com.dutn.be_do_an_vat.entity.DanhMuc;
import com.dutn.be_do_an_vat.service.DanhMucSer;
import com.dutn.be_do_an_vat.utility.Const;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Danh Muc", description = "Danh muc APIs")
@RequestMapping("${project.endpont.v1}/danh-muc")
@CrossOrigin("*")
public class DanhMucController {
    @Autowired
    private DanhMucSer danhMucSer;

    @Operation(summary = "API lấy danh sách danh muc", description = "trả về danh sách danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @GetMapping
    public ResponseEntity showDanhMucs() {
        return ResponseEntity.ok().body(danhMucSer.getAll());
    }

    @Operation(summary = "API them danh muc", description = "trả về danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin danh mục: nameDanhMuc = tên danh mục \n" +
            "sanPhams = danh sách sản phẩm")
    @PostMapping("add")
    public ResponseEntity thenDanhMuc(@RequestBody DTODanhMuc danhMuc) {
        return ResponseEntity.ok().body(danhMucSer.add(danhMuc));
    }

    @Operation(summary = "API sửa danh muc", description = "trả về danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin danh mục: nameDanhMuc = tên danh mục \n" +
            "sanPhams = danh sách sản phẩm")
    @Parameter(name = "iddm", description = "id của danh mục cần update")

    @PutMapping("update/{iddm}")
    public ResponseEntity updateDanhMuc(@PathVariable Long iddm, @RequestBody DTODanhMuc danhMuc) {
        return ResponseEntity.ok().body(danhMucSer.update(iddm, danhMuc));
    }

    @Operation(summary = "API xóa danh muc", description = "trả về danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "iddm", description = "id của danh mục cần xoa")
    @DeleteMapping("delete/{iddm}")
    public ResponseEntity thenDanhMuc(@PathVariable Long iddm) {
        danhMucSer.delete(iddm);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
