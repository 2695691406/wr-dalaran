package com.qisimanxiang.workreport.dalaran.agent.test;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * 测试运行中探针
 * @author wangmeng
 * @date 2019-08-19
 */
public class T {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        String jvmPid = "9047";
        String agentFile = "/Users/wangmeng/work-report/dalaran/dalaran-agent/target/dalaran-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
        VirtualMachine jvm = VirtualMachine.attach(jvmPid);
        jvm.loadAgent(agentFile);
        jvm.detach();
    }
}
