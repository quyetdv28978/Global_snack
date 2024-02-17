package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.config.MailConfi;
import com.dutn.be_do_an_vat.dto.LoSanPhamDTO;
import com.dutn.be_do_an_vat.entity.LoSanPham;
import com.dutn.be_do_an_vat.entity.SanPham;
import com.dutn.be_do_an_vat.utility.ConstMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ILoSanPhamSer {
    List<LoSanPhamDTO> loSanPhams(Long idsp, int trangThai);

    void themLoSanPham(LoSanPhamDTO loSanPhamDTO, SanPham sanPham);

}
