package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.service.GioHangSer;
import com.dutn.be_do_an_vat.service.base_service.IGioHangSer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Gio Hang", description = "Gio Hang APIs")
@RequestMapping("${project.endpont.v1}/gio-hang")
public class GioHangController {
    @Autowired
    private IGioHangSer gioHangSer;

    @Operation(summary = "API them gio hang", description = "trả về gio hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping("/{iduser}")
    public ResponseEntity themGioHang(@PathVariable Long iduser, @RequestBody List<DTOGioHang> dtoGioHang) {
        return ResponseEntity.ok().body(gioHangSer.themGioHang(iduser, dtoGioHang));
    }


    @Operation(summary = "API sua gio hang", description = "trả về gio hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PutMapping("/{iduser}")
    public ResponseEntity updateGioHang(@PathVariable Long iduser, @RequestBody List<DTOGioHang> dtoGioHang) {
        return ResponseEntity.ok().body(gioHangSer.updateGioHang(iduser, dtoGioHang));
    }


    @Operation(summary = "API tim gio hang cua khach hang", description = "trả về gio hang cua khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "400", description = "Không tim thay khach hang"),

    })
    @GetMapping("{iduser}")
    public ResponseEntity searchGioHangUser(@PathVariable Long iduser) {
        return ResponseEntity.ok().body(gioHangSer.searchGioHangByUser(iduser));
    }
}
