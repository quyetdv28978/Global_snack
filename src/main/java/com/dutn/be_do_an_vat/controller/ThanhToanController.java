package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.service.VNPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Thanh toán online", description = "Thanh toán online APIs")
public class ThanhToanController {
    @Autowired
    private VNPayService vnPayService;

    @Operation(summary = "API thanh toán", description = "redirect sang trang thanh toán VNPAY")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @Parameter(name = "amount", description = "Tổng tiền phải trả")
    @Parameter(name = "orderInfo", description = "Sản phẩm cần thanh toán")
    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @Operation(summary = "API thanh toán", description = "Lưu thông tin thanh toán vào db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping("/vnpay-payment")
    public ResponseEntity GetMapping(HttpServletRequest request) {
        return ResponseEntity.ok().body(vnPayService.orderReturn(request));
    }
}
