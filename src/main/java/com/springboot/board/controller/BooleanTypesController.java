package com.springboot.board.controller;

import com.springboot.board.dto.request.BooleanTypesDto;
import com.springboot.board.repository.BooleanTypesQueryDslRepository;
import com.springboot.board.service.BooleanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BooleanTypesController {

    private final BooleanTypeService booleanTypeService;

    @PostMapping("/query-dsl/boolean-types")
    public Long postBooleanTypes(@RequestBody BooleanTypesDto booleanTypesDto) {
        return booleanTypeService.update(booleanTypesDto);
    }

    @PostMapping("/boolean-types")
    public Long postBooleanTypesWithOutQueryDsl(@RequestBody BooleanTypesDto booleanTypesDto) {
        return booleanTypeService.updateWithOutQueryDsl(booleanTypesDto);
    }

}
