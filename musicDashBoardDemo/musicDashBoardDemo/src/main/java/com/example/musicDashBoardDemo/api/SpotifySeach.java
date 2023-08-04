package com.example.musicDashBoardDemo.api;

import com.example.musicDashBoardDemo.api.detailType.Detail;
import com.example.musicDashBoardDemo.api.searchType.Search;
import org.json.JSONObject;

public class SpotifySeach {

    private Search search;
    private String accesstoken = CreateToken.accesstoken();

    public SpotifySeach(Search search) {
        this.search = search;
    }

    private Detail detail;
    public SpotifySeach(Detail detail) { this.detail = detail; }

    public JSONObject getSearchResult(String text, int limit, int offset){
        return search.returnSearchResult(accesstoken, text, limit, offset);
    }

    public JSONObject getDetailResult(String id, String type) {
        return detail.returnDetail(accesstoken, id, type);
    }
}
