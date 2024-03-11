package com.example.demo.core.Admin.model.response;

import com.example.demo.entity.Image;
import com.example.demo.entity.SanPhamChiTiet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDOT {

    private List<AdminImageResponse> img;
    private List<AdminSanPhamChiTiet2Response> sanPhamChiTiet;
    private Integer id;
    private String ten;
    private String moTa;
    private String ma;
    private Integer trangThai;
    private String ngayTao;
    private Integer soLuongTon;
    private String vatLieu;
    private String loai;
    private String thuongHieu;
    private String anh;
    private String ngaySua;


    public SanPhamDOT(List<AdminImageResponse> img,
                      Integer id, String ten, String moTa, String ma, Integer trangThai,
                      String ngayTao, Integer soLuongTon,
                      String vatLieu, String loai,
                      String thuongHieu, String anh, String ngaySua) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.ma = ma;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.soLuongTon = soLuongTon;
        this.vatLieu = vatLieu;
        this.loai = loai;
        this.thuongHieu = thuongHieu;
        this.anh = anh;
        this.ngaySua = ngaySua;
    }
}
