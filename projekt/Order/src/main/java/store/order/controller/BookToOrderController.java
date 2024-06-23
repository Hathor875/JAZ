package store.order.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bookstore.api.OrderReportApi;
import store.order.Service.BookToOrderService;
import pl.bookstore.model.BookToOrderDetails;
import pl.bookstore.model.BookToOrderRequest;

import java.util.List;

@RestController
public class BookToOrderController implements OrderReportApi {

    private final BookToOrderService bookOrderService;

    public BookToOrderController(BookToOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public ResponseEntity<List<BookToOrderDetails>> sendBookToOrder(List<BookToOrderRequest> bookOrderRequests) {
        return ResponseEntity.ok(bookOrderService.processBooksToOrder(bookOrderRequests));
    }

    @Override
    public ResponseEntity<Resource> generatePDFReport() {
        byte[] pdfData = bookOrderService.createPdfReport();

        ByteArrayResource pdfResource = new ByteArrayResource(pdfData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "books_report.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfResource);
    }
}
