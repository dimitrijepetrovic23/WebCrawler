import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Crawler {
    private final HttpClient client;
    Crawler(){
        client = HttpClient.newHttpClient();
    }
    String crawl(String x) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(x))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response.body() + "Status code: " + response.statusCode();
    }
}
