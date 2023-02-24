package bg.softuni.books.service;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.Author;
import bg.softuni.books.model.entity.Book;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public long createBook(BookDTO newBook) {
        String authorName = newBook.getAuthor().getName();

        Optional<Author> authorOptional = authorRepository.findAuthorByName(authorName);

        Book newBookEntity = new Book();
        newBookEntity.setTitle(newBook.getTitle());
        newBookEntity.setIsbn(newBook.getIsbn());
        newBookEntity.setAuthor(authorOptional.orElseGet(() -> createNewAuthor(authorName)));

        return bookRepository.save(newBookEntity).getId();
    }

    private Author createNewAuthor(String authorName) {
        Author newAuthor = new Author();
        newAuthor.setName(authorName);

        return authorRepository.save(newAuthor);
    }

    public Optional<BookDTO> findBookById(Long bookId) {
        return bookRepository.findById(bookId).map(this::map);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::map).toList();
    }

    private BookDTO map(Book book) {
        AuthorDTO authorDTO = new AuthorDTO().setName(book.getAuthor().getName());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(authorDTO);
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());

        return bookDTO;
    }

    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
