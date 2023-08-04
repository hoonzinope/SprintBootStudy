package com.example.musicDashBoardDemo.controller;

import com.example.musicDashBoardDemo.api.SpotifySeach;
import com.example.musicDashBoardDemo.api.detailType.AlbumDetail;
import com.example.musicDashBoardDemo.api.detailType.ArtistDetail;
import com.example.musicDashBoardDemo.api.detailType.TrackDetail;
import com.example.musicDashBoardDemo.entity.Result;
import com.example.musicDashBoardDemo.service.ResultService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/detail")
    public String getDetail(@RequestParam(name="targetName") String targetName,
                            @RequestParam(name="targetId") String targetId,
                            @RequestParam(name="targetType") String type){

        // result exists?
        Long resultByID = resultService.getResultByID(targetId);
        if(resultByID == null || resultByID == -1) {
            resultByID = resultService.saveResult(targetName, targetId, type);
        }

        return "redirect:post?postSeq="+resultByID+"&type="+type;
    }


    @GetMapping("/post")
    public String getPost(@RequestParam(name="postSeq", required = true) Long postSeq,
                            Model model) {
        Result result = resultService.getResult(postSeq);
        String result_id = result.getResultId();
        String type = result.getType();
        SpotifySeach spotifySeach = null;
        if(type.equals("album")) spotifySeach = new SpotifySeach(new AlbumDetail());
        else if(type.equals("track")) spotifySeach = new SpotifySeach(new TrackDetail());
        else if(type.equals("artist")) spotifySeach = new SpotifySeach(new ArtistDetail());

        JSONObject resultInfo = spotifySeach.getDetailResult(result_id, type);
        model.addAttribute("resultInfo", resultInfo);

        return "details/post";
    }
}
