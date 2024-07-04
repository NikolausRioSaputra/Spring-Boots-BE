package com.nikolaus.reactive.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.nikolaus.reactive.exception.BookNotFoundException;
import com.nikolaus.reactive.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.test.web.reactive.server.WebTestClient;



import com.nikolaus.reactive.model.Book;
import com.nikolaus.reactive.model.ErrorResponse;
import com.nikolaus.reactive.service.BookService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = BookController.class)
@AutoConfigureWebTestClient
@Slf4j
@EnableR2dbcRepositories
public class BookControllerTest {

    private final WebTestClient webTestClient;

    @MockBean
    private final BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @MockBean
    ConnectionFactoryInitializer initializer;

    private final String url = "/api/books";

    @Autowired
    public BookControllerTest(WebTestClient webTestClient, BookService bookService) {
        this.webTestClient = webTestClient;
        this.bookService = bookService;
    }

    @Test
    void findBookByIdOk() {
        var book = Book.builder().id(1).title("test title 1").description("test description 1").build();
        log.info("book: {}",book);

        when(bookService.findById(book.getId())).thenReturn(Mono.just(book));

        String findBookByIdUrl = String.format("%s/%s", url, book.getId());
        log.info("url: {}",findBookByIdUrl);
        webTestClient
                .get()
                .uri(findBookByIdUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Book.class)
                .consumeWith(result -> {
                    var existBook = result.getResponseBody();

                    assert existBook != null;
                    assertEquals(book.getId(), existBook.getId());
                    assertEquals(book.getTitle(), existBook.getTitle());

                });
    }

    @Test
    void findBookByIdErrorNotFound() {
        var bookId =3;
        var bookNotFoundException = new BookNotFoundException(String.format("Book not found. ID:%s", bookId));

        when(bookService.findById(bookId)).thenReturn(Mono.error(bookNotFoundException));

        String findTodoByIdUrl = String.format("%s/%s", url, bookId);
        webTestClient
                .get()
                .uri(findTodoByIdUrl)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorResponse.class)
                .consumeWith(result -> {
                    var errorResponse = result.getResponseBody();

                    assertNotNull(errorResponse);
                    assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatusCode());
                    assertEquals("Book not found. ID:" + bookId , errorResponse.getMessage());
                });
    }
}