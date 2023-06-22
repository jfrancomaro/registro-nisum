package com.nisumlatam.registro.infrastructure.adapter.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class GlobalMessages {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    public GlobalMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String msgErrorGeneral() {
        return accessor.getMessage("msg.error.general");
    }

    public String msgValidationRequestFailed(String valorUno, String valorDos) {
        return accessor.getMessage("msg.validation.error", new Object[]{valorUno, valorDos});
    }

    public String msgValidationEmailFailed() {
        return accessor.getMessage("msg.validation.email");
    }

    public String msgValidationUserExists() {
        return accessor.getMessage("msg.validation.user");
    }
}
