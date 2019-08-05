package com.qisimanxiang.workreport.dalaran.http.service;

import com.qisimanxiang.workreport.dalaran.http.dto.AuthorInfoDTO;
import com.qisimanxiang.workreport.dalaran.http.dto.VisitorDTO;
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
        return AuthorInfoDTO.newInstance(visitorDTO);
    }

//    /**
//     * 使用方式错误
//     * @param visitorDTO
//     * @param name
//     * @return
//     */
//    @HttpProtocol(path = "author/ask2")
//    public AuthorInfoDTO askAuthorInfo(VisitorDTO visitorDTO,String name) {
//        return AuthorInfoDTO.newInstance(visitorDTO);
//    }
}
