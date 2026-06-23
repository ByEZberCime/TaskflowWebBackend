package com.taskfloweb.fx.byezbercime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalException  {

    protected static <T> EntityCatch<T> createBody(T body, HttpStatus status) {
        EntityBody<T> entityBody = new EntityBody<>();
        entityBody.setBody(body);

        EntityCatch<T> entityCatch = new EntityCatch<>();
        entityCatch.setDateTimestamp(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        entityCatch.setAddress(Inet4Address.getLoopbackAddress().getHostAddress());
        entityCatch.setStatusType(status.name() +" " + status.value());
        entityCatch.setBody(entityBody);

        return entityCatch;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public static <T> EntityCatch<T> errorCatch(IllegalArgumentException ex,HttpStatus status) {
        EntityCatch<T> entityCatch = new EntityCatch<>();
        entityCatch.setErrorMessage(ex.getMessage());
        entityCatch.setException(ex);
        entityCatch.setBody(new EntityBody<>());
        entityCatch.setDateTimestamp(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        entityCatch.setAddress(Inet4Address.getLoopbackAddress().getHostAddress());
        entityCatch.setStatusType(status.name() +" " + status.value());

        return entityCatch;
    }

}
