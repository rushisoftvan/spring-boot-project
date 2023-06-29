package com.productmanagmentsystem.valiadtion;

import com.productmanagmentsystem.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
public  class StatusValidater implements ConstraintValidator<ValidateStatus,StatusEnum> {

    public boolean isValid(StatusEnum  status, ConstraintValidatorContext constraintValidatorContext) {
        List<String> statusType = asList(StatusEnum.ACTIVE.name(),StatusEnum.IN_ACTIVE.name());
        return statusType.contains(status.name());
    }
}
