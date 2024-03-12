package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminLosanPhamRequest;
import com.example.demo.core.Admin.model.request.AdminSanPhamChiTietRequest;
import com.example.demo.core.Admin.model.response.AdminLoSanPham;
import com.example.demo.core.Admin.model.response.AdminSanPhamChiTiet2Response;
import com.example.demo.core.Admin.repository.AdChiTietSanPhamReponsitory;
import com.example.demo.core.Admin.repository.AdSanPhamReponsitory;
import com.example.demo.entity.LoSanPham;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.reponsitory.ILoSanPhamRes;
import com.example.demo.reponsitory.NhaCungCapReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoSanPhamSer {
    @Autowired
    private ILoSanPhamRes loSanPhamRes;
    @Autowired
    private NhaCungCapReponsitory nhaCungCapRes;

    @Autowired
    private AdSanPhamReponsitory sanPhamReponsitory;

    @Autowired
    private AdChiTietSanPhamReponsitory chiTietSanPhamReponsitory;

    public List<LoSanPham> getAllLoSanPham() {
        return loSanPhamRes.findAll();
    }

    public List<LoSanPham> getAllLoSanPhamByTrangThai(int trangThai) {
        return loSanPhamRes.findAllByTrangThai(trangThai);
    }

    public List<AdminLoSanPham> getAllLoSanPhamBySP(Integer idCTSP) {
        return loSanPhamRes.findAllBySanPhamChiTiet(idCTSP);
    }

    public List<AdminLoSanPham> getAllLoSanPhamBySPNotNull(Integer idCTSP) {
        return loSanPhamRes.findAllBySanPhamChiTiet(chiTietSanPhamReponsitory.findById(idCTSP).get());
    }

//    public List<LoSanPham> getAllLoSanPhamByTenTrangThai(String tenSanpham, int trangThai) {
//        return loSanPhamRes.findByTenLoAndTrangThai(tenSanpham, trangThai);
//    }

    public List<LoSanPham> getAllByTrangThai(int trangThai) {
        return loSanPhamRes.findAllByTrangThai(trangThai);
    }

    public LoSanPham addLoSanPham(AdminLosanPhamRequest losanPhamRequest) {
        return loSanPhamRes.save(LoSanPham.builder()
                .maLo(losanPhamRequest.getMaLo())
                .tenLo(losanPhamRequest.getTenLo())
                .ngayHetHan(losanPhamRequest.getNgayHetHan())
                .ngayNhap(LocalDateTime.now())
                .trangThai(0)
                .build());
    }

    public AdminSanPhamChiTiet2Response addLoSanPhamSanPhams(AdminSanPhamChiTietRequest loSanPham, Integer idSanPhamChiTiet) {
        LoSanPham loSanPhamMoi = loSanPhamRes.findById(Long.valueOf(loSanPham.getTenLo())).get();
        SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamReponsitory.findById(idSanPhamChiTiet).get();

        LoSanPham loSanPhamCu = loSanPhamRes.showLoSanPhamByIdCtsp(sanPhamChiTiet.getId());
        if (loSanPhamCu != null) {
            if (loSanPhamMoi.getNgayHetHan().isBefore(loSanPhamCu.getNgayHetHan())) {
                loSanPhamCu.setTrangThai(2);
                loSanPhamMoi.setTrangThai(1);
                loSanPhamRes.save(loSanPhamCu);
            } else if (loSanPhamCu.getId() == loSanPhamMoi.getId()) {
                loSanPhamMoi.setSoLuong(loSanPhamMoi.getSoLuong() + loSanPham.getSoLuongTon());
                loSanPhamRes.save(loSanPhamMoi);
                return sanPhamReponsitory.getByid(idSanPhamChiTiet);
            }else {
                loSanPhamMoi.setTrangThai(2);
                loSanPhamRes.save(loSanPhamMoi);
            }
        }else loSanPhamMoi.setTrangThai(1);


        loSanPhamMoi.setSoLuong(loSanPham.getSoLuongTon());
        loSanPhamMoi.setSanPhamChiTiet(sanPhamChiTiet);
//        loSanPhamMoi.setTrangThai(1);
        sanPhamChiTiet.setTrangThai(1);
        loSanPhamRes.save(loSanPhamMoi);
        Integer slt = loSanPhamRes.sumSoLuongSanPham(sanPhamChiTiet.getId());
        sanPhamChiTiet.setSoLuongTon(slt == null ? loSanPham.getSoLuongTon() + sanPhamChiTiet.getSoLuongTon()  : slt);
        chiTietSanPhamReponsitory.save(sanPhamChiTiet);
        return sanPhamReponsitory.getByid(idSanPhamChiTiet);
    }

    public void updateTrangThai(Long idLSP) {
        LoSanPham loSanPham = loSanPhamRes.findById(idLSP).get();
        loSanPham.setTrangThai(0);
        loSanPhamRes.save(loSanPham);
    }

    public AdminSanPhamChiTiet2Response updateLoSanPhamByIdCtSP(Long idLSP, Integer idCtsp) {
        LoSanPham loSanPhamMoi = loSanPhamRes.findById(idLSP).get();
        LoSanPham loSanPhamCu = loSanPhamRes.showLoSanPhamByIdCtsp(idCtsp);

        loSanPhamMoi.setTrangThai(1);
        loSanPhamCu.setTrangThai(2);

        loSanPhamRes.save(loSanPhamMoi);
        loSanPhamRes.save(loSanPhamCu);
        return sanPhamReponsitory.getByid(idCtsp);
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.of(2024, 3,11).isBefore(LocalDate.of(2024, 3, 12)));
    }
}
