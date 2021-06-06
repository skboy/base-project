package io.github.talelin.latticy.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 描述:
 *
 * @author skboy
 * @version 1.0
 * @date 2021/4/16 3:10 下午
 */
@Getter
@AllArgsConstructor
public enum DemoTypeEnum {
    /**
     * demo
     */
    DEMO("demo"),
    ;
    private final String value;

    public static DemoTypeEnum getEnum(String value) {
        return Arrays.stream(DemoTypeEnum.values())
                .filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(() -> new RuntimeException("11"));
    }
}
