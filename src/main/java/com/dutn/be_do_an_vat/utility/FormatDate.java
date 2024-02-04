package com.dutn.be_do_an_vat.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public static LocalDate getDate(String date) {
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, simpleDateFormat);
    }
}
