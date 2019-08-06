package com.qisimanxiang.workreport.dalaran.server.http.service;

import com.qisimanxiang.workreport.dalaran.server.http.dto.AuthorInfoDTO;
import com.qisimanxiang.workreport.dalaran.server.http.dto.VisitorDTO;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
interface AuthorService {
    AuthorInfoDTO askAuthorInfo(VisitorDTO customerDTO);
}
