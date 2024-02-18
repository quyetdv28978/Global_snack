package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTODanhMuc;
import com.dutn.be_do_an_vat.entity.DanhMuc;
import com.dutn.be_do_an_vat.entity.DanhMucChiTiet;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.exception.DanhMucException;
import com.dutn.be_do_an_vat.repositoty.IDanhMuc;
import com.dutn.be_do_an_vat.repositoty.IDanhMucChiTiet;
import com.dutn.be_do_an_vat.repositoty.ISanPham;
import com.dutn.be_do_an_vat.service.base_service.IService;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("danhmucser")
@EnableScheduling
public class DanhMucSer implements IService<DTODanhMuc> {
    @Autowired
    private IDanhMuc danhMucRes;
    @Autowired
    private IDanhMucChiTiet danhMucChiTietRes;
    @Autowired
    private ISanPham sanPhamRes;

    @Override
    public List getAll() {
        return danhMucRes.findAll().stream().map(i -> DTODanhMuc.builder()
                .iddm(i.getId())
                .nameDanhMuc(i.getNameDanhMuc())
                .sanPhams(i.getDanhMucChiTiet().stream().map(sanpham -> sanpham.getSanPham().getTenSanPham())
                        .collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional add(DTODanhMuc danhMuc) {
        DanhMuc danhMuc1 = danhMucRes.save(MapperUtils.dtoToEntity(danhMuc, DanhMuc.class));
        List<DanhMucChiTiet> danhMucChiTiets = danhMucChiTietRes.saveAll(danhMuc.getSanPhams().stream().map(i -> DanhMucChiTiet.builder()
                .danhMuc(danhMuc1)
                .sanPham(sanPhamRes.findById(Long.parseLong(String.valueOf(i))).get())
                .build()).collect(Collectors.toSet()));
        danhMuc1.setDanhMucChiTiet(danhMucChiTiets.stream().collect(Collectors.toSet()));
        return danhMucRes.findById(danhMuc1.getId());
    }

    @Override
    public Optional update(Long id, DTODanhMuc danhMuc) {
        Optional<DanhMuc> optional = danhMucRes.findById(id);
        if (!optional.isPresent())
            throw new DanhMucException(Const.Danh_Muc_NOT_FOUND);
        DanhMuc danhMuc1 = optional.get();
        danhMuc1.setNameDanhMuc(danhMuc.getNameDanhMuc());

        danhMuc1.getDanhMucChiTiet().addAll(danhMuc.getSanPhams().stream().map(i -> DanhMucChiTiet.builder()
                .danhMuc(danhMuc1)
                .sanPham(sanPhamRes.findById(Long.parseLong(String.valueOf(i))).get())
                .build()).collect(Collectors.toSet()));
        return Optional.of(danhMucRes.save(danhMuc1));
    }

    @Override
    public void delete(Long id) {
        danhMucRes.deleteById(id);
    }

    @Override
    public DTODanhMuc search(Long id) {
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
