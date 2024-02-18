package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTOKhachHang;
import com.dutn.be_do_an_vat.dto.DTONhanVien;
import com.dutn.be_do_an_vat.service.KhachHangSer;
import com.dutn.be_do_an_vat.service.NhanVienSer;
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
@Tag(name = "Nhân Viên", description = "Nhân viên APIs")
@RequestMapping("${project.endpont.v1}/nhan-vien")
@CrossOrigin("*")
public class NhanVienController {
    @Autowired
    private NhanVienSer nhanVienSer;

    @Operation(summary = "API lấy danh sách nhân viên", description = "trả về danh sách nhân viên")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "sl", description = "Số lượng sản phẩm trong 1 trang")
    @Parameter(name = "trang", description = "Trang cần hiển thị")
    @GetMapping("{sl}/{trang}")
    public ResponseEntity showDanhMucs(@PathVariable(value = "5") Integer sl, @PathVariable(value = "0") Integer trang) {
        return ResponseEntity.ok().body(nhanVienSer.getAll_filter(sl, trang));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Khách hàng: idkh = id của khách hàng \n" +
            "taiKhoan = tài khoản nhân viên\n" +
            "matKhau = Mật khẩu nhân viên\n" +
            "fullName = tên đầy đủ nhân viên\n" +
            "DOB = ngày sinh nhân viên (yyyy-dd-mm)\n" +
            "gioiTinh = giới tính nhân viên (NAM, NU, KHAC)")
    @PostMapping("add")
    public ResponseEntity thenDanhMuc(@RequestBody DTONhanVien danhMuc) {
        return ResponseEntity.ok().body(nhanVienSer.add(danhMuc));
    }

    @Operation(summary = "API sửa khach hang", description = "trả về khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "danhMuc", description = "Thông tin Khách hàng: idkh = id của khách hàng \n" +
            "taiKhoan = tài khoản nhân viên\n" +
            "matKhau = Mật khẩu nhân viên\n" +
            "fullName = tên đầy đủ nhân viên\n" +
            "DOB = ngày sinh nhân viên (yyyy-dd-mm)\n" +
            "gioiTinh = giới tính nhân viên (NAM, NU, KHAC)")
    @Parameter(name = "iddm", description = "id của nhân viên cần update")

    @PutMapping("update/{idkh}")
    public ResponseEntity updateDanhMuc(@PathVariable Long iddm, @RequestBody DTONhanVien danhMuc) {
        return ResponseEntity.ok().body(nhanVienSer.update(iddm, danhMuc));
    }

    @Operation(summary = "API xóa khach hang", description = "trả về khach hang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @Parameter(name = "iddm", description = "id của nhân viên cần xoa")
    @DeleteMapping("delete/{iddm}")
    public ResponseEntity thenDanhMuc(@PathVariable Long iddm) {
        nhanVienSer.delete(iddm);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
