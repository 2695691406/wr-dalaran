package com.qisimanxiang.workreport.dalaran.agent.running;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author wangmeng
 * @date 2019-08-19
 */
public class RunningClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.startsWith("com/qisimanxiang/workreport/dalaran/server/http/service")) {
            System.out.println("Transformer ： " + className + " working ");
            try {
                final ClassPool classPool = ClassPool.getDefault();
                final CtClass clazz =
                        classPool.get("com.qisimanxiang.workreport.dalaran.server.http.service.AuthorServiceImpl");



                for (final CtMethod method : clazz.getMethods()) {
                    if (method.getMethodInfo().getCodeAttribute() == null) {
                        continue;
                    }

                    System.out.println(method.getName() + "改变");
                    method.insertAfter("System.out.println(\"agent running 拦截 ：\"+\"" + method.getName() + "\");");
                }

                byte[] byteCode = clazz.toBytecode();
                clazz.detach();

                return byteCode;
            } catch (final NotFoundException | CannotCompileException | IOException ex) {
                ex.printStackTrace();
            }
        }
        return new byte[0];
    }
}
