package micronautcore.issue2385.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import java.util.Map;

@Client("/books")
public interface BookClient {

    @Post
    Single<HttpResponse<Book>> saveInvalid(@Body Single<Map<String, ?>> bookInfo);
}
