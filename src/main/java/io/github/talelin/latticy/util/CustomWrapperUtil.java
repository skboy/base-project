package io.github.talelin.latticy.util;//package io.github.talelin.latticy.util;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import io.github.talelin.latticy.annotation.WhereType;
//import io.github.talelin.latticy.common.enumeration.FormatterTypeEnum;
//import io.github.talelin.latticy.common.enumeration.WhereTypeEnum;
//import io.github.talelin.latticy.service.WhereFun;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.util.CollectionUtils;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 描述:  Wrapper组装工具
// *
// * @author skboy
// * @version 1.0
// * @date 2021/4/16 1:44 下午
// */
//public class CustomWrapperUtil {
//    private static Map<WhereTypeEnum, WhereFun> funMap;
//
//    static {
//        funMap = new HashMap<>(16);
//        funMap.put(WhereTypeEnum.EQ, ((wrapper, field, value) -> wrapper.eq(field, value)));
//        funMap.put(WhereTypeEnum.NE, ((wrapper, field, value) -> wrapper.ne(field, value)));
//        funMap.put(WhereTypeEnum.LE, ((wrapper, field, value) -> wrapper.le(field, value)));
//        funMap.put(WhereTypeEnum.LT, ((wrapper, field, value) -> wrapper.lt(field, value)));
//        funMap.put(WhereTypeEnum.GE, ((wrapper, field, value) -> wrapper.ge(field, value)));
//        funMap.put(WhereTypeEnum.GT, ((wrapper, field, value) -> wrapper.gt(field, value)));
//        funMap.put(WhereTypeEnum.LIKE, ((wrapper, field, value) -> wrapper.like(field, value.toString())));
//        funMap.put(WhereTypeEnum.IN, ((wrapper, field, value) -> {
//            if (value instanceof Collection) {
//                Collection<?> collection = (Collection<?>) value;
//                if (collection.size() != 0) {
//                    wrapper.in(field, collection);
//                } else if (value instanceof String) {
//                    List<String> list = Arrays.asList(((String) value).split(","));
//                    if (!CollectionUtils.isEmpty(list)) {
//                        wrapper.in(field, list);
//                    }
//                } else if (value instanceof Object[]) {
//                    Object[] arr = (Object[]) value;
//                    if (arr.length > 0) {
//                        wrapper.in(field, arr);
//                    }
//                } else {
//                    wrapper.in(field, value.toString());
//                }
//            }
//        }));
//    }
//
//    public static <T, K> void autoWrapper(T object, QueryWrapper<K> wrapper) throws IllegalAccessException {
//        execute(object, wrapper);
//    }
//
//    private static <T, K> void execute(T object, QueryWrapper<K> wrapper) throws IllegalAccessException {
//        List<Field> allField = getAllField(object);
//        for (Field field : allField) {
//            //设置可以获取私有权限
//            field.setAccessible(true);
//            //获取成员变量的值
//            Object val = field.get(field);
//
//            String colum = "";
//
//            if (val != null && !"".equals(val.toString())) {
//
//                WhereType whereType = field.getAnnotation(WhereType.class);
//
//                if (whereType != null) {
//                    if (StringUtils.isNoneBlank(whereType.field())) {
//                        colum = whereType.field();
//                    } else {
//                        colum = camelToUnderline(field.getName());
//                    }
//                }
//                if (whereType.formatterType() != FormatterTypeEnum.NONE) {
//                    switch (whereType.formatterType()) {
//                        case LOCAL_DATE:
//                            int dayPlus = whereType.dayPlus();
//                            val = localDateFormatter(val, whereType.formatter(), dayPlus);
//                            break;
//                        default:
//                            throw new RuntimeException("error");
//                    }
//                }
//                funMap.get(whereType.type()).fun(wrapper,colum,val);
//            }
//
//
//        }
//    }
//
//    private static LocalDate localDateFormatter(Object string, String formatter, int dayPlus) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
//        return LocalDate.parse(string.toString(), dateTimeFormatter).plusDays(dayPlus);
//    }
//
//    /**
//     * 获取该类所有成员 (包括父类)
//     *
//     * @param object
//     * @param <T>
//     * @return
//     */
//    private static <T> List<Field> getAllField(T object) {
//        Class<?> clazz = object.getClass();
//        List<Field> fieldList = new ArrayList<>();
//        while (clazz != null) {
//            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
//            clazz = clazz.getSuperclass();
//        }
//        return fieldList;
//    }
//
//    private static String camelToUnderline(String name) {
//        if (StringUtils.isBlank(name)) {
//            return "";
//        }
//        int length = name.length();
//        StringBuilder stringBuilder = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            char c = name.charAt(i);
//            if (Character.isUpperCase(c)) {
//                stringBuilder.append("_");
//                stringBuilder.append(Character.toLowerCase(c));
//            } else {
//                stringBuilder.append(c);
//            }
//        }
//        return stringBuilder.toString();
//    }
//}
