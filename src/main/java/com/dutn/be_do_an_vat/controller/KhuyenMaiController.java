package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTODieuKienKhuyenMai;
import com.dutn.be_do_an_vat.dto.DTOKhuyenMai;
import com.dutn.be_do_an_vat.service.KhuyenMaiSer;
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
@RequestMapping("${project.endpont.v1}/khuyenmai")
@Tag(name = "Khuyến mãi", description = "Khuyến Mãi APIs")
@CrossOrigin("*")
public class KhuyenMaiController {
    @Autowired
    private KhuyenMaiSer khuyenMaiSer;

    @Operation(summary = "API them khuyến mãi % hoặc giảm tiền mặt", description = "trả về thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping("lasao")
    public ResponseEntity themKhuyenMai(@RequestBody DTOKhuyenMai dtoKhuyenMai) {
        khuyenMaiSer.addKhuyenMaiGiamTheoTiLe(dtoKhuyenMai);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API them khuyến mãi tặng quà", description = "trả về thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping("dieu-kien")
    public ResponseEntity themKhuyenMaiTangQua(@RequestBody DTODieuKienKhuyenMai dtoKhuyenMai) {
        khuyenMaiSer.addkhuyenMaiTangKem(dtoKhuyenMai);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API sửa khuyến mãi", description = "trả về thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "idkm", description = "id của khuyến mãi cần update")
    @PostMapping("update/{idkm}")
    public ResponseEntity themKhuyenMaiTangQua(@RequestBody DTOKhuyenMai dtoKhuyenMai, Long idkm) {
        khuyenMaiSer.updateKhuyenMai(dtoKhuyenMai, idkm);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API sửa trạng thái khuyến mãi", description = "trả về thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "idkm", description = "id của khuyến mãi cần update")
    @Parameter(name = "trangThai", description = "trạng thái của khuyến mãi cần update")
    @PostMapping("update/{idkm}/{trangThai}")
    public ResponseEntity themKhuyenMaiTangQua(int trangThai, Long idkm) {
        khuyenMaiSer.updateKhuyenMaiQuick(idkm, trangThai);
        return ResponseEntity.ok().body(Const.SUSSCESS);
    }

    @Operation(summary = "API xóa khuyến mãi", description = "trả về thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "idkm", description = "id của khuyến mãi cần update")
    @DeleteMapping("delete/{idkm}")
    public ResponseEntity xoaKhuyenMai(Long idkm) {
        khuyenMaiSer.deleteKhuyenMai(idkm);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }

}
