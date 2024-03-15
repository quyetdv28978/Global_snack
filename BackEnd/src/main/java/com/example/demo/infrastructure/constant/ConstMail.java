package com.example.demo.infrastructure.constant;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class ConstMail {
    public final static Long H_MS = 3600 * 1000L;

    public static Long time;
    public static ThreadPoolTaskScheduler threadPoolTaskScheduler;
}
