package micronautcore.issue2385.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Single
import micronautcore.issue2385.api.BookClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class BookControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    ApplicationContext applicationContext = embeddedServer.getApplicationContext()

    void "test declarative http client error"() {

        given:
        Throwable ex = null
        HttpResponse<?> res = null
        BookClient bookClient = applicationContext.getBean(BookClient)

        when:
        bookClient.saveInvalid(Single.just(["name": "The Stand"]))
                .subscribe({ response -> res = response }, { error -> ex = error })

        then:
        new PollingConditions().eventually {
            ex != null
            res == null
        }
        noExceptionThrown()


    }
}
