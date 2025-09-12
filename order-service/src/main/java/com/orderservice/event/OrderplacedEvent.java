package com.orderservice.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderplacedEvent {

    //plain pojo class

    private String orderNumber;
}
