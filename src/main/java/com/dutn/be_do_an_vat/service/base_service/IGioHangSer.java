package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.entity.GioHang;
import com.dutn.be_do_an_vat.entity.KhachHang;

import java.util.List;

public interface IGioHangSer {
    KhachHang themGioHang(Long iduser, List<DTOGioHang> dtoGioHang);

    KhachHang updateGioHang(Long iduser, List<DTOGioHang> dtoGioHang);

    GioHang searchGioHangByUser(Long idUser);
}
