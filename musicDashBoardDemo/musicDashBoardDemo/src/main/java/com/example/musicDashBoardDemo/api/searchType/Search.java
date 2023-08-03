package com.example.musicDashBoardDemo.api.searchType;

import org.json.JSONObject;

public interface Search {
    public JSONObject returnSearchResult(String accessToken, String searchText, int limit, int offset);
}
