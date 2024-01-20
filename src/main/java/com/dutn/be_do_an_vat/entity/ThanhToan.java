package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ThanhToan extends BaseEntity {
    private Double tongTien;
    @CreatedDate
    private LocalDateTime ngayThanhToan;
    private int trangThai;
}
