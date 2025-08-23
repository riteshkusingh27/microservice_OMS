   package com.orderservice.service;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItems;
import com.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        // setting order
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList()
                .stream()
                .map(orderLineItemDto -> maptoDto(orderLineItemDto, order))  // pass order here
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepo.save(order);
    }

    private OrderLineItems maptoDto(OrderLineItemsDto order , Order ords) {

        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(order.getSkuCode());
        orderLineItems.setOrder(ords);
        orderLineItems.setQuantity(order.getQuantity());
        orderLineItems.setPrice(order.getPrice());
        return orderLineItems;
    }
}
