package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.entity.KhachHang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IService<Q> {
    List getAll();
     Optional add(Q q);

    Optional update(UUID id, Q q);
    void delete(UUID id);
    Q search(UUID id);
    List getAll_filter(int soLuong, int trang);

    KhachHang add(KhachHang kh);
}
