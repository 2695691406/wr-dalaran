package com.qisimanxiang.workreport.dalaran.protocol.http;

import com.qisimanxiang.workreport.dalaran.protocol.common.MethodInfo;
import com.qisimanxiang.workreport.dalaran.protocol.http.exception.PathNotExistException;
import com.qisimanxiang.workreport.dalaran.protocol.http.mapping.HttpPathMapping;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.JsonMapper;
import com.qisimanxiang.workreport.dalaran.protocol.http.util.PathUtil;
import com.qisimanxiang.workreport.dalaran.protocol.log.DalaranLogger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static io.netty.handler.codec.http.HttpHeaderUtil.is100ContinueExpected;

/**
 * HTTP 请求处理
 *
 * @author wangmeng
 * @date 2019-08-04
 */

@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    private static final int SINGLE_PARAMETER_LENGTH = 1;
    private static final int FIRST_PARAMETER_INDEX = 0;
    private DalaranLogger dalaranLogger;

    public HttpRequestHandler(DalaranLogger dalaranLogger) {
        super();
        this.dalaranLogger = dalaranLogger;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //100 Continue
        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
        }
        // 获取请求的uri
        String uri = PathUtil.pathOptimizat(req.uri());
        String content = getRequestContent(req);

        if (log.isInfoEnabled()) {
            log.info("request uri:{} content: {}", uri, content);
        }

        //响应处理&只支持单参或无参
        Object responseObject;
        HttpResponseStatus responseStatus;
        try {
            responseObject = invoke(uri, content);
            responseStatus = HttpResponseStatus.OK;
        } catch (PathNotExistException e) {
            responseObject = new ErrorResponse("not found");
            responseStatus = HttpResponseStatus.NOT_FOUND;
        }

        //生成响应&设置头信息&输出
        String respBody = responseObject == null ? "" : JsonMapper.NON_DEFAULT.toJson(responseObject);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                responseStatus,
                Unpooled.copiedBuffer(respBody, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, APPLICATION_JSON);
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
        MethodInfo methodInfo = HttpPathMapping.mapping.get(uri);
        //路径不存在要反回404
        if (methodInfo == null) {
            throw new PathNotExistException();
        }
        //通过反射进行方法调用
        Method method = methodInfo.getMethod();
        Parameter[] parameters = method.getParameters();
        //单参
        if (parameters != null && parameters.length == SINGLE_PARAMETER_LENGTH) {
            Object parameter = JsonMapper.NON_DEFAULT.prase(body, parameters[FIRST_PARAMETER_INDEX].getType());
            return methodInfo.getMethod().invoke(methodInfo.getBean(), parameter);
        }
        //无参
        return methodInfo.getMethod().invoke(methodInfo.getBean());
    }

    /**
     * 获取请求参数内容
     * TODO 考虑优化
     *
     * @param httpRequest 请求
     * @return
     */
    private String getRequestContent(FullHttpRequest httpRequest) {
        ByteBuf buf = httpRequest.content();
        byte[] bytes = new byte[buf.capacity()];
        buf.readBytes(bytes);
        return new String(bytes);
    }

    /**
     * 错误响应类
     */
    @Data
    @AllArgsConstructor
    private static class ErrorResponse implements Serializable {
        private static final long serialVersionUID = -4807078450773275945L;
        private String error;
    }

}
