package com.dutn.be_do_an_vat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {
    @Autowired
    private MessageSource messageSource;

    /**
     *
     * @param code truyền vào biến constant ở MessageConstant
     * @return giá trị trong message_vi.properties
     */
    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, Locale.getDefault());
        } catch (Exception ex) {
            return code;
        }
    }
}
