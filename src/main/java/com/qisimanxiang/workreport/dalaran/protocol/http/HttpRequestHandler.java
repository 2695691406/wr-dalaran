package com.qisimanxiang.workreport.dalaran.protocol.http;

import com.qisimanxiang.workreport.dalaran.protocol.http.exception.PathNotExistException;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.JsonMapper;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.PathUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaderUtil.is100ContinueExpected;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public static ConcurrentHashMap<String, MethodInfo> pathMapping = new ConcurrentHashMap<>();

    /**
     * 方法信息类
     */
    public static class MethodInfo {
        private Method method;
        private Object bean;

        public MethodInfo(Method method, Object bean) {
            this.method = method;
            this.bean = bean;
        }

        public Method getMethod() {
            return method;
        }

        public Object getBean() {
            return bean;
        }
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //100 Continue
        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }
        // 获取请求的uri
        String uri = PathUtil.pathOptimizat(req.uri());
        String body = getBody(req);
        log.info("request uri:{} body: {}", uri, body);
        //响应处理

        //只支持单参或无参
        Object resp;
        HttpResponseStatus responseStatus;
        try {
            resp = invoke(uri, body);
            responseStatus = HttpResponseStatus.OK;
        } catch (PathNotExistException e) {
            resp = new ErrorResponse("not found");
            responseStatus = HttpResponseStatus.NOT_FOUND;
        }

        //响应输出
        String respBody = resp == null ? "" : JsonMapper.NON_DEFAULT.toString(resp);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                responseStatus,
                Unpooled.copiedBuffer(respBody, CharsetUtil.UTF_8)
        );
        // 头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        //回写
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 调用对应的方法
     *
     * @param uri  path
     * @param body 参数体
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Object invoke(String uri, String body) throws IllegalAccessException, InvocationTargetException, PathNotExistException {
        MethodInfo methodInfo = pathMapping.get(uri);
        if (methodInfo == null) {
            throw new PathNotExistException();
        }

        Method method = methodInfo.getMethod();
        Parameter[] parameters = method.getParameters();
        if (parameters != null && parameters.length == 1) {
            Object parameter = JsonMapper.NON_DEFAULT.prase(body, parameters[0].getType());
            return methodInfo.getMethod().invoke(methodInfo.bean, parameter);
        }
        return methodInfo.getMethod().invoke(methodInfo.bean);
    }

    /**
     * 获取参数体
     * <p>
     * TODO 优化此处
     *
     * @param httpRequest 请求
     * @return
     */
    private String getBody(FullHttpRequest httpRequest) {
        ByteBuf content = httpRequest.content();
        byte[] bytes = new byte[content.capacity()];
        content.readBytes(bytes);
        return new String(bytes);
    }

    @Data
    private static class ErrorResponse implements Serializable {
        private static final long serialVersionUID = -4807078450773275945L;
        private String error;

        private ErrorResponse(String error) {
            this.error = error;
        }
    }
}
