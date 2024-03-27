package com.example.demo.infrastructure.constant;

import com.example.demo.infrastructure.status.ChiTietSanPhamStatus;
import com.example.demo.reponsitory.ChiTietSanPhamReponsitory;
import com.example.demo.reponsitory.ILoSanPhamRes;
import com.example.demo.reponsitory.SanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateTrangThaiSanPham {
    @Autowired
    private ILoSanPhamRes loSanPhamRes;
    @Autowired
    private SanPhamReponsitory sanPhamReponsitory;

    @Autowired
    private ChiTietSanPhamReponsitory chiTietSanPhamReponsitory;
    public void updateDongThoiTrangThaiSP(int trangThai) {

    }

//   return True -> toàn bộ biến thể sản phẩm hết hàng, đỗi trạng thái sản phẩm hết hàng
//    false trừ số lượng ở biến thể sản phẩm đi
    public boolean checkSoLuongBienTheSanPham(Integer idsp) {
        int sizeBienTheSanPham = sanPhamReponsitory.findAll().size();
        return sanPhamReponsitory.findById(idsp).get().getSanPhamChiTietList()
                .stream().filter(i -> i.getSoLuongTon() == 0).count() == sizeBienTheSanPham ? true : false;
    }

    //   return True -> toàn bộ biến thể sản phẩm hết hàng, đỗi trạng thái sản phẩm hết hàng
//    false trừ số lượng ở biến thể sản phẩm đi
    public boolean checkTrangThaiBienTheSanPham(Integer idsp) {
        int sizeBienTheSanPham = sanPhamReponsitory.findAll().size();
        return chiTietSanPhamReponsitory.findById(idsp).get().getSanPham().getSanPhamChiTietList()
                .stream().filter(i -> i.getTrangThai() == ChiTietSanPhamStatus.XOA).count() == sizeBienTheSanPham ? true : false;
    }
}
