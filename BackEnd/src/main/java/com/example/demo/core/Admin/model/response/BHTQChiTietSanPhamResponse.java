package com.example.demo.core.Admin.model.response;

import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.TrongLuong;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BHTQChiTietSanPhamResponse {
    private Integer id;
    private String ma;
    private String ngaySua;
    private String ngayTao;
    private String anh;
    private Integer trangThai;
    private Integer soLuongTon;
    private BigDecimal giaBan;
    private BigDecimal giaNhap;
    private BigDecimal giaSauGiam;
    private SanPham sanPham;
    private TrongLuong trongLuong;
    private KhuyenMai khuyenMai;
}
