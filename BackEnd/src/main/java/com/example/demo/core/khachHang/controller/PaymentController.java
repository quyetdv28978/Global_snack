package com.example.demo.core.khachHang.controller;

import com.example.demo.core.khachHang.model.request.hoadon.HoaDonRequest;
import com.example.demo.core.khachHang.model.request.hoadonchitiet.KHHoaDonChiTietRequest;
import com.example.demo.core.khachHang.model.request.paymentmethod.CreatePayMentMethodTransferRequest;
import com.example.demo.core.khachHang.service.HoaDonService;
import com.example.demo.core.khachHang.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api")
public class PaymentController {
    @Value("${frontend.base-endpoint}")
    private  String BASE_FRONTEND_ENDPOINT;

    @Autowired
    PaymentService paymentService;

    @Autowired
    HoaDonService hoaDonService;
    @PostMapping("/payment-vnpay")
    public String pay(@RequestBody CreatePayMentMethodTransferRequest payModel, HttpServletRequest request){
        System.out.println(payModel);
        try {
            return paymentService.payWithVNPAYOnline(payModel, request) ;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/payment-callback")
    public ResponseEntity<Boolean> paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        queryParams.forEach((a,b) -> System.out.println(a + " " + "key: " + queryParams.get(a)));
        String products = queryParams.get("vnp_OrderInfo");
        List<KHHoaDonChiTietRequest> listHDs = new ArrayList<>();
        String[] productArray = products.split("/");
        String[] productArray2 = products.split("/");
        int checkSizeProducts = 0, checkCharProduct = 0;
        for (int i = 0; i < products.split("/").length; i++) {
            productArray2 = productArray[i].split("\\+");
            listHDs.add(KHHoaDonChiTietRequest.builder()
                    .idCTSP(Integer.parseInt(productArray2[0]))
                    .soLuong(Integer.parseInt(productArray2[1]))
                    .donGia(new BigDecimal(productArray2[2]))
                    .build());
        }
        if ("00".equals(vnp_ResponseCode)) {
            listHDs.forEach(System.out::println);
//            response.sendRedirect(BASE_FRONTEND_ENDPOINT + "/gio-hang/thanh-toan/thanh-cong");
            response.sendRedirect("http://localhost:5173/gio-hang/thanh-toan/thanh-cong");
            return ResponseEntity.ok(true);
        } else{
//            response.sendRedirect(BASE_FRONTEND_ENDPOINT + "/gio-hang/thanh-toan/that-bai");
            response.sendRedirect("http://localhost:5173/san-pham/gio-hang/thanh-toan/that-bai");
        }
        return ResponseEntity.ok(false);
    }
}
