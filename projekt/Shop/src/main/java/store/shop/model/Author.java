package store.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "author_table")
public class Author {
    @Id
    @GeneratedValue
    private UUID authorId;

    private String firstName;
    private String lastName;

    @OneToMany
    private List<Book> books;
}
