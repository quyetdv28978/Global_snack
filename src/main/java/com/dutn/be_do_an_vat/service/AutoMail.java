package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.config.MailConfi;
import com.dutn.be_do_an_vat.repositoty.ILoSanPhamRes;
import com.dutn.be_do_an_vat.utility.ConstMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AutoMail {
    @Autowired
    private MailConfi mailConfi;
    @Autowired
    private ILoSanPhamRes loSanPhamRes;

    public void checkOutDate(Long timeOld, Long timeNew) {
        List<Object[]> products = loSanPhamRes.showOutDate7Day().isEmpty() ?
                loSanPhamRes.showDateMin() : loSanPhamRes.showOutDate7Day();
        if (timeOld > timeNew) {
            ConstMail.threadPoolTaskScheduler.shutdown();
            ConstMail.threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            ConstMail.threadPoolTaskScheduler.initialize();
            mailConfi.schedledTaskAutoMail(timeNew, ConstMail.threadPoolTaskScheduler, null, products);
        }
    }
}
