package com.example.musicDashBoardDemo.controller;

import com.example.musicDashBoardDemo.api.CreateToken;
import com.example.musicDashBoardDemo.api.SpotifySeach;
import com.example.musicDashBoardDemo.api.searchType.AlbumSearch;
import com.example.musicDashBoardDemo.api.searchType.ArtistSearch;
import com.example.musicDashBoardDemo.api.searchType.Search;
import com.example.musicDashBoardDemo.api.searchType.TrackSearch;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {


    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="references") String references,
                         @RequestParam(name="searchText") String searchText,
                         @RequestParam(name="limit", required = false, defaultValue = "10") Integer limit,
                         @RequestParam(name="offset", required = false, defaultValue = "0") Integer offset,
                         Model model) throws Exception {
        JSONObject result = new JSONObject();
        SpotifySeach spotifySeach = null;
        String pageName = "";
        // API 사용
        if(references.equals("artist")) {
            spotifySeach = new SpotifySeach(new ArtistSearch());
            pageName = "search/searchArtist";
        }
        else if(references.equals("track")) {
            spotifySeach = new SpotifySeach(new TrackSearch());
            pageName = "search/searchTrack";
        }
        else if(references.equals("album")) {
            spotifySeach = new SpotifySeach(new AlbumSearch());
            pageName = "search/searchAlbum";
        }
        else {
            throw new Exception("references not suitable");
        }

        result = spotifySeach.getSearchResult(searchText, limit, offset);
        model.addAttribute("searchResult", result);
        return pageName; //"searchAlbum";
    }
}
