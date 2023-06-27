package com.productmanagmentsystem.valiadtion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER}) //where you want to implement this annotation
@Retention(RetentionPolicy.RUNTIME) //when you want to implement this annotation
@Documented
@Constraint(validatedBy = StatusValidater.class)
public @interface ValidateStatus {

    public String message() default "Status should be ACTIVE OR INACTIVE";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
