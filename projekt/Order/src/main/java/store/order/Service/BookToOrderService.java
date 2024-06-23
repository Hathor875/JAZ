package store.order.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import store.order.mapper.BookToOrderMapper;
import store.order.model.BookToOrder;
import store.order.repository.BookToOrderRepository;
import pl.bookstore.model.BookToOrderDetails;
import pl.bookstore.model.BookToOrderRequest;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookToOrderService {

    private final BookToOrderRepository bookOrderRepo;
    private final BookToOrderMapper bookOrderMapper;

    public BookToOrderService(BookToOrderRepository bookOrderRepo, BookToOrderMapper bookOrderMapper) {
        this.bookOrderRepo = bookOrderRepo;
        this.bookOrderMapper = bookOrderMapper;
    }

    public List<BookToOrderDetails> processBooksToOrder(List<BookToOrderRequest> bookOrderRequests) {
        List<BookToOrder> processedOrders = new ArrayList<>();

        for (BookToOrderRequest bookOrderRequest : bookOrderRequests) {
            BookToOrder bookOrderEntity = bookOrderMapper.toEntity(bookOrderRequest);
            Optional<BookToOrder> existingBookOrder = bookOrderRepo.findById(bookOrderEntity.getBookId());
            if (existingBookOrder.isPresent()) {
                BookToOrder orderToUpdate = existingBookOrder.get();

                if (bookOrderRequest.getVisitorCount() % 10 == 0) {
                    int countToOrder = bookOrderRequest.getVisitorCount() / 10;
                    orderToUpdate.setQuantity(orderToUpdate.getQuantity() + countToOrder);
                }
                bookOrderRepo.delete(bookOrderEntity);
                bookOrderRepo.save(orderToUpdate);
                processedOrders.add(orderToUpdate);
            } else {
                BookToOrder newBookOrder = bookOrderMapper.toEntity(bookOrderRequest);
                bookOrderRepo.save(newBookOrder);
                processedOrders.add(newBookOrder);
            }
        }

        return processedOrders.stream()
                .map(bookOrderMapper::toDetails)
                .collect(Collectors.toList());
    }

    public byte[] createPdfReport() {
        List<BookToOrder> bookOrders = bookOrderRepo.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        for (BookToOrder bookOrder : bookOrders) {
            document.add(new Paragraph("Book ID: " + bookOrder.getBookId()));
            document.add(new Paragraph("Quantity: " + bookOrder.getQuantity()));
            document.add(new Paragraph("\n"));
        }

        return byteArrayOutputStream.toByteArray();
    }
}
