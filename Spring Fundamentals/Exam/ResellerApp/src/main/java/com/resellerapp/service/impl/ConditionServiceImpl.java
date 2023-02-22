package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.ConditionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void seedConditions() {
        if (conditionRepository.count() != 0) {
            return;
        }

        Arrays.stream(ConditionNameEnum.values())
                .forEach(conditionNameEnum -> {
                    Condition condition = new Condition();
                    condition.setConditionName(conditionNameEnum);
                    switch (conditionNameEnum) {
                        case EXCELLENT:
                            condition.setDescription("In perfect condition");
                            break;
                        case GOOD:
                            condition.setDescription("Some signs of wear and tear or minor defects");
                            break;
                        case ACCEPTABLE:
                            condition.setDescription("The item is fairly worn but continues to function properly");
                            break;
                    }

                    conditionRepository.save(condition);
                });
    }

    @Override
    public Condition findByConditionNameEnum(ConditionNameEnum conditionNameEnum) {
        return conditionRepository.findByConditionName(conditionNameEnum).orElse(null);
    }
}
