package store.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bookstore.api.OrdersApi;
import store.shop.service.OrderService;
import pl.bookstore.model.OrderDetails;
import pl.bookstore.model.OrderRequest;

import java.util.List;

@RestController
public class OrderController implements OrdersApi {
    private final OrderService orderHandlerService;

    public OrderController(OrderService orderHandlerService) {
        this.orderHandlerService = orderHandlerService;
    }

    @Override
    public ResponseEntity<OrderDetails> addNewOrder(OrderRequest orderData) {
        return ResponseEntity.ok(orderHandlerService.addNewOrder(orderData));
    }

    @Override
    public ResponseEntity<List<OrderDetails>> getAllOrder() {
        return ResponseEntity.ok(orderHandlerService.fetchAllOrders());
    }
}
