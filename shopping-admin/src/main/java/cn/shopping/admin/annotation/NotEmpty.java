package cn.shopping.admin.annotation;

import javax.validation.*;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * 非空注解
 * @Description:
 * @Author:libin
 * @Date: Created in 16:12 2018/1/2
 */
@Documented
@Constraint(validatedBy = {NotEmpty.NotEmptyValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NotEmpty {
    String message() default "not allow empty!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotEmptyValidator implements ConstraintValidator<NotEmpty,Object>{

        @Override
        public void initialize(NotEmpty constraintAnnotation) {

        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if(value instanceof String){
                return null != value && ((String) value).replaceAll("\\s*","").length()>0;
            }else if(value instanceof Collection){
                ((Collection) value).removeAll(Collections.singleton(null));//去除所有的null
                return null != value && ((Collection) value).size()>0;
            }else if(value instanceof Object[]){
                value = Arrays.stream((Object[]) value).filter(item ->item !=null && !"".equals(item)).toArray();//去除数组中的null
                return null != value && ((Object[]) value).length>0;
            }else if(value instanceof Map){
                return null!= value && ((Map) value).size()>0;
            }else {
                return null != value;
            }
        }
    }
}
