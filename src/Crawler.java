import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Crawler {
    String DOMAIN;
    Crawler(String x){
        this.DOMAIN = x;
    }
    String crawl(){
        Scanner scan = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(DOMAIN))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String webpage = response.body();
        scan.close();
        client.close();
        return webpage;
    }
}
