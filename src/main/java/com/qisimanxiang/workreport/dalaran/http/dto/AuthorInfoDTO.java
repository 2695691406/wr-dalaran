package com.qisimanxiang.workreport.dalaran.http.dto;

//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
@Data
@ToString(callSuper = true)
public class AuthorInfoDTO implements Serializable {
    private static final long serialVersionUID = -2399607174430508741L;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 访问时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date visitDate;
    /**
     * 访问者
     */
    private VisitorDTO visitor;

    private AuthorInfoDTO() {
    }

    private AuthorInfoDTO(String name, String birthday, String desc, VisitorDTO visitor) {
        this.name = name;
        this.birthday = birthday;
        this.desc = desc;
        this.visitor = visitor;
        this.visitDate = new Date();
    }

    public static AuthorInfoDTO newInstance(VisitorDTO visitor) {
        return new AuthorInfoDTO("王猛", "1992/10/11", "挺安静的一个人？", visitor);
    }
}
