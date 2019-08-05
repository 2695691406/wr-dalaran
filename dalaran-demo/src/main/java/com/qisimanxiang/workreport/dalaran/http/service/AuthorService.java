package com.qisimanxiang.workreport.dalaran.http.service;

import com.qisimanxiang.workreport.dalaran.http.dto.AuthorInfoDTO;
import com.qisimanxiang.workreport.dalaran.http.dto.VisitorDTO;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
interface AuthorService {
    AuthorInfoDTO askAuthorInfo(VisitorDTO customerDTO);
}
