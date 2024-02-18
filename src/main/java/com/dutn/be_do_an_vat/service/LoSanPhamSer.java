package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.LoSanPhamDTO;
import com.dutn.be_do_an_vat.entity.ChiTietLoSanPham;
import com.dutn.be_do_an_vat.entity.LoSanPham;
import com.dutn.be_do_an_vat.entity.SanPham;
import com.dutn.be_do_an_vat.repositoty.IChiTietSanPhamRes;
import com.dutn.be_do_an_vat.repositoty.ILoSanPhamRes;
import com.dutn.be_do_an_vat.service.base_service.ILoSanPhamSer;
import com.dutn.be_do_an_vat.utility.ConstMail;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoSanPhamSer implements ILoSanPhamSer {
    @Autowired
    private ILoSanPhamRes loSanPhamRes;
    @Autowired
    private IChiTietSanPhamRes chiTietSanPhamRes;
    @Autowired
    private AutoMail autoMail;

    @Override
    public List<LoSanPhamDTO> loSanPhams(Long idsp, int trangThai) {
        List<LoSanPhamDTO> loSanPhamDTO = loSanPhamRes.showAllLoSanPham(idsp, trangThai).stream()
                .map(i -> MapperUtils.entityToDTO(i, LoSanPhamDTO.class))
                .collect(Collectors.toList());
        return loSanPhamDTO;
    }

    @Override
    @Transactional
    public void themLoSanPham(LoSanPhamDTO loSanPhamDTO, SanPham sanPham) {
        LoSanPham loSanPham = loSanPhamRes.saveAndFlush(LoSanPham.builder()
                .ngayNhap(LocalDateTime.now())
                .ngaySanXuat(loSanPhamDTO.getNgaySanXuat())
                .ngayHetHan(loSanPhamDTO.getNgayHetHan())
                .soLuongTon(loSanPhamDTO.getSoLuongTon())
                .maLo(loSanPhamDTO.getMaLo())
                .giaBan(sanPham.getGiaBan())
                .giaNhap(loSanPhamDTO.getGiaNhap())
                .tongTien(loSanPhamDTO.getSoLuongTon() * sanPham.getGiaBan())
                .tenLo(loSanPhamDTO.getTenLo())
                .build());
        chiTietSanPhamRes.save(ChiTietLoSanPham.builder()
                .loSanPham(loSanPham)
                .sanPham(sanPham)
                .build());

        Long time = loSanPhamDTO.getNgaySanXuat().until(loSanPhamDTO.getNgayHetHan(), ChronoUnit.DAYS);
        autoMail.checkOutDate(ConstMail.time, time);

    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().until(LocalDate.of(2025, 2, 13), ChronoUnit.DAYS));
    }
}
