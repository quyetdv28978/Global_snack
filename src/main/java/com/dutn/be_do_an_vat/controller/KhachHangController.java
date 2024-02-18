package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOKhachHang;
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
@Tag(name = "Khách hàng", description = "Khách hàng APIs")
@RequestMapping("${project.endpont.v1}/khach-hang")
@CrossOrigin("*")
public class KhachHangController {
    @Autowired
    private KhachHangSer khachHangSer;

    @Operation(summary = "API lấy danh sách khach hang", description = "trả về danh sách danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @GetMapping
    public ResponseEntity showDanhMucs() {
        return ResponseEntity.ok().body(khachHangSer.getAll());
    }

    @Operation(summary = "API lấy danh sách khach hang phân trang", description = "trả về danh sách danh muc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "sl", description = "Số lượng sản phẩm trong 1 trang")
    @Parameter(name = "trang", description = "Trang cần hiển thị")
    @GetMapping("{sl}/{trang}")
    public ResponseEntity showDanhMucsPhanTrang(@PathVariable(value = "5") Integer sl, @PathVariable(value = "0") Integer trang) {
        return ResponseEntity.ok().body(khachHangSer.getAll_filter(sl, trang));
    }

    @Operation(summary = "API them khach hang", description = "trả về khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Khách hàng: idkh = id của khách hàng \n" +
            "tenKhachHang = Tên khách hàng\n" +
            "diaChis = danh sách địa chỉ khách hàng")
    @PostMapping("add")
    public ResponseEntity thenDanhMuc(@RequestBody DTOKhachHang danhMuc) {
        return ResponseEntity.ok().body(khachHangSer.add(danhMuc).get());
    }

    @Operation(summary = "API sửa khach hang", description = "trả về khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Khách hàng: idkh = id của khách hàng \n" +
            "tenKhachHang = Tên khách hàng\n" +
            "diaChis = danh sách địa chỉ khách hàng")
    @Parameter(name = "iddm", description = "id của kahch hang cần update")

    @PutMapping("update/{iddm}")
    public ResponseEntity updateDanhMuc(@PathVariable long iddm, @RequestBody DTOKhachHang danhMuc) {
        return ResponseEntity.ok().body(khachHangSer.update(iddm, danhMuc).get());
    }

    @Operation(summary = "API xóa khach hang", description = "trả về khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "iddm", description = "id của khach hang cần xoa")
    @DeleteMapping("delete/{iddm}")
    public ResponseEntity thenDanhMuc(@PathVariable Long iddm) {
        khachHangSer.delete(iddm);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
