package com.dutn.be_do_an_vat.utility;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Const {
    public final static String USER_NOT_FOUND = "user.username";
    public final static String ROLE_NOT_FOUND = "role.exist";
    public final static String DELETE_SUSSCESS = "com.delete";


    public final static String SP_NOT_FOUND = "sanpham.exist";

    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");
    public final static String FILE_FORMAT = "file -> jpg, png";


}
