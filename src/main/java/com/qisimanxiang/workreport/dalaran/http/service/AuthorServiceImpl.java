package com.qisimanxiang.workreport.dalaran.http.service;

import com.qisimanxiang.workreport.dalaran.http.dto.AuthorInfoDTO;
import com.qisimanxiang.workreport.dalaran.http.dto.VisitorDTO;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.DalaranProvider;
import com.qisimanxiang.workreport.dalaran.protocol.http.annotation.HttpProtocol;
import org.springframework.stereotype.Service;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
@Service
@DalaranProvider
public class AuthorServiceImpl implements AuthorService {


    @HttpProtocol(path = "author/ask")
    @Override
    public AuthorInfoDTO askAuthorInfo(VisitorDTO visitorDTO) {
        return AuthorInfoDTO.newInstance(visitorDTO);
    }
}
