package com.example.cursework.controllers;

import com.example.cursework.models.Book;
import com.example.cursework.models.User;
import com.example.cursework.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String books(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
        model.addAttribute("books", bookService.bookList(name));
        model.addAttribute("user", bookService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", name);
        return "books";
    }

    @GetMapping("/book/{id}")
    public String bookInfo(@PathVariable Long id, Model model, Principal principal) {
        Book book = bookService.getBookByID(id);
        model.addAttribute("images", book.getImageList());
        model.addAttribute("book", bookService.getBookByID(id));
        model.addAttribute("user", bookService.getUserByPrincipal(principal));
        model.addAttribute("authorProduct", book.getUser());
        return "book-info";
    }

    @PostMapping("/book/create")
    public String createBook(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Book book, Principal principal) throws IOException {
        bookService.saveBook(principal, book, file1, file2, file3);
        return "redirect:/my/books";
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id, Principal principal) {
        bookService.deleteBook(bookService.getUserByPrincipal(principal), id);
        return "redirect:/my/books";
    }

    @GetMapping("/my/books")
    public String userProducts(Principal principal, Model model) {
        User user = bookService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("books", user.getBookList());
        return "my-books";
    }
}
