package cn.edu.scau.blog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/16
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("blogComment")
public class BlogComment {
    private Integer id;
    private String nickname;
    private String commentText;
    private Date commentTime = new Date();
    private Integer blogTextId;
}
