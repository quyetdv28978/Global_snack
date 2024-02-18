package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTODonHang;
import com.dutn.be_do_an_vat.dto.DTOGioHang;
import com.dutn.be_do_an_vat.dto.DTOKhachHang;
import com.dutn.be_do_an_vat.entity.DiaChi;
import com.dutn.be_do_an_vat.entity.GioHang;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.exception.KhachHangException;
import com.dutn.be_do_an_vat.repositoty.IDiaChi;
import com.dutn.be_do_an_vat.repositoty.IKhachHang;
import com.dutn.be_do_an_vat.service.base_service.IKhachHangSer;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KhachHangSer implements IKhachHangSer {
    @Autowired
    private IKhachHang khachHangRes;
    @Autowired
    private IDiaChi diaChiRes;

    @Override
    public Set<DTODonHang> getDonHangs(Long idkh) {
        return null;
    }

    @Override
    public Set<DTOGioHang> getGioHangs(Long idkh) {
        return null;
    }

    @Override
    public List getAll() {
        return khachHangRes.findAll().stream().map(i -> DTOKhachHang.builder()
                .tenKhachHang(i.getName())
                .idkh(i.getId())
                .diaChis(diaChiRes.showDiaChisKhachHang(i.getId()).stream().map(DiaChi::getDiaChi).collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Optional add(DTOKhachHang dtoKhachHang) {
        KhachHang khachHang = MapperUtils.dtoToEntity(dtoKhachHang, KhachHang.class);
        return SaveKhachHang(dtoKhachHang, khachHang);
    }

    @Override
    public Optional update(Long id, DTOKhachHang dtoKhachHang) {
        if (!khachHangRes.findById(id).isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        KhachHang khachHang = MapperUtils.dtoToEntity(dtoKhachHang, KhachHang.class);
        khachHang.setId(id);
        diaChiRes.deleteAll((Iterable<? extends DiaChi>) diaChiRes.showDiaChisKhachHang(khachHang.getId()).iterator());
        return SaveKhachHang(dtoKhachHang, khachHang);
    }

    private Optional SaveKhachHang(DTOKhachHang dtoKhachHang, KhachHang khachHang) {
        khachHangRes.save(khachHang);
        diaChiRes.save(DiaChi.builder()
                .diaChi(dtoKhachHang.getDiaChis().stream().findFirst().get())
                .khachHang(khachHang)
                .build());
        return Optional.of(DTOKhachHang.builder()
                .tenKhachHang(khachHang.getName())
                .diaChis(diaChiRes.showDiaChisKhachHang(khachHang.getId()).stream().map(DiaChi::getDiaChi).collect(Collectors.toSet()))
                .build());
    }

    @Override
    public void delete(Long id) {
        Optional optional = khachHangRes.findById(id);
        if (!optional.isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        khachHangRes.deleteById(id);
    }

    @Override
    public DTOKhachHang search(Long id) {
        Optional<KhachHang> optional = khachHangRes.findById(id);
        if (!optional.isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        return DTOKhachHang.builder()
                .tenKhachHang(optional.get().getName())
                .diaChis(diaChiRes.showDiaChisKhachHang(optional.get().getId()).stream()
                        .map(DiaChi::getDiaChi)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List getAll_filter(int soLuong, int trang) {
        return khachHangRes.findAll(PageRequest.of(soLuong, trang)).stream().map(i -> DTOKhachHang.builder()
                .tenKhachHang(i.getName())
                .idkh(i.getId())
                .diaChis(diaChiRes.showDiaChisKhachHang(i.getId()).stream().map(DiaChi::getDiaChi).collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public KhachHang add(KhachHang kh) {
        return null;
    }
}