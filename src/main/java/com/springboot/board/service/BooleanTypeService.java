package com.springboot.board.service;

import com.springboot.board.dto.request.BooleanTypesDto;
import com.springboot.board.entity.BooleanTypes;
import com.springboot.board.repository.BooleanTypesRepository;
import com.springboot.board.repository.BooleanTypesQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BooleanTypeService {

    private final BooleanTypesQueryDslRepository booleanTypesQueryDslRepository;
    private final BooleanTypesRepository booleanTypesRepository;

    public Long update(BooleanTypesDto booleanTypesDto) {
        return booleanTypesQueryDslRepository.update(booleanTypesDto);
    }

    public Long updateWithOutQueryDsl(BooleanTypesDto booleanTypesDto) {
        BooleanTypes booleanTypes = BooleanTypes.builder()
                .no(1L)
                .inputBool(booleanTypesDto.isVarBool())
                .inputEnum(booleanTypesDto.getVarEnum())
                .inputChar(booleanTypesDto.isVarChar())
                .build();

        return booleanTypesRepository.save(booleanTypes).getNo();
    }

}
