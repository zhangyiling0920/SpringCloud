package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code; //200
    private String message; //success
    private T data; //payment/order

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}

//Json封装体