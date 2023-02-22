package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionNameEnum;

public interface ConditionService {
    void seedConditions();

    Condition findByConditionNameEnum(ConditionNameEnum conditionNameEnum);
}
