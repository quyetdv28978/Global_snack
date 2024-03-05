package com.example.demo.core.Admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminLosanPhamRequest {
    //    thong tin lo san pham
    private String maLo;
    private String tenLo;
    private int ngayHetHan;
    private List<String> giaBan;

    private List<String> soLuongSize; // số lượng tồn
    private String nameNhaCungCap;
}
