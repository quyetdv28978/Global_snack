package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.entity.DanhMuc;
import com.dutn.be_do_an_vat.service.DanhMucSer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Danh Muc", description = "Danh muc APIs")
@RequestMapping("${project.endpont.v1}/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucSer danhMucSer;

    @Operation(summary = "API them danh muc", description = "trả về danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping
    public ResponseEntity thenDanhMuc(@RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok().body(danhMucSer.add(danhMuc));
    }
}
