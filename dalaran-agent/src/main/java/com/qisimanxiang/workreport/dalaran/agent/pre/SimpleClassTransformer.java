package com.qisimanxiang.workreport.dalaran.agent.pre;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class SimpleClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            final ClassLoader loader,
            final String className,
            final Class<?> classBeingRedefined,
            final ProtectionDomain protectionDomain,
            final byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.startsWith("com/qisimanxiang/workreport/dalaran/server/http/service")) {
            try {
                final ClassPool classPool = ClassPool.getDefault();
                final CtClass clazz =
                        classPool.get("com.qisimanxiang.workreport.dalaran.server.http.service.AuthorServiceImpl");

                for (final CtConstructor constructor : clazz.getConstructors()) {
                    constructor.insertAfter("System.out.println(\"agent 拦截 ：\"+this.getClass().getName());");
                }

                byte[] byteCode = clazz.toBytecode();
                clazz.detach();

                return byteCode;
            } catch (final NotFoundException | CannotCompileException | IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
}