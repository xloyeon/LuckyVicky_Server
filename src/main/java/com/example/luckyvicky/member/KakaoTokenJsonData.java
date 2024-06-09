package com.example.luckyvicky.member;

import com.example.luckyvicky.member.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoTokenJsonData {
    private final WebClient webClient;

    @Value("${kakao.token_uri}")
    String TOKEN_URI;

    @Value("${kakao.redirect_uri}")
    String REDIRECT_URI;

    @Value("${kakao.client_id}")
    String CLIENT_ID;
    private static final String GRANT_TYPE = "authorization_code";

    public KakaoTokenResponse getToken(String code){
        String uri = TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&code=" + code;
        System.out.println(uri);

        Flux<KakaoTokenResponse> response = webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(KakaoTokenResponse.class);

        return response.blockFirst();
    }

}
