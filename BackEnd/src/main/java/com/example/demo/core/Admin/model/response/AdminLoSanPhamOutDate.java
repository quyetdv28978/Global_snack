package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminLoSanPhamOutDate {
    @Value("#{target.maLo}")
    String getMaLo();
    @Value("#{target.tenLo}")
    String getTenLo();
    @Value("#{target.outdate}")
    String getOutdate();
}
