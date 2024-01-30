package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ThanhToan extends BaseEntity {
    private String maGiaoDich;
    private String tenNganHang;
    private String thongTinDonhang;
    private Double tongTien;
    @CreatedDate
    private LocalDate ngayThanhToan;
    private int trangThai;

    @OneToOne
    @JoinColumn(name = "id_httt")
    private HinhThucThanhToan hinhThucThanhToan;

    @OneToOne
    @JoinColumn(name = "id_dh")
    private DonHang donHang;
}
