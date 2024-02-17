package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Loai_Khuyen_Mai;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table()
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class LoaiKhuyenMai extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private E_Loai_Khuyen_Mai loaiKhuyenMai;

    @OneToMany(mappedBy = "loaiKhuyenMai")
    private Set<KhuyenMai> khuyenMais;
}
