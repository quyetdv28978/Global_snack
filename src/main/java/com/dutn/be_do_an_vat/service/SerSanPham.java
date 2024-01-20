package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.entity.SanPham;
import com.dutn.be_do_an_vat.repositoty.ISanPham;
import com.dutn.be_do_an_vat.service.base_service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class SerSanPham implements IService<SanPham> {
    @Autowired
    private ISanPham resSanPham;
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Optional add(SanPham sanPham) {
        return Optional.empty();
    }

    @Override
    public Optional update(UUID id, SanPham sanPham) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public SanPham search(UUID id) {
        return null;
    }

    @Override
    public List getAll_filter(int soLuong, int trang) {
        return null;
    }

    @Override
    public KhachHang add(KhachHang kh) {
        return null;
    }
}
