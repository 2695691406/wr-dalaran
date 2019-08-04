package com.qisimanxiang.workreport.dalaran.protocol.http.init;

import com.qisimanxiang.workreport.dalaran.protocol.http.HttpRequestHandler;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.DalaranProvider;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.HttpProtocol;
import com.qisimanxiang.workreport.dalaran.protocol.http.exception.PathAlreadyExistException;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
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
            String methodPath = protocol.path();
            boolean pathIsOk = PathUtil.checkPath(methodPath);
            if (!pathIsOk) {
                log.error("dalaran path {} is illegal! check class {}", methodPath, beanClass.getName());
                throw new PathAlreadyExistException("dalaran path " + methodPath + " is  illegal!");
            }

            String path = PathUtil.pathOptimizat(methodPath);

            boolean contains = HttpRequestHandler.pathMapping.containsKey(path);
            if (contains) {
                log.error("dalaran path {} already exist! check class {}", path, beanClass.getName());
                throw new PathAlreadyExistException("dalaran path " + path + " already exist!");
            }
            //注册到path mapping中
            HttpRequestHandler.pathMapping.put(path, new HttpRequestHandler.MethodInfo(m, bean));
            log.info("dalaran path {} init success!", path);
        }
        return bean;

    }

}
