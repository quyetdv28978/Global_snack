package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.entity.KhachHang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IService<Q> {
    List getAll();
     Optional add(Q q);

    Optional update(Long id, Q q);
    void delete(Long id);
    Q search(Long id);
    List getAll_filter(int soLuong, int trang);

    KhachHang add(KhachHang kh);
}
