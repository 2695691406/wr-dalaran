package com.qisimanxiang.workreport.dalaran.protocol.http;

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
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // http 编解码
        pipeline.addLast(new HttpServerCodec());
        // http 消息聚合器  1024*1024为接收的最大contentlength
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024));
        // 请求处理器
        pipeline.addLast(new HttpRequestHandler());

    }
}
