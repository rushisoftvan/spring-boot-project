package com.productmanagmentsystem.valiadtion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD,ElementType.PARAMETER}) //where you want to implement this annotation
@Retention(RetentionPolicy.RUNTIME) //when you want to implement this annotation
@Constraint(validatedBy = StatusValidater.class)
public @interface ValidateStatus {

    public String message() default "status should be ACTIVE OR INACTIVE";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
