package com.dutn.be_do_an_vat.exception;

import com.dutn.be_do_an_vat.entity.SanPham;

public class SanPhamNotFoundException extends RuntimeException {
    public SanPhamNotFoundException(String msg) {
        super(msg);
    }
}
