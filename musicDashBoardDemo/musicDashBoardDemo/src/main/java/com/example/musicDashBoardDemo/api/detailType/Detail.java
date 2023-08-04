package com.example.musicDashBoardDemo.api.detailType;

import org.json.JSONObject;

public interface Detail {
    String apiUrl = "https://api.spotify.com/v1/";
    JSONObject returnDetail(String accessToken, String result_id, String type);
}
