package com.example.cursework.services;

import com.example.cursework.models.Book;
import com.example.cursework.models.Image;
import com.example.cursework.models.User;
import com.example.cursework.repositories.BookRepository;
import com.example.cursework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<Book> bookList(String name) {
        if(name != null) return bookRepository.findByName(name);
        return bookRepository.findAll();
    }

    public void saveBook(Principal principal, Book book, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        book.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            book.addImageToBook(image1);
        }
        if(file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            book.addImageToBook(image2);
        }
        if(file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            book.addImageToBook(image3);
        }
        log.info("Saving new book. Book name: {}; User: {}", book.getName(), book.getUser().getEmail());
        Book bookFromDB = bookRepository.save(book);
        bookFromDB.setPreviewImageId(bookFromDB.getImageList().get(0).getId());
        bookRepository.save(book);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setFileName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setFileSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteBook(User user, long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if (book.getUser().getId().equals(user.getId())) {
                bookRepository.delete(book);
                log.info("Book with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this book with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Book with id = {} is not found", id);
        }
    }

    public Book getBookByID(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
