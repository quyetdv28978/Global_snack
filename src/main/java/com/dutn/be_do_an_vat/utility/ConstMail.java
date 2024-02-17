package com.dutn.be_do_an_vat.utility;

import com.dutn.be_do_an_vat.config.MailConfi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class ConstMail {
    public static Long time;
    public static ThreadPoolTaskScheduler threadPoolTaskScheduler;
}
