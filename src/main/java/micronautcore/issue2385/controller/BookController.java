package micronautcore.issue2385.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import micronautcore.issue2385.api.Book;

@Controller("/books")
public class BookController {

    @Post
    Single<Book> save(@Body Single<Book> book) {
        return book;
    }
}
