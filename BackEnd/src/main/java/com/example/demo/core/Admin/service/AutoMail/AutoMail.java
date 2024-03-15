//package com.example.demo.core.Admin.service.AutoMail;
//
//import com.example.demo.dto.DataSanPhamOutDate;
//import com.example.demo.entity.SanPhamOutDate;
//import com.example.demo.infrastructure.config.MailConfig;
//import com.example.demo.infrastructure.constant.ConstMail;
//import com.example.demo.infrastructure.mapper.MapperUtils;
//import com.example.demo.reponsitory.ILoSanPhamRes;
//import com.example.demo.reponsitory.SanPhamOutDateRepository;
//import com.example.demo.util.Const;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class AutoMail {
//    @Autowired
//    private MailConfig mailConfi;
//    @Value("${mailServer.email}")
//    private String email;
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private SanPhamOutDateRepository sanPhamOutDateRepository;
//    @Autowired
//    private ILoSanPhamRes loSanPhamRes;
//
//    /**
//     * Auto gửi mail
//     * Case 1: nếu không có lô sản phẩm nào có hsd <= 7 ngày -> sẽ lấy thời gian của lô sản phẩm có hạn sử dụng xa nhất
//     * Làm thời gian để gửi mail
//     * Case 2: ngược lên case 1
//     *
//     * @param time                       -> thời gian để lên lịch gửi mail
//     * @param threadPoolTaskScheduler    -> tạo và quản lý lịch gửi mail
//     * @param threadPoolTaskSchedulerOld -> shut down tác task không dùng nữa
//     * @param products                   -> list các lô sản phẩm sắp hết hạn
//     */
//    private void schedledTaskAutoMail(Long time, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
//        System.out.println(time);
//        System.out.println((time - 7) * 24 * ConstMail.H_MS);
//        threadPoolTaskScheduler.schedule(() -> {
//            List<DataSanPhamOutDate> listSpOutDate = sanPhamOutDateRepository.findAll().stream()
//                    .map(i -> MapperUtils.entityToDTO(i, DataSanPhamOutDate.class))
//                    .collect(Collectors.toList());
//            String[] maLo = Arrays.copyOf(listSpOutDate.stream().map(DataSanPhamOutDate::getMaLo).toArray()
//                    , listSpOutDate.stream().map(DataSanPhamOutDate::getMaLo).toArray().length, String[].class);
//            String[] tenLo = Arrays.copyOf(listSpOutDate.stream().map(DataSanPhamOutDate::getTenLo).toArray()
//                    , listSpOutDate.stream().map(DataSanPhamOutDate::getMaLo).toArray().length, String[].class);
//            System.out.println(maLo);
//            System.out.println("quyet haha");
//            //            SimpleMailMessage message = new SimpleMailMessage();
////            message.setFrom(email);
////            message.setTo(email);
////            message.setSubject("Sản phẩm sắp hết hạn");
////            message.setText("Lô sản phẩm: " + tenLo + " sắp hết hạn sử dụng vui lòng cấp thêm hàng. \n"
////                    + "Mã lô: " + maLo);
////            javaMailSender.send(message);
//        }, Instant.now().plusMillis(1200000));
//    }
//
//    public void addSPOutDate(Long time, ThreadPoolTaskScheduler threadPoolTaskScheduler,
//                             DataSanPhamOutDate products) {
//        if (ConstMail.time == null) {
//            addSanPhamOutDate(time, products);
//            ConstMail.threadPoolTaskScheduler = threadPoolTaskScheduler;
//            schedledTaskAutoMail(time, threadPoolTaskScheduler);
//        } else if (ConstMail.time == time) {
//            addSanPhamOutDate(time, products);
//        } else if (ConstMail.time > time) {
//            ConstMail.threadPoolTaskScheduler.getScheduledExecutor().shutdownNow();
//            ConstMail.threadPoolTaskScheduler = threadPoolTaskScheduler;
//            sanPhamOutDateRepository.deleteAll();
//            addSanPhamOutDate(time, products);
//            schedledTaskAutoMail(time, threadPoolTaskScheduler);
//        }
//    }
//    private void addSanPhamOutDate(Long time, DataSanPhamOutDate products) {
//        ConstMail.time = time;
//        sanPhamOutDateRepository.save(SanPhamOutDate.builder()
//                .tenLo(products.getTenLo())
//                .maLo(products.getMaLo())
//                .tenSanPham(products.getTenSanPham())
//                .build());
//    }
//}
