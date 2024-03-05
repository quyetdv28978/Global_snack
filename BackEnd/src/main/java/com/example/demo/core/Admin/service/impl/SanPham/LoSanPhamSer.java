package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminLosanPhamRequest;
import com.example.demo.entity.LoSanPham;
import com.example.demo.reponsitory.ILoSanPhamRes;
import com.example.demo.reponsitory.NhaCungCapReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoSanPhamSer {
    @Autowired
    private ILoSanPhamRes loSanPhamRes;
    @Autowired
    private NhaCungCapReponsitory nhaCungCapRes;

    public List<LoSanPham> getAllLoSanPham() {
        return loSanPhamRes.findAll();
    }

    public List<LoSanPham> getAllByTrangThai(int trangThai) {
        return loSanPhamRes.findAllByTrangThai(trangThai);
    }

    public void addLoSanPham(AdminLosanPhamRequest loSanPham) {
        for (int i = 0; i < loSanPham.getSoLuongSize().size(); i++) {
            loSanPhamRes.save(LoSanPham.builder()
                    .maLo(loSanPham.getMaLo())
                    .tenLo(loSanPham.getTenLo())
                    .giaBan(Double.parseDouble(loSanPham.getGiaBan().get(i)))
                    .soLuong(Integer.parseInt(loSanPham.getSoLuongSize().get(i)))
                    .tongSoLuongSanPham(loSanPham.getSoLuongSize()
                            .stream().mapToInt(j -> Integer.parseInt(j)).sum())
                    .ngayHetHan(loSanPham.getNgayHetHan())
                    .nhaCungCap(nhaCungCapRes.findByTenNhaCungCap(loSanPham.getNameNhaCungCap()))
                    .trangThai(1)
                    .build());
        }
    }

    public void updateTrangThai(Long idLSP) {
        LoSanPham loSanPham = loSanPhamRes.findById(idLSP).get();
        loSanPham.setTrangThai(0);
        loSanPhamRes.save(loSanPham);
    }
}
