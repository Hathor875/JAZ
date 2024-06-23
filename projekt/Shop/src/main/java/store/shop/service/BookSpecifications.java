package store.shop.service;

import org.springframework.data.jpa.domain.Specification;
import store.shop.model.Book;
import pl.bookstore.model.BookType;

public class BookSpecifications {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Book> hasBookType(BookType type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("bookType"), type);
    }

    public static Specification<Book> hasPagesLessThanOrEqualTo(Integer pages) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("pages"), pages);
    }

    public static Specification<Book> hasPriceLessThanOrEqualTo(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
}
