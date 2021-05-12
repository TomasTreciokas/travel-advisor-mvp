package lt.traveladvisor.mvp.general.wrapper;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

@Component
public class HttpClientWrapper {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    public HttpResponse<String> executeGetRequest(String url,
                                                  Map<String, String> paramMap) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder uriBuilder = new URIBuilder(url);
        paramMap.forEach(uriBuilder::addParameter);
        URI uri = uriBuilder.build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .timeout(Duration.ofSeconds(10))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
