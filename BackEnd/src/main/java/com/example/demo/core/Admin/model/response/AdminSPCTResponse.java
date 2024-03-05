package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface AdminSPCTResponse {

    Integer getStt();

    @Value("#{target.id}")
    Integer getId();

    @Value("#{target.tenSP}")
    String getTenSP();

    @Value("#{target.giaBan}")
    BigDecimal getGiaBan();

    @Value("#{target.trangThai}")
    Integer getTrangThai();


}
