package com.example.demo.core.khachHang.service;

import com.example.demo.core.khachHang.model.request.paymentmethod.CreatePayMentMethodTransferRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {

    String payWithVNPAYOnline(CreatePayMentMethodTransferRequest payModel, HttpServletRequest request) throws UnsupportedEncodingException;
}
