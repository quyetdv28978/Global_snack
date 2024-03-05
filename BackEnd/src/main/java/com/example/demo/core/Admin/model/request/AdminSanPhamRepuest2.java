package com.example.demo.core.Admin.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminSanPhamRepuest2 {

    private Integer id;

    @NotBlank(message = "không bỏ trống tên")
    private String ten;

    private Integer thuongHieu;

    private Integer loai;

    @NotBlank(message = "Vui lòng chọn trạng thái")
    private String trangThai;

    @NotBlank(message = "Vui lòng nhập mô tả")
    private String moTa;

    private String anh;

    private Integer khuyenMai;

    private List<String> giaBan;

    private List<String> soLuongSize; // số lượng tồn

    @Positive(message = "Không bỏ trống vật liệu")
    private Integer vatLieu;

    private List<String> idMauSac; // trọng lượng

    private List<String> imgMauSac;

    private List<String> imagesProduct;

    private List<String> tenLo;

}
