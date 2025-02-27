    package com.example.App_SpringBoot_One.controller;

    import com.example.App_SpringBoot_One.exception.BookIdMismatchException;
    import com.example.App_SpringBoot_One.exception.BookNotFoundException;
    import com.example.App_SpringBoot_One.model.Book;
    import com.example.App_SpringBoot_One.repo.BookRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/books")
    public class BookController {

        @Autowired
        private BookRepository bookRepository;

        @GetMapping
        public Iterable<Book> findAll(){
            return bookRepository.findAll();
        }

        @GetMapping("/title/{bookTitle}")
        public List<Book> findByTitle(@PathVariable String bookTitle){
            return bookRepository.findByTitle(bookTitle);
        }

        @GetMapping("/{id}")
        public Book findOne(@PathVariable long id){
            return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Book create(@RequestBody Book book){
            Book book1 = bookRepository.save(book);
            return book1;
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable long id) {
            bookRepository.findById(id)
                    .orElseThrow(BookNotFoundException::new);
            bookRepository.deleteById(id);
        }


        @PutMapping("/{id}")
        public Book updateBook(@RequestBody Book book, @PathVariable long id){
            if(book.getId() != id){
                throw new BookIdMismatchException();
            }
            bookRepository.findById(id)
                    .orElseThrow(BookNotFoundException::new);
                    return bookRepository.save(book);
        }


    }
