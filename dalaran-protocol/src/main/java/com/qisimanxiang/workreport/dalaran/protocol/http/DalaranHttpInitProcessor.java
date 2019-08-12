package com.qisimanxiang.workreport.dalaran.protocol.http;

import com.qisimanxiang.workreport.dalaran.protocol.common.MethodInfo;
import com.qisimanxiang.workreport.dalaran.protocol.config.DalaranConfig;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.DalaranProvider;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.HttpProtocol;
import com.qisimanxiang.workreport.dalaran.protocol.http.exception.MethodParameterIllegalException;
import com.qisimanxiang.workreport.dalaran.protocol.http.exception.PathAlreadyExistException;
import com.qisimanxiang.workreport.dalaran.protocol.http.mapping.HttpPathMapping;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
@ConditionalOnBean(DalaranConfig.class)
@Component
@Slf4j
public class DalaranHttpInitProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        DalaranProvider annotation = beanClass.getAnnotation(DalaranProvider.class);
        if (annotation == null) {
            //无注解放行
            return bean;
        }
        //有注解执行协议注册
        Method[] methods = beanClass.getMethods();
        for (Method m : methods) {
            HttpProtocol protocol = m.getAnnotation(HttpProtocol.class);
            if (protocol == null) {
                //无注解继续循环
                continue;
            }
            //检查path是否存在
            String pathInMethod = protocol.path();
            boolean pathIsOk = PathUtil.checkPath(pathInMethod);
            if (!pathIsOk) {
                log.error("dalaran path {} is illegal! please check class {}", pathInMethod, beanClass.getName());
                throw new PathAlreadyExistException("dalaran path " + pathInMethod + " is  illegal!");
            }

            //检查方法是否为单参或无参
            Parameter[] parameters = m.getParameters();
            if (parameters != null && parameters.length > 1) {
                log.error("dalaran method  {} parameters number is illegal! please check class {}", m.getName(), beanClass.getName());
                throw new MethodParameterIllegalException("dalaran method " + m.getName() + " parameters number is  illegal!");
            }


            String path = PathUtil.pathOptimizat(pathInMethod);

            boolean contains = HttpPathMapping.mapping.containsKey(path);
            if (contains) {
                log.error("dalaran path {} already exist! please check class {}", path, beanClass.getName());
                throw new PathAlreadyExistException("dalaran path " + path + " already exist!");
            }
            //注册到path mapping中
            HttpPathMapping.mapping.put(path, new MethodInfo(m, bean));
            log.info("dalaran path {} init success!", path);
        }
        return bean;

    }

}
