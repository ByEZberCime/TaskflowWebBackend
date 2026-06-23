package com.taskfloweb.fx.byezbercime.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
@NoArgsConstructor
public class EntityCatch<T> {

    private String dateTimestamp;
    private String statusType;
    private String address;
    private String errorMessage = "nothing";
    private Exception exception;

    private EntityBody<T> entiy;

    public static <T> EntityCatch<T> handlerBody(T body, HttpStatus status) {
        return GlobalException.createBody(body, status);
    }

}
