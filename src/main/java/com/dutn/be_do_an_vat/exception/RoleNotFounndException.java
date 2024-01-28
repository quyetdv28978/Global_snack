package com.dutn.be_do_an_vat.exception;

// Xứ lí ngoại lệ không tìm thấy role
public class RoleNotFounndException extends RuntimeException {
    public RoleNotFounndException(String msg) {
        super(msg);
    }
}
