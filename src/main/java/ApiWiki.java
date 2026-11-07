import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class ApiWiki {

    HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    public String workApi(String req) throws IOException {
        String url =
            Constants.CONST_URL_1 +
            URLEncoder.encode(req, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("User-Agent", "WikiSearchLab/1.0 (student project)")
            .timeout(Duration.ofSeconds(10))
            .build();
        try {
            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() != 200) {
                throw new ApiException(
                    "Сервер вернул код " + response.statusCode()
                );
            }
            return response.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("Запрос был прерван.");
        }
    }
}
