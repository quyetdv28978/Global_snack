package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.entity.GioHang;
import com.dutn.be_do_an_vat.entity.GioHangChiTiet;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.entity.SanPham;
import com.dutn.be_do_an_vat.repositoty.IGioHang;
import com.dutn.be_do_an_vat.repositoty.IGioHangChiTIet;
import com.dutn.be_do_an_vat.repositoty.IKhachHang;
import com.dutn.be_do_an_vat.repositoty.ISanPham;
import com.dutn.be_do_an_vat.service.base_service.IGioHangSer;
import com.dutn.be_do_an_vat.utility.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GioHangSer implements IGioHangSer {
    @Autowired
    private IGioHang gioHangRes;

    @Autowired
    private IGioHangChiTIet gioHangChiTIetRes;

    @Autowired
    private ISanPham sanPhamRes;

    @Autowired
    private IKhachHang khachHangRes;

    @Override
    @Transactional
    public KhachHang themGioHang(Long iduser, List<DTOGioHang> dtoGioHang) {
        KhachHang khachHang = khachHangRes.findById(iduser).get();
        GioHang gioHang = gioHangRes.saveAndFlush(GioHang.builder()
                .khachHang(khachHangRes.findById(iduser).get())
                .trangThai(0)
                .build());
        updateAndSave(dtoGioHang, iduser, gioHang);
        khachHang.setGioHang(gioHang);
        return khachHang;
    }

    @Override
    @Transactional
    public KhachHang updateGioHang(Long iduser, List<DTOGioHang> dtoGioHang) {
        KhachHang khachHang = khachHangRes.findById(iduser).orElseThrow(() -> new UsernameNotFoundException(Const.USER_NOT_FOUND));

        gioHangChiTIetRes.deleteAllById(() -> khachHang.getGioHang().getGioHangChiTiets().stream()
                .map(i -> i.getId()).iterator()
        );

        updateAndSave(dtoGioHang, iduser, khachHang.getGioHang());
        return khachHangRes.findById(iduser).get();
    }

    private void updateAndSave(List<DTOGioHang> dtoGioHang, Long iduser, GioHang gioHang) {
        dtoGioHang.stream().forEach(i -> {
            gioHangChiTIetRes.saveAndFlush(GioHangChiTiet.builder()
                    .gioHangs(gioHang)
                    .sanPhams(sanPhamRes.findById(i.getIdsp()).get())
                    .giaTien(sanPhamRes.findById(i.getIdsp()).get().getGiaBan() * i.getSoLuong())
                    .soLuong(i.getSoLuong())
                    .build());
        });
    }

    @Override
    public GioHang searchGioHangByUser(Long idUser) {
        return gioHangRes.findById(idUser).get();
    }
}
