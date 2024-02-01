package com.micros.email.factories;

import com.micros.email.dtos.EmailDto;
import com.micros.email.enums.StatusEmail;
import com.micros.email.models.EmailModel;
import org.springframework.beans.BeanUtils;

public class EmailModelFactory {

    public static EmailModel getInstance(){
        EmailModel emailModel = new EmailModel.EmailModelBuilder().build();
        emailModel.setStatusEmail(StatusEmail.DRAFT);
        return emailModel;
    }

    public static EmailModel getInstance(EmailDto emailDto){
        EmailModel emailModel = getInstance();
        BeanUtils.copyProperties(emailDto, emailModel);
        return emailModel;
    }

}
