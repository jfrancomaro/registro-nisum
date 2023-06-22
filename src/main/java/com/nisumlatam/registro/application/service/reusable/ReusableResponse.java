package com.nisumlatam.registro.application.service.reusable;

import com.nisumlatam.registro.domain.response.GenericoResponse;
import com.nisumlatam.registro.infrastructure.adapter.exceptions.GlobalMessages;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ReusableResponse {

    protected GlobalMessages globalMessages;

    protected void procesarExito(final GenericoResponse response) {
        response.setRespuesta(response.getRespuesta() != null ? response.getRespuesta() : new Object());
        response.setMensaje(response.getMensaje() != null && !response.getMensaje().isEmpty() ?
                response.getMensaje() : globalMessages.msgProcesoExitoso());
    }

    protected GenericoResponse procesarRespuesta(final Object respuesta, final String mensaje) {
        GenericoResponse response = new GenericoResponse();
        response.setRespuesta(respuesta);
        response.setMensaje(mensaje);
        procesarExito(response);
        return response;
    }

    @Autowired
    public void setGlobalMessages(GlobalMessages globalMessages) {
        this.globalMessages = globalMessages;
    }
}
