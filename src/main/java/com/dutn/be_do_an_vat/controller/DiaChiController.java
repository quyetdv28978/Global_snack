package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTODiaChi;
import com.dutn.be_do_an_vat.dto.DTOKhachHang;
import com.dutn.be_do_an_vat.service.DiaChiSer;
import com.dutn.be_do_an_vat.service.KhachHangSer;
import com.dutn.be_do_an_vat.utility.Const;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Địa chỉ", description = "\"Địa chỉ APIs")
@RequestMapping("${project.endpont.v1}/dia-chi")
@CrossOrigin("*")
public class DiaChiController {
    @Autowired
    private DiaChiSer diaChiSer;

    @Operation(summary = "API them \"Địa chỉ", description = "trả về \"Địa chỉ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Địa chỉ: diaChi = Thông tin địa chỉ khách hàng \n" +
            "idkh = id của khách hàng\n")
    @PostMapping("add")
    public ResponseEntity thenDanhMuc(@RequestBody DTODiaChi danhMuc) {
        diaChiSer.themDiaChi(danhMuc);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API sửa địa chỉ", description = "trả về địa chỉ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Địa chỉ: diaChi = Thông tin địa chỉ khách hàng \n" +
            "idkh = id của khách hàng\n")
    @Parameter(name = "iddm", description = "id của địa chỉ cần update")

    @PutMapping("update/{idkh}")
    public ResponseEntity updateDanhMuc(@PathVariable Long iddm, @RequestBody DTODiaChi danhMuc) {
        diaChiSer.updateDiaChi(danhMuc, iddm);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API xóa địa chỉ", description = "trả về địa chỉ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "iddm", description = "id của địa chỉ cần xoa")
    @DeleteMapping("delete/{idkh}")
    public ResponseEntity thenDanhMuc(@RequestBody DTODiaChi iddm, @PathVariable long idkh) {
        diaChiSer.deleteDiaChi(iddm.getDiaChi(), idkh);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
