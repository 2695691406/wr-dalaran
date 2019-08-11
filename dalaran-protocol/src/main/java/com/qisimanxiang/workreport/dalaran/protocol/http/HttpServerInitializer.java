package com.qisimanxiang.workreport.dalaran.protocol.http;

import com.qisimanxiang.workreport.dalaran.protocol.log.DalaranLogger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * channel初始化
 *
 * @author wangmeng
 * @date 2019-08-04
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private DalaranLogger logger;
    private int maxContentLength;
    private int defaultContentLength = 512 * 1024;

    public HttpServerInitializer(DalaranLogger logger, int maxContentLength) {
        this.logger = logger;
        this.maxContentLength = maxContentLength < defaultContentLength ? defaultContentLength : maxContentLength;
    }

    // http 编解码
    // http 消息聚合器  1024*1024为接收的最大contentlength
    // http 请求处理器
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(
                "httpAggregator",
                new HttpObjectAggregator(maxContentLength));
        pipeline.addLast(new HttpRequestHandler(logger));

    }
}
