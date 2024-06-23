package store.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "book_order_table")
public class BookToOrder {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID bookId;
    private Integer quantity;
}
