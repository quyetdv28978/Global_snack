package com.dutn.be_do_an_vat.config;

import com.dutn.be_do_an_vat.repositoty.ILoSanPhamRes;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.ConstMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class MailConfi {
    @Value("${global.snack.gmailFrom}")
    private String userName;
    @Value("${global.snack.gmailTo}")
    private String userNameTo;
    @Value("${global.snack.password}")
    private String pass;
    @Autowired
    private ILoSanPhamRes loSanPhamRes;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(userName);
        mailSender.setPassword(pass);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("mail_auto_" + new Random().nextInt(100000));
        taskScheduler.initialize();
        return taskScheduler;
    }

    /**
     * Auto gửi mail
     * Case 1: nếu không có lô sản phẩm nào có hsd <= 7 ngày -> sẽ lấy thời gian của lô sản phẩm có hạn sử dụng xa nhất
     * Làm thời gian để gửi mail
     * Case 2: ngược lên case 1
     *
     * @param time                       -> thời gian để lên lịch gửi mail
     * @param threadPoolTaskScheduler    -> tạo và quản lý lịch gửi mail
     * @param threadPoolTaskSchedulerOld -> shut down tác task không dùng nữa
     * @param products                   -> list các lô sản phẩm sắp hết hạn
     */
    public void schedledTaskAutoMail(Long time, ThreadPoolTaskScheduler threadPoolTaskScheduler,
                                     ThreadPoolTaskScheduler threadPoolTaskSchedulerOld, List<Object[]> products) {
        Set<String> tenSanPham = products.stream().map(i -> i[1].toString()).collect(Collectors.toSet());
        Set<String> maLo = products.stream().map(i -> i[3].toString()).collect(Collectors.toSet());
        Set<String> tenLo = products.stream().map(i -> i[2].toString()).collect(Collectors.toSet());
        if (threadPoolTaskSchedulerOld != null) threadPoolTaskSchedulerOld.shutdown();
        threadPoolTaskScheduler.schedule(() -> {
            List<Object[]> productsOutDay = loSanPhamRes.showOutDate7Day();
            ConstMail.time = ((Long) productsOutDay.get(0)[0] - 7) * 24 * 3600 * 1000;
            ConstMail.threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            ConstMail.threadPoolTaskScheduler.setPoolSize(1);
            ConstMail.threadPoolTaskScheduler.setThreadNamePrefix("quyet " + new Random().nextInt(1000));
            ConstMail.threadPoolTaskScheduler.initialize();
            schedledTaskAutoMail(ConstMail.time, ConstMail.threadPoolTaskScheduler, threadPoolTaskScheduler, loSanPhamRes.showOutDate7Day());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(userName);
            message.setTo(userNameTo);
            message.setSubject("Sản phẩm sắp hết hạn");
            message.setText("Sản Phẩm: " + tenSanPham + " sắp hết hạn sử dụng vui lòng cấp thêm hàng. \n"
                    + "Mã lô: " + maLo + ", Tên lô: " + tenLo + "\n");
            getJavaMailSender().send(message);
        }, Instant.now().plusMillis(time));
    }
}
