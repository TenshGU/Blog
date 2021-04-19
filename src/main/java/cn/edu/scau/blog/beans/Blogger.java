package cn.edu.scau.blog.beans;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/14
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Alias("blogger")
public class Blogger {
    private String account;
    private String password;
    private String nickname;
    private String image;
    private String brief;
    private Date createTime;
}
