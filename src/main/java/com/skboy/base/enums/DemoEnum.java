package com.skboy.base.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述
 *
 * @author skboy
 * @date 2/6/2021下午8:04
 */
@Getter
@AllArgsConstructor
public enum DemoEnum implements IEnum<Integer> {
    LOW(1,"低风险"),
    MID(2,"中风险"),
    HEIGHT(3,"高风险"),
    ;
    private Integer value;
    @JsonValue
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
