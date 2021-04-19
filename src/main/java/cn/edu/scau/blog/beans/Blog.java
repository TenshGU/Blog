package cn.edu.scau.blog.beans;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("blog")
public class Blog {
    private Integer id;
    private String title;
    private String brief;
    private String context;
    private Date createTime = new Date();
    private Date lastMod = new Date();
    private Integer readCount = 1;
    private Integer likeCount = 0;
    private BlogType blogType;
    private Blogger blogger;
    private List<BlogComment> blogComments;
}
