package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTODonHang;
import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.entity.HoaDon;
import com.dutn.be_do_an_vat.entity.KhachHang;

import java.util.List;
import java.util.Set;

public interface IDonHangSer {
    Set<DTODonHang> getAllDonHangBy(Integer trangThai);

    KhachHang themDonHang(Long iduser, List<DTOGioHang> dtoGioHang);

    HoaDon updateDonHang(Long iduser, List<DTOGioHang> dtoGioHang);

    Set<DTODonHang> searchDonHangByUser(Long idUser);

    Set<DTODonHang> searchGioHangByUser(Long idUser);
}
