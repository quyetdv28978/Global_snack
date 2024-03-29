package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminLosanPhamRequest;
import com.example.demo.core.Admin.model.request.AdminSanPhamChiTietRequest;
import com.example.demo.core.Admin.model.response.*;
import com.example.demo.core.Admin.repository.AdChiTietSanPhamReponsitory;
import com.example.demo.core.Admin.repository.AdSanPhamReponsitory;
import com.example.demo.entity.LoSanPham;
import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.reponsitory.ILoSanPhamRes;
import com.example.demo.reponsitory.NhaCungCapReponsitory;
import com.example.demo.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class LoSanPhamSer {
    @Autowired
    private ILoSanPhamRes loSanPhamRes;

    @Autowired
    private NhaCungCapReponsitory nhaCungCapRes;

    @Autowired
    private AdSanPhamReponsitory sanPhamReponsitory;

    @Autowired
    private AdChiTietSanPhamReponsitory chiTietSanPhamReponsitory;
    @Value("${global.snack.gmailFrom}")
    private String mailFrom;
    @Value("${global.snack.gmailTo}")

    private String mailTo;
    @Autowired
    private JavaMailSender javaMailSender;

    public List<LoSanPham> getAllLoSanPham() {
        return loSanPhamRes.findAll();
    }

    public List<LoSanPham> getAllLoSanPhamByTrangThai(int trangThai) {
        return loSanPhamRes.findAllByTrangThai(trangThai);
    }

    public List<AdminLoSanPhamNotSoLuong> getAllLoSanPhamBySP(Integer idCTSP) {
        return loSanPhamRes.findAllBySanPhamChiTiet(idCTSP);
    }

    public List<AdminLoSanPham> getAllLoSanPhamBySPNotNull(Integer idCTSP) {
        return loSanPhamRes.findAllBySanPhamChiTiet(chiTietSanPhamReponsitory.findById(idCTSP).get());
    }

    public List<LoSanPham> getAllByTrangThai(int trangThai) {
        return loSanPhamRes.findAllByTrangThai(trangThai);
    }

    public LoSanPham addLoSanPham(AdminLosanPhamRequest losanPhamRequest) {
        return loSanPhamRes.save(LoSanPham.builder()
                .maLo(losanPhamRequest.getMaLo())
                .tenLo(losanPhamRequest.getTenLo())
                .ngayHetHan(losanPhamRequest.getNgayHetHan())
                .ngayNhap(LocalDateTime.now())
                .trangThai(0)
                .build());
    }

    public AdminSanPhamChiTiet2Response addLoSanPhamSanPhams(AdminSanPhamChiTietRequest loSanPham, Integer idSanPhamChiTiet) {
        LoSanPham loSanPhamMoi = loSanPhamRes.findById(Long.valueOf(loSanPham.getTenLo())).get();
        SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamReponsitory.findById(idSanPhamChiTiet).get();

        LoSanPham loSanPhamCu = loSanPhamRes.showLoSanPhamByIdCtsp(sanPhamChiTiet.getId());
        if (loSanPhamCu != null) {
            if (loSanPhamMoi.getNgayHetHan().isBefore(loSanPhamCu.getNgayHetHan())) {
                loSanPhamCu.setTrangThai(2);
                loSanPhamMoi.setTrangThai(1);
                loSanPhamRes.save(loSanPhamCu);
            } else if (loSanPhamCu.getId() == loSanPhamMoi.getId()) {
                loSanPhamMoi.setSoLuong(loSanPhamMoi.getSoLuong() + loSanPham.getSoLuongTon());
                sanPhamChiTiet.setAnh(Const.DOMAIN + loSanPham.getAnh());
                chiTietSanPhamReponsitory.save(sanPhamChiTiet);
                loSanPhamRes.save(loSanPhamMoi);
                return sanPhamReponsitory.getByid(idSanPhamChiTiet);
            } else {
                loSanPhamMoi.setTrangThai(2);
                loSanPhamRes.save(loSanPhamMoi);
            }
        } else loSanPhamMoi.setTrangThai(1);

        loSanPhamMoi.setSoLuong(loSanPham.getSoLuongTon());
        loSanPhamMoi.setSanPhamChiTiet(sanPhamChiTiet);
//        loSanPhamMoi.setTrangThai(1);
        sanPhamChiTiet.setTrangThai(1);
        loSanPhamRes.save(loSanPhamMoi);
        Integer slt = loSanPhamRes.sumSoLuongSanPham(sanPhamChiTiet.getId());
        sanPhamChiTiet.setSoLuongTon(slt == null ? loSanPham.getSoLuongTon() + sanPhamChiTiet.getSoLuongTon() : slt);
        chiTietSanPhamReponsitory.save(sanPhamChiTiet);
//        Đặt thời gian cho những lô sản phẩm gần hết hạn sử dụng
//        Long timeOutDate = loSanPhamRes.timeOutDate();
//        mail.addSPOutDate(timeOutDate, threadPoolTaskScheduler, DataSanPhamOutDate
//                .builder()
//                        .tenSanPham(sanPhamChiTiet.getSanPham().getTen())
//                        .tenLo(loSanPhamMoi.getTenLo())
//                        .maLo(loSanPhamMoi.getMaLo())
//                .build());
        return sanPhamReponsitory.getByid(idSanPhamChiTiet);
    }

    public void updateTrangThai(Long idLSP) {
        LoSanPham loSanPham = loSanPhamRes.findById(idLSP).get();
        loSanPham.setTrangThai(0);
        loSanPhamRes.save(loSanPham);
    }

    public AdminSanPhamChiTiet2Response updateLoSanPhamByIdCtSP(Long idLSP, Integer idCtsp) {
        LoSanPham loSanPhamMoi = loSanPhamRes.findById(idLSP).get();
        LoSanPham loSanPhamCu = loSanPhamRes.showLoSanPhamByIdCtsp(idCtsp);

        loSanPhamMoi.setTrangThai(1);
        if (loSanPhamCu.getTrangThai() != 4)
        loSanPhamCu.setTrangThai(2);

        loSanPhamRes.save(loSanPhamMoi);
        loSanPhamRes.save(loSanPhamCu);
        return sanPhamReponsitory.getByid(idCtsp);
    }

    public List<AdminLoSanPhamHanSuDung> loSanPhamHanSuDungs(int mount) {
       return loSanPhamRes.showHanSuDungSanPhamByMouth(mount);
    }
    @Scheduled(cron = "0 10 1 * * ?")
    @Async
    protected void checkLoSanPhamOutDate() {
        List<AdminLoSanPhamOutDate> spOutDate =  loSanPhamRes.timeOutDate();
        List<String> productOutDate = spOutDate.stream().filter(i -> Integer.parseInt(i.getOutdate()) <= 0)
                .map(j -> j.getTenLo()).collect(Collectors.toList());
        System.out.println(productOutDate);
        if (!spOutDate.isEmpty()) {
            String [] tenLo = Arrays.copyOf(spOutDate.stream().map(AdminLoSanPhamOutDate::getTenLo).toArray(),
                    spOutDate.stream().map(AdminLoSanPhamOutDate::getTenLo).toArray().length, String[].class);

            if (!productOutDate.isEmpty()) {
                productOutDate.forEach(i -> {
                    LoSanPham loSanPham = loSanPhamRes.findByTenLo(i).get();
                    loSanPham.setTrangThai(4);
                    SanPhamChiTiet sanPhamChiTiet = loSanPham.getSanPhamChiTiet();
                    sanPhamChiTiet.setTrangThai(0);
                    chiTietSanPhamReponsitory.save(sanPhamChiTiet);
                    loSanPhamRes.save(loSanPham);
                });
            }


            String [] maLo = Arrays.copyOf(spOutDate.stream().map(AdminLoSanPhamOutDate::getMaLo).toArray(),
                    spOutDate.stream().map(AdminLoSanPhamOutDate::getTenLo).toArray().length, String[].class);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailFrom);
            message.setTo(mailTo);
            message.setSubject("Sản phẩm sắp hết hạn");
            message.setText("Lô sản phẩm: " + String.join(",", tenLo) + " sắp hết hạn sử dụng vui lòng cấp thêm hàng. \n"
                    + "Mã lô: " + String.join(",", maLo));
            javaMailSender.send(message);
        }
    }
}
