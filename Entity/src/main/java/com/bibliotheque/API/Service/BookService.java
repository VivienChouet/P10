package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Entity.Dto.BookDTO;
import com.bibliotheque.API.Entity.Dto.NewBookDTO;
import com.bibliotheque.API.Repository.BookRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    BookRepository bookRepository;

    /**
     * findAll
     * @return List<Book>
     */
    public List<Book> findAll() {
        logger.info("return list book");
        return bookRepository.findAll();
    }

    /**
     * findById
     * @param id
     * @return Book
     */
    public Book findById(int id) {
        logger.info("return book id =" + id);
        return  bookRepository.findById(id).get();
    }

    /**
     * Save
     * @param book
     */
    public void save(NewBookDTO newBookDTO) {
        logger.info("save new book = " + newBookDTO.getTitle());
        Book book = new Book();
        book.setAuthor(newBookDTO.getAuthor());
        book.setPublication(newBookDTO.getPublication());
        book.setTitle(newBookDTO.getTitle());
        book.setResume(newBookDTO.getResume());
        bookRepository.save(book);
    }

    /**
     * Update
     * @param book
     */
    public void update(int id, BookDTO bookUpdate) {
       Book book = this.bookRepository.findById(id).get();
        book.setTitle(bookUpdate.getTitle());
        book.setPublication(bookUpdate.getPublication());
        book.setResume(bookUpdate.getResume());
        book.setAuthor(bookUpdate.getAuthor());
       logger.info("update book name = " + book.title + " new name = " + bookUpdate.getTitle());
       bookRepository.save(book);
    }

    /**
     * findByTitle
     * @param name
     * @return Book
     */
    public Book findByTitle(String name) {
        logger.info("research book name = " + name);
       Book book = bookRepository.findByTitle(name);
        return book;
    }

    public void delete(int id) {
       logger.info("delete Bookid : " + id);
       Book book = this.bookRepository.findById(id).get();
       bookRepository.delete(book);
    }
}
