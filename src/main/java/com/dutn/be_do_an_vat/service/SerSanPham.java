package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.dto.LoSanPhamDTO;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.exception.SanPhamNotFoundException;
import com.dutn.be_do_an_vat.repositoty.IDanhMuc;
import com.dutn.be_do_an_vat.repositoty.IDanhMucChiTiet;
import com.dutn.be_do_an_vat.repositoty.ISanPham;
import com.dutn.be_do_an_vat.repositoty.Image;
import com.dutn.be_do_an_vat.service.base_service.ISanPhamSer;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("sanphamser")
public class SerSanPham implements ISanPhamSer {
    @Autowired
    private ISanPham resSanPham;

    @Autowired
    private Image imageRes;

    @Autowired
    private IDanhMuc danhMucRes;

    @Autowired
    private IDanhMucChiTiet danhMucChiTietRes;

    @Autowired
    private LoSanPhamSer loSanPhamSer;

    /*
    Param: soluong -> số lượng phần tử của 1 trang
    trang -> số trang trong tổng số sản phầm

    return -> danh sách sản phẩm với số lượng = soluong
     */
    @Override
    public List getSanPhamBy(int soLuong, int trang) {
        return resSanPham.findAll(PageRequest.of(trang, soLuong)).toList();
    }

    @Override
    public List getSanPhamDiscount() {
        return resSanPham.showSanPhamNotDiscount();
    }

    /*
    Thêm sản phẩm
    Param: sanPham -> thuộc tính:tenSanPham;
    giaBan, soLuongTon, mota, tieuDe, images, idDanhMuc;

    return  -> sanpham
     */
    @Override
    @Transactional
    public SanPham themSanPham(DTOSanPham sanPham) {
        SanPham sanPham1 = resSanPham.save(MapperUtils.dtoToEntity(sanPham, SanPham.class));

        danhMucChiTietRes.save(DanhMucChiTiet.builder()
                .danhMuc(danhMucRes.findById(Long.valueOf(sanPham.getIdDanhMuc())).get())
                .sanPham(sanPham1)
                .build());
        Set<Images> iamges = saveIamges(sanPham, sanPham1);
        sanPham1.setImages(iamges);
        LoSanPhamDTO loSanPhamDTO = MapperUtils.dtoToEntity(sanPham, LoSanPhamDTO.class);
        loSanPhamSer.themLoSanPham(loSanPhamDTO, sanPham1);
        return sanPham1;
    }

    /*
        Sửa sản phẩm
    Param: sanPham -> thuộc tính:tenSanPham;
    giaBan, soLuongTon, mota, tieuDe, images, idDanhMuc;

    return  -> sanpham
     */
    @Override
    public SanPham updateSanPham(Long idsp, DTOSanPham sanPham) {
        SanPham sanPham1 = resSanPham.findById(idsp).orElseThrow(() -> new SanPhamNotFoundException(Const.SP_NOT_FOUND));
        sanPham1.setTenSanPham(sanPham.getTenSanPham());
        sanPham1.setMota(sanPham.getMota());
        sanPham1.setTieuDe(sanPham.getTieuDe());
        sanPham1.setGiaBan(sanPham.getGiaBan());
        sanPham1.setId(idsp);
        resSanPham.save(sanPham1);

        imageRes.deleteAllById(() -> sanPham1.getImages().stream().map(BaseEntity::getId).collect(Collectors.toSet()).iterator());
        danhMucChiTietRes.deleteAllById(() -> sanPham1.getDanhMucChiTiets().stream().map(BaseEntity::getId).iterator());

        Set<Images> iamges = saveIamges(sanPham, sanPham1);

        danhMucChiTietRes.save(DanhMucChiTiet.builder()
                .danhMuc(danhMucRes.findById(Long.valueOf(sanPham.getIdDanhMuc())).get())
                .sanPham(sanPham1)
                .build());

        sanPham1.setImages(iamges);
        return sanPham1;
    }

    private Set<Images> saveIamges(DTOSanPham sanPham, SanPham sanPham1) {
        return sanPham.getImages().stream().map(i ->
                imageRes.save(Images.builder()
                        .sanPham(sanPham1)
                        .image(i)
                        .build())
        ).collect(Collectors.toSet());
    }

    @Override
    public void deleteSanPham(Long idsp) {
        SanPham sanPham = resSanPham.findById(idsp).get();
        sanPham.setTrangThai(1);
        resSanPham.save(sanPham);
    }

    public List showSanPhamDistinctDiscount() {
        return resSanPham.findAll();
    }

    @Override
    public SanPham searchSanPhamBy(String... string) {
        return null;
    }
}