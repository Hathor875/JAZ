package store.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.order.model.BookToOrder;

import java.util.UUID;

@Repository
public interface BookToOrderRepository extends JpaRepository<BookToOrder, UUID> {
}
