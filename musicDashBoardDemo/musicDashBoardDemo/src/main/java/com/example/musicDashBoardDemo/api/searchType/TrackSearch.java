package com.example.musicDashBoardDemo.api.searchType;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TrackSearch implements Search{

    private String apiUrl = "https://api.spotify.com/v1/search?type=track";

    @Override
    public JSONObject returnSearchResult(String accessToken, String searchText, int limit, int offset) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);;
        headers.add("Host", "api.spotify.com");
        headers.add("Content-type", "application/json");
        String body = "";

        String searchUrl = this.apiUrl+"&query=" + searchText+"&limit="+limit+"&offset="+offset;

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(searchUrl, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();
        return new JSONObject(response);
    }
}
