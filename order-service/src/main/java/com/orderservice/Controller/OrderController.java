package com.orderservice.Controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")

@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="product",fallbackMethod = "fallbackPlaceOrder")
    @TimeLimiter(name="product")
    @Retry(name="product")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest order){
  System.out.println(order);
  // by default it used another thread and free up the main thread for retry (not in the thread that handles http request)
        // placeorder method is offloaded to a backgound thread
       return   CompletableFuture.supplyAsync(()-> orderService.placeOrder(order));

    }

    // fallback
    // if the srvice is down the when the ewquest fails the fallback method is executed
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public CompletableFuture<String> fallbackPlaceOrder (OrderRequest order, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please order after some time");
    }

}
