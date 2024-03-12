package com.example.demo.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Const {
    public final static class SEND_MAIL_SUBJECT {
        public final static String CLIENT_REGISTER = "XÁC NHẬN TẠO MỚI THÔNG TIN NGƯỜI DÙNG";
    }

    public final static class TEMPLATE_FILE_NAME {
        public final static String CLIENT_REGISTER = "client";
    }

    public final static class SEND_MAIL_OTP {
        public final static String CLIENT_REGISTER = "XÁC NHẬN MÃ OTP";
    }

    public final static class TEMPLATE_OTP_NAME {
        public final static String CLIENT_REGISTER = "otp";
    }

    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");


    public static final String DOMAIN = "http://localhost:8080/static/images/";
}
