package Oregano.Backend.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;


@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient FetchJobListings_WebClient() {
        // WebClient를 생성하고 DefaultUriBuilderFactory에 EncodingMode를 NONE으로 설정
        UriBuilderFactory factory = new DefaultUriBuilderFactory();
        ((DefaultUriBuilderFactory) factory).setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return WebClient.builder()
            .uriBuilderFactory(factory)
            .build();
    }
}