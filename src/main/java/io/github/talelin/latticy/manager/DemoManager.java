package io.github.talelin.latticy.manager;

import io.github.talelin.latticy.common.enumeration.DemoTypeEnum;
import io.github.talelin.latticy.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author skboy
 * @version 1.0
 * @date 2021/4/16 3:09 下午
 */
@Slf4j
@Service
public class DemoManager implements BeanPostProcessor {
    private static final Map<DemoTypeEnum, DemoService> serviceMap = new HashMap<>(DemoTypeEnum.values().length);

    private DemoService getService(String type) {
        return serviceMap.get(DemoTypeEnum.getEnum(type));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof DemoService)) {
            return bean;
        }
        DemoService demo = (DemoService) bean;
        DemoTypeEnum type = demo.getType();

        if (serviceMap.containsKey(type)) {
            throw new IllegalStateException("重复注册");
        }

        log.info("Load DemoService {} for video type {}", demo.getClass(),
                type.getValue());
        serviceMap.put(type, demo);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

}

