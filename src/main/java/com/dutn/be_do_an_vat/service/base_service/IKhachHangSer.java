package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTODonHang;
import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.dto.DTOKhachHang;
import com.dutn.be_do_an_vat.entity.KhachHang;

import java.util.Optional;
import java.util.Set;

public interface IKhachHangSer extends IService<DTOKhachHang> {
    public Set<DTODonHang> getDonHangs(Long idkh);

    public Set<DTOGioHang> getGioHangs(Long idkh);
}
