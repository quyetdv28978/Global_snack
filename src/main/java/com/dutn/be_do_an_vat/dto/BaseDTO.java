package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.base_entity.E_Gioi_Tinh;

import java.time.LocalDate;
import java.util.UUID;

public class BaseDTO {
    private UUID id;
    private String name;
    private String fullName;
    private LocalDate DOB;
}
