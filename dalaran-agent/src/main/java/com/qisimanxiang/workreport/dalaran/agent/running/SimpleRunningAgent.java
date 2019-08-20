package com.qisimanxiang.workreport.dalaran.agent.running;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author wangmeng
 * @date 2019-08-19
 */
public class SimpleRunningAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("[Agent] In agentmain method");
        String className = "com.qisimanxiang.workreport.dalaran.server.http.service.AuthorServiceImpl";
        Class<?> aClass = Class.forName(className);
        inst.addTransformer(new RunningClassFileTransformer(),true);
        inst.retransformClasses(aClass);
    }
}
