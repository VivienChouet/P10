package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Entity.Dto.BookDTO;
import com.bibliotheque.API.Entity.Dto.NewBookDTO;
import com.bibliotheque.API.Entity.Mapper.BookMapper;
import com.bibliotheque.API.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookMapper bookMapper;


    @RequestMapping("/")
    public ResponseEntity <List<BookDTO>>listOfBooks() {
        List<Book> books = this.bookService.findAll();
        System.out.println(books);
        return new ResponseEntity<>(bookMapper.toDto(books), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> bookId(@PathVariable int id) {
        Book book = this.bookService.findById(id);
        System.out.println(book.title);
        if (book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Mapper = " + bookMapper.toDto(book));
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<NewBookDTO> newBook(@RequestBody NewBookDTO newBookDTO) {
        bookService.save(newBookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity <BookDTO> updateBook(@RequestBody BookDTO bookDTO){
        bookService.update(bookMapper.toEntity(bookDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/search")
    public ResponseEntity<BookDTO> ResearchByBookTitle (@RequestBody BookDTO bookDTO){
       Book book= this.bookService.findByTitle(bookDTO.getTitle());
        if (book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.OK);
    }

}
