package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTODonHang;
import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.repositoty.*;
import com.dutn.be_do_an_vat.service.base_service.IDonHangSer;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DonHangSer implements IDonHangSer {
    @Autowired
    private IDonhang donHangSer;

    @Autowired
    private IDonHangChiTiet donHangChiTietRes;

    @Autowired
    private ISanPham sanPhamRes;

    @Autowired
    private IKhachHang khachHangRes;
    @Autowired
    private ITaiKhoan taiKhoanRes;

    @Override
    public Set<DTODonHang> getAllDonHangBy(Integer trangThai) {
        return donHangSer.findAllByTrangThai(trangThai).stream().flatMap(i -> i.getHoaDonChiTiets().stream())
                .map(sanpham -> DTODonHang.builder()
                        .sanPhams(MapperUtils.entityToDTO(sanpham.getSanPham(), DTOSanPham.class))
                        .soLuong(sanpham.getSoLuong())
                        .tongTien(sanpham.getTongTien())
                        .trangThai(sanpham.getSanPham().getTrangThai())
                        .build()
                ).collect(Collectors.toSet());
    }

    @Override
    public KhachHang themDonHang(Long iduser, List<DTOGioHang> dtoGioHang) {
        KhachHang khachHang = khachHangRes.findById(iduser).get();
        HoaDon hoaDon = donHangSer.saveAndFlush(HoaDon.builder()
                .trangThai(0)
                .build());

        khachHang.setHoaDons(Set.of(hoaDon));
        updateAndSave(dtoGioHang, iduser, hoaDon);
        return khachHang;
    }

    @Override
    @Transactional
    public HoaDon updateDonHang(Long idDonhang, List<DTOGioHang> dtoGioHang) {
        HoaDon hoaDon = donHangSer.findById(idDonhang).get();

        donHangChiTietRes.deleteAllById(() -> hoaDon.getHoaDonChiTiets().stream()
                .map(i -> i.getId()).iterator()
        );

        updateAndSave(dtoGioHang, idDonhang, hoaDon);
        return hoaDon;
    }

    @Override
    @Transactional
    public Set<DTODonHang> searchDonHangByUser(Long idUser) {
        return null;
    }

    @Override
    public Set<DTODonHang> searchGioHangByUser(Long idUser) {
        return null;
    }

    public void updateTrangThaiDonHang(Long idDonHang, int trangThai) {
        HoaDon hoaDon = donHangSer.findById(idDonHang).get();
        hoaDon.setTrangThai(trangThai);
        donHangSer.save(hoaDon);
    }

    private void updateAndSave(List<DTOGioHang> dtoGioHang, Long iduser, HoaDon gioHang) {
        dtoGioHang.stream().forEach(i -> {
            donHangChiTietRes.saveAndFlush(HoaDonChiTiet.builder()
                    .hoaDon(gioHang)
                    .sanPham(sanPhamRes.findById(i.getIdsp()).get())
                    .tongTien(sanPhamRes.findById(i.getIdsp()).get().getGiaBan() * i.getSoLuong())
                    .soLuong(i.getSoLuong())
                    .build());
        });
    }
}
