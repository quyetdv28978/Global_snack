package com.example.demo.core.khachHang.model.response;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

public interface KhTrongLuongRespon {
        @Value("#{target.idTrongLuong}")
        Integer getId();

        @Value("#{target.trongLuong}")
        String getValue();
        @Value("#{target.donVi}")
        String getDonVi();

        @Value("#{target.anh}")
        String getAnh();
}
