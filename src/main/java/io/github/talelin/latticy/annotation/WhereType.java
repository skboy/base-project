package io.github.talelin.latticy.annotation;

import io.github.talelin.latticy.common.enumeration.FormatterTypeEnum;
import io.github.talelin.latticy.common.enumeration.WhereTypeEnum;

/**
 * 描述:  mybatis-plus wrapper注解
 *
 * @author skboy
 * @version 1.0
 * @date 2021/4/16 1:45 下午
 */
public @interface WhereType {
    /**
     * 查询类型
     * @return
     */
    WhereTypeEnum type() default WhereTypeEnum.EQ;

    /**
     * 查询字段
     * @return
     */
    String field() default "";


    FormatterTypeEnum formatterType() default FormatterTypeEnum.NONE;

    String formatter() default "";

    int dayPlus() default 0;
}
