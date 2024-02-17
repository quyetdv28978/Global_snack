package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.entity.DanhMuc;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.repositoty.IDanhMuc;
import com.dutn.be_do_an_vat.service.base_service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("danhmucser")
@EnableScheduling
public class DanhMucSer implements IService<DanhMuc> {
    @Autowired
    private IDanhMuc danhMucRes;

    @Override
    public List getAll() {
        return danhMucRes.findAll();
    }

    @Override
    public Optional add(DanhMuc danhMuc) {
        return Optional.of(danhMucRes.save(danhMuc));
    }

    @Override
    public Optional update(Long id, DanhMuc danhMuc) {
        DanhMuc danhMuc1 = danhMucRes.findById(id).get();
        danhMuc1.setNameDanhMuc(danhMuc.getNameDanhMuc());
        return Optional.of(danhMucRes.save(danhMuc1));
    }

    @Override
    public void delete(Long id) {
        danhMucRes.deleteById(id);
    }

    @Override
    public DanhMuc search(Long id) {
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
