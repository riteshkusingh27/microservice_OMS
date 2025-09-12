package com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderplacedEvent {

    //plain pojo class
    // recommend not to share any classes between services
    //have own version of event class in each service

    private String orderNumber;
}
