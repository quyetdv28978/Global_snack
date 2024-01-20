package com.dutn.be_do_an_vat.entity.base_entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedBy
    private String createBy;
    @CreatedDate
    private LocalDate createDate;
    @LastModifiedBy
    private String lastUpdatedBy;
    @LastModifiedDate
    private LocalDate lastUpdatedDate;
}
