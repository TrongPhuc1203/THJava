package com.example.Lap3.services;

import com.example.Lap3.entity.Book;
import com.example.Lap3.entity.Category;
import com.example.Lap3.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;
@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public  Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }


    public List<Book> getAllCategories() {
        // Triển khai logic để lấy danh sách các category từ nguồn dữ liệu (database, API, v.v.)
        // Ví dụ:
        List<Book> categories = bookRepository.findAll();

        return categories;
    }
}
