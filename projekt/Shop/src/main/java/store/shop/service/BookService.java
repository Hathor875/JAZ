package store.shop.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.shop.mapper.BookMapper;
import store.shop.model.Book;
import store.shop.repository.BookRepository;
import pl.bookstore.model.BookDetails;
import pl.bookstore.model.BookRequest;
import pl.bookstore.model.BookType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepo;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    public List<BookDetails> fetchAllBooks() {
        return bookRepo.findAll().stream().map(bookMapper::toDetails).collect(Collectors.toList());
    }

    public BookDetails findBookById(UUID bookId) {
        incrementVisitCount(bookId);
        return bookMapper.toDetails(bookRepo.getReferenceById(bookId));
    }

    public List<BookDetails> filterAndSortBooks(String title, BookType type, Integer maxPages, Double maxPrice, String sortBy) {
        Specification<Book> spec = Specification.where(null);

        if (title != null) {
            spec = spec.and(BookSpecifications.hasTitle(title));
        }
        if (type != null) {
            spec = spec.and(BookSpecifications.hasBookType(type));
        }
        if (maxPages != null) {
            spec = spec.and(BookSpecifications.hasPagesLessThanOrEqualTo(maxPages));
        }
        if (maxPrice != null) {
            spec = spec.and(BookSpecifications.hasPriceLessThanOrEqualTo(maxPrice));
        }

        Sort sort = Sort.by(sortBy != null ? sortBy : "id");

        return bookRepo.findAll(spec, sort).stream().map(bookMapper::toDetails).collect(Collectors.toList());
    }

    @Transactional
    public void incrementVisitCount(UUID bookId) {
        Book book = bookRepo.getReferenceById(bookId);
        book.setVisitorCount(book.getVisitorCount() + 1);
        Book updatedBook = book;
        bookRepo.delete(book);
        bookRepo.save(updatedBook);
    }

    public BookDetails addNewBook(BookRequest bookData) {
        Book entity = bookMapper.toEntity(bookData);
        bookRepo.save(entity);
        return bookMapper.toDetails(entity);
    }

    public void removeBook(UUID bookId) {
        bookRepo.delete(bookRepo.getReferenceById(bookId));
    }

    public BookDetails modifyBook(UUID bookId, BookRequest bookData) {
        Book existingBook = bookRepo.getReferenceById(bookId);

        Book updatedBook = bookMapper.toEntity(bookData);
        updatedBook.setBookId(existingBook.getBookId());
        bookRepo.save(updatedBook);

        return bookMapper.toDetails(updatedBook);
    }
}
