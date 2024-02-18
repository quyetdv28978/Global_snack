package com.dutn.be_do_an_vat.utility;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Const {
    public final static String USER_NOT_FOUND = "User not found";
    public final static String ROLE_NOT_FOUND = "Role not found";
    public final static String DELETE_SUSSCESS = "Delete susscess";
    public final static String SUSSCESS = "susscess";

    public final static String AUTHORIZATION = "You don't have access this resource";

    public final static String SP_NOT_FOUND = "Product not found";
    public final static String KH_NOT_FOUND = "Customer not found";
    public final static String Danh_Muc_NOT_FOUND = "category not found";
    public final static String Dia_Chi_NOT_FOUND = "Dia chi not found";

    public final static String PAYMENT_ERROR = "Thanh Toan that bai";

    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");
    public final static String FILE_FORMAT = "file -> jpg, png";

    public final static String NOTICE_MAIL_OLD_DATE = "";

    public final static Long H_MS = 3600 * 1000L;

}
