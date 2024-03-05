package com.example.demo.core.khachHang.service.impl;

import com.example.demo.core.khachHang.model.request.hoadonchitiet.KHHoaDonChiTietRequest;
import com.example.demo.core.khachHang.model.request.paymentmethod.CreatePayMentMethodTransferRequest;
import com.example.demo.core.khachHang.repository.KHChiTietSPRepository;
import com.example.demo.core.khachHang.service.PaymentService;
import com.example.demo.infrastructure.config.PaymentConfig;
import com.example.demo.infrastructure.constant.VNPayConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    KHChiTietSPRepository ctspRepo;


    private VNPayConstant VnPayConstant;

    @Override
    public String payWithVNPAYOnline(CreatePayMentMethodTransferRequest payModel, HttpServletRequest request) throws UnsupportedEncodingException {

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        StringBuilder products = new StringBuilder();
        payModel.getHdct().forEach(i -> {
            products.append(String.join("+", i.getIdCTSP() +"", i.getSoLuong() +"", i.getDonGia() +"+"));
            products.append("/");
        });

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        cld.add(Calendar.MINUTE, 15);

        String vnp_ExpireDate = formatter.format(cld.getTime());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConstant.vnp_Version);
        vnp_Params.put("vnp_Command", VnPayConstant.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VnPayConstant.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(payModel.vnp_Ammount + "00"));
        vnp_Params.put("vnp_BankCode", VnPayConstant.vnp_BankCode);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", VnPayConstant.vnp_CurrCode);
        vnp_Params.put("vnp_IpAddr", PaymentConfig.getIpAddress(request));
        vnp_Params.put("vnp_Locale", VnPayConstant.vnp_Locale);
        vnp_Params.put("vnp_OrderInfo", products.toString());
        vnp_Params.put("vnp_OrderType", payModel.vnp_OrderType);
        vnp_Params.put("vnp_ReturnUrl", VnPayConstant.vnp_ReturnUrl);
        vnp_Params.put("vnp_TxnRef", "HD" + RandomStringUtils.randomNumeric(6) + "-" + vnp_CreateDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
//        vnp_Params.put("vnp_products", products.toString());

        List fieldList = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldList);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        Iterator itr = fieldList.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append("=");
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append("=");
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append("&");
                    hashData.append("&");
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(VnPayConstant.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
//        queryUrl += "&vnp_products=" + products.toString();
        String paymentUrl = VnPayConstant.vnp_Url + "?" + queryUrl;
        System.out.println(paymentUrl);
        return paymentUrl;
    }

    public static void main(String[] args) {
        List<KHHoaDonChiTietRequest> a = List.of(KHHoaDonChiTietRequest.builder()
                        .idCTSP(22).soLuong(30)
                .build(), KHHoaDonChiTietRequest.builder()
                .idCTSP(23).soLuong(40)
                .build()
        );

        Map<String, String> b = new HashMap<>();
        b.put("a", a.stream().map(i -> String.join("/"
        , i.getIdCTSP() + "", i.getDonGia() +"", i.getSoLuong() +""))
                .toString());
    }
}
