package store.shop.service;

import org.springframework.stereotype.Service;
import store.shop.mapper.OrderMapper;
import store.shop.model.Order;
import store.shop.repository.OrderRepository;
import pl.bookstore.model.OrderDetails;
import pl.bookstore.model.OrderRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    public OrderDetails addNewOrder(OrderRequest orderData) {
        Order order = orderMapper.toEntity(orderData);
        orderRepo.save(order);
        return orderMapper.toDetails(order);
    }

    public List<OrderDetails> fetchAllOrders() {
        return orderRepo.findAll().stream().map(orderMapper::toDetails).collect(Collectors.toList());
    }
}
