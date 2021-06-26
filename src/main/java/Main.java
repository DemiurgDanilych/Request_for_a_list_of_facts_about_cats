import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static ObjectMapper mapper = new ObjectMapper();
	public static final String REMOTE_SERVICE_URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
	
	public static void main(String[] args) throws IOException {
		
		CloseableHttpClient httpClient = createHttpClient();
		
		HttpGet request = new HttpGet(REMOTE_SERVICE_URL);
		request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
		
		CloseableHttpResponse response = httpClient.execute(request);
		
		List<ClientsResponse> clientsResponseList = mapper.readValue(response.getEntity().getContent()
				, new TypeReference<List<ClientsResponse>>() {});
		
		List<ClientsResponse> sortResponse = clientsResponseList.stream()
				.filter(value -> value.getUpvotes() != null && (!value.getUpvotes().equals(0)))
				.collect(Collectors.toList());
		sortResponse.forEach(System.out::println);
	}
	
	
	private static CloseableHttpClient createHttpClient() {
		CloseableHttpClient methodHttpClient = HttpClientBuilder.create()
				.setDefaultRequestConfig(RequestConfig.custom()
						.setConnectionRequestTimeout(5000)
						.setSocketTimeout(30000)
						.setRedirectsEnabled(false)
						.build())
				.build();
		return methodHttpClient;
	}
}
