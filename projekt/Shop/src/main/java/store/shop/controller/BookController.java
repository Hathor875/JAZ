package store.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bookstore.api.BooksApi;
import store.shop.service.BookService;
import pl.bookstore.model.BookDetails;
import pl.bookstore.model.BookRequest;
import pl.bookstore.model.BookType;

import java.util.List;
import java.util.UUID;

@RestController
public class BookController implements BooksApi {
    private final BookService bookHandlerService;

    public BookController(BookService bookHandlerService) {
        this.bookHandlerService = bookHandlerService;
    }

    @Override
    public ResponseEntity<BookDetails> addNewBook(BookRequest bookData) {
        return ResponseEntity.ok(bookHandlerService.addNewBook(bookData));
    }

    @Override
    public ResponseEntity<Void> deleteBook(UUID bookId) {
        bookHandlerService.removeBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookDetails>> getAllBooks() {
        return ResponseEntity.ok(bookHandlerService.fetchAllBooks());
    }

    @Override
    public ResponseEntity<BookDetails> getBookById(UUID bookId) {
        return ResponseEntity.ok(bookHandlerService.findBookById(bookId));
    }

    @Override
    public ResponseEntity<BookDetails> updateBook(UUID bookId, BookRequest bookData) {
        return ResponseEntity.ok(bookHandlerService.modifyBook(bookId, bookData));
    }

    @Override
    public ResponseEntity<List<BookDetails>> filterBooks(String title, BookType type, Integer maxPages, Double maxPrice, String sortBy) {
        return ResponseEntity.ok(bookHandlerService.filterAndSortBooks(title, type, maxPages, maxPrice, sortBy));
    }
}
