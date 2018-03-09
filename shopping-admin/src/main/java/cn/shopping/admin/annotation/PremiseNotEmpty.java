package cn.shopping.admin.annotation;

import cn.shopping.admin.vo.OperateType;

import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * 有前提条件的非空注解
 * @Description:
 * @Author:libin
 * @Date: Created in 9:31 2018/1/10
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface PremiseNotEmpty{
    String message() default "not allowed empty";
    OperateType Premise() default OperateType.NO;
}
