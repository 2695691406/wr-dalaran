package com.qisimanxiang.workreport.dalaran.server.http.service;

import com.qisimanxiang.workreport.dalaran.server.http.dto.AuthorInfoDTO;
import com.qisimanxiang.workreport.dalaran.server.http.dto.VisitorDTO;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.DalaranProvider;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.HttpProtocol;
import org.springframework.stereotype.Service;

/**
 * 例子
 * @author wangmeng
 * @date 2019-08-04
 */
@Service
@DalaranProvider
public class AuthorServiceImpl implements AuthorService {

    /**
     * 正常使用方式
     * @param visitorDTO
     * @return
     */
    @HttpProtocol(path = "author/ask")
    @Override
    public AuthorInfoDTO askAuthorInfo(VisitorDTO visitorDTO) {
        //TODO 此处可用Dubbo、HSF等方式调用内部RPC服务。
        return AuthorInfoDTO.newInstance(visitorDTO);
    }

}
