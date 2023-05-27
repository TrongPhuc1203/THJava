package com.example.Lap3.controller;

import com.example.Lap3.entity.Book;
import com.example.Lap3.entity.Category;
import com.example.Lap3.services.BookService;
import com.example.Lap3.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
//    @PostMapping("/add")
//    public String addBook(@ModelAttribute("book") Book book){
//        bookService.addBook(book);
//        return "redirect:/books";
//    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
//        if (book == null) {
//            return "book/edit"; // Trang thông báo sách không tìm thấy
//        }

        List<Book> categories = bookService.getAllCategories(); // Lấy danh sách các category

        model.addAttribute("book", book);
        model.addAttribute("categories", categories);

        return "book/edit"; // Trang view để hiển thị thông tin đầu sách cần chỉnh sửa
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "book/edit"; // Trang thông báo sách không tìm thấy
        }

        // Cập nhật thông tin của đầu sách
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        // Tiếp tục cập nhật các thông tin khác (nếu có)

        bookService.updateBook(book);
        return "redirect:/books"; // Chuyển hướng người dùng đến trang danh sách đầu sách
    }

    @GetMapping({"/delete/{id}"})
    public String DeleteBook(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            // Xoá Book dựa vào Book có Id vừa tìm được
            bookService.deleteBook(id);
            System.out.println("Book đã được xóa");
        } else {
            System.out.println("Book chưa được xóa");
        }
        return "redirect:/books";
    }






}
