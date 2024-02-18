package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTODiaChi;
import com.dutn.be_do_an_vat.entity.DiaChi;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.repositoty.IDiaChi;
import com.dutn.be_do_an_vat.repositoty.IKhachHang;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DiaChiSer {
    @Autowired
    private IDiaChi diaChiRes;
    @Autowired
    private IKhachHang khachHang;

    @Transactional
    public void themDiaChi(DTODiaChi diaChi) {
        KhachHang khachHang2 = khachHang.findById(diaChi.getIdkh()).get();
        DiaChi diaChi1 = diaChiRes.save(DiaChi.builder()
                .diaChi(diaChi.getDiaChi())
                .khachHang(khachHang2)
                .build());
    }

    @Transactional
    public void updateDiaChi(DTODiaChi diaChi, long idDiaChi) {
        if (!diaChiRes.findById(idDiaChi).isPresent()) throw new UsernameNotFoundException(Const.Dia_Chi_NOT_FOUND);
        khachHang.findById(diaChi.getIdkh()).get();
        DiaChi diaChi1 = MapperUtils.dtoToEntity(diaChi, DiaChi.class);
        diaChi1.setId(idDiaChi);
        diaChiRes.save(diaChi1);
    }

    public void deleteDiaChi(String idDiaChi, long idkh) {
        System.out.println(idkh + idDiaChi);
        Optional<DiaChi> id = diaChiRes.findAll().stream().filter(i -> {
            if (i.getKhachHang().getId() == idkh && i.getDiaChi().equals(idDiaChi))
                return true;
            return false;
        }).findFirst();
//        System.out.println(id.isEmpty());
//        System.out.println(id);
        diaChiRes.deleteById(id.get().getId());
    }
}
