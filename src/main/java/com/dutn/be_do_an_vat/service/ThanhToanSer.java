package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.repositoty.IKieuThanhToan;
import com.dutn.be_do_an_vat.repositoty.IThanhToan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThanhToanSer {
    @Autowired
    private IThanhToan thanhToan;
    @Autowired
    private IKieuThanhToan kieuThanhToan;
}
