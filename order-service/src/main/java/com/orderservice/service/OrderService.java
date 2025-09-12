package com.orderservice.service;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.inventoryresponse;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItems;
import com.orderservice.event.OrderplacedEvent;
import com.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final WebClient.Builder webClient;
    //inject kafka template here
    private final KafkaTemplate<String,OrderplacedEvent> kafkaTemplate;


    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        // setting order
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList()
                .stream()
                .map(orderLineItemDto -> maptoDto(orderLineItemDto, order))  // pass order here
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        List<String> skucode = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        // call inventory service and place order if product is in stock !!! using webclient {webflux}
        // customer order many orders with different sku code , collect sku code as list

        log.info("skucode: {}", skucode);
        System.out.println("skucode: " + skucode);
        inventoryresponse[] inventoryresponsearray = webClient.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skucode", skucode.toArray(new String[0])).build())

                .retrieve()
                .bodyToMono(inventoryresponse[].class)
                .block();
        boolean allProductinStock = Arrays.stream(inventoryresponsearray).allMatch(inventoryresponse::isInStock);
        if (allProductinStock) {
            orderRepo.save(order);
            // send order placed event to notification service
            kafkaTemplate.send("notificationTopic",  new OrderplacedEvent(order.getOrderNumber()));
            return "Order Placed Succcesfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }

    private OrderLineItems maptoDto(OrderLineItemsDto order, Order ords) {

        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(order.getSkuCode());
        orderLineItems.setOrder(ords);
        orderLineItems.setQuantity(order.getQuantity());
        orderLineItems.setPrice(order.getPrice());
        return orderLineItems;
    }
}
