package com.weekend.cafe.Service;

import com.weekend.cafe.domain.Grade;
import com.weekend.cafe.repository.GradeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    public JSONArray getAllGrade() {
        List<Grade> gradeList = gradeRepository.findAll();
        JSONArray result = new JSONArray();
        gradeList.forEach(grade -> {
            JSONObject gradeInfo = new JSONObject();
            gradeInfo.put("name", grade.getName());
            gradeInfo.put("count", grade.getNeedsCount());
            result.put(gradeInfo);
        });
        return result;
    }

}
