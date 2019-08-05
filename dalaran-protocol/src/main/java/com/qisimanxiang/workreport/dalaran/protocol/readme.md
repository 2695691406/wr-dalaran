## 协议

本package要做的事情如下：
- 提供一个将接口转为http接口的实现并提供便捷使用的注解（netty）
- 提供一个将接口转为dubbo接口的实现并提供便捷使用的注解（dubbo）
- 统一的配置管理（config）

#### 启动

启动有两种方式
1. 借助 Spring boot web starter，如此需要使用两个端口，同时会出现mvc和自实现netty两套http接口机制。
2. 借助 Spring boot starter，netty 自身作为主线程处理服务。

以上两种方式有各有优略。
你可能会问直接用mvc将dubbo转http行不行？当然可以，但是意味着分层结构被打破，终归是个取舍问题。

dubbo的封装，此处由于时间问题就不coding了，有兴趣可以自己实现一下。