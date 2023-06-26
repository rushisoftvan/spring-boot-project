package com.productmanagmentsystem.valiadtion;

import com.productmanagmentsystem.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import  java.util.List;

import static java.util.Arrays.*;

@Slf4j
public  class StatusValidater implements ConstraintValidator<ValidateStatus,StatusEnum> {

    public boolean isValid(StatusEnum  status, ConstraintValidatorContext constraintValidatorContext) {
        List<StatusEnum> statusType = asList(StatusEnum.ACTIVE,StatusEnum.IN_ACTIVE);
        return statusType.contains(status);
    }
}
