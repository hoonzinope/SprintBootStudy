package com.example.musicDashBoardDemo.service;

import com.example.musicDashBoardDemo.entity.Result;
import com.example.musicDashBoardDemo.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result getResult(Long seq) {
        return resultRepository.findById(seq).orElse(null);
    }

    public Long getResultByID(String result_id) {
        Optional<Result> optionalResult = resultRepository.findByResultId(result_id);
        return optionalResult.map(Result::getSeq).orElse(null);
    }

    @Transactional
    public Long saveResult(String name, String resultId, String type) {
        Result result = Result.builder()
                .name(name)
                .resultId(resultId)
                .type(type)
                .build();
        return resultRepository.save(result).getSeq();
    }
}
