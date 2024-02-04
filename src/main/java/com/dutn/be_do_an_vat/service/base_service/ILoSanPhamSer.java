package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.LoSanPhamDTO;
import com.dutn.be_do_an_vat.entity.LoSanPham;
import com.dutn.be_do_an_vat.entity.SanPham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILoSanPhamSer {
    List<LoSanPhamDTO> loSanPhams(Long idsp, int trangThai);

    public void themLoSanPham(LoSanPhamDTO loSanPhamDTO, SanPham sanPham);
}
