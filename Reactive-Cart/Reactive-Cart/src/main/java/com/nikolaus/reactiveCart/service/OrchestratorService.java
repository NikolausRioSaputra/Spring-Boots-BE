package com.nikolaus.reactiveCart.service;

import com.nikolaus.reactiveCart.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class OrchestratorService {
    private final WebClient webClient;

    public OrchestratorService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<Product> getproductById(Long id){
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }

}
