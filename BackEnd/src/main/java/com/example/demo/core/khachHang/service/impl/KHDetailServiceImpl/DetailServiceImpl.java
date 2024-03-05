package com.example.demo.core.khachHang.service.impl.KHDetailServiceImpl;

import com.example.demo.core.Admin.model.response.AdminSanPhamChiTietResponse;

import com.example.demo.core.khachHang.model.response.*;

import com.example.demo.core.khachHang.repository.KHSanPhamRepository;
import com.example.demo.core.khachHang.repository.KHchiTietSanPhamRepository;
import com.example.demo.core.khachHang.service.KHDetailService.DetaiService;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailServiceImpl implements DetaiService {

    @Autowired
    private KHchiTietSanPhamRepository repository;

    @Autowired
    private KHSanPhamRepository spRepo;

    @Override
    public AdminSanPhamChiTietResponse get(Integer id) {
        AdminSanPhamChiTietResponse optional = this.repository.get(id);
        return optional;
    }

    public List<SanPhamChiTiet> getAlls() {
        return repository.findAll();
    }

    @Override
    public List<KHSanPhamChiTiet2Response> getAllByIdSp(Integer idSP) {
        return spRepo.get(idSP);
    }

    @Override
    public KHSanPhamResponse findById(Integer id) {
        return spRepo.findByIdSP(id);
    }

    @Override
    public List<KhImageResponse> findImage(Integer id) {
        return spRepo.findImage(id);
    }

    @Override
    public List<KhTrongLuongRespon> findTrongLuong(Integer id) {
        spRepo.findTrongLuong(id).forEach(i -> System.out.println(i.getAnh() + " " + i.getId()));
        return spRepo.findTrongLuong(id);
    }


    @Override
    public KHSanPhamChiTiet2Response getSanPhamChiTietByTrongLuong(String idSize, Integer idSP) {
        return spRepo.getSanPhamChiTietAndTrongLuong(Integer.parseInt(idSize), idSP);
    }
}
