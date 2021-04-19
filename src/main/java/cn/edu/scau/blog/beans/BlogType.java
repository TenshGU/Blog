package cn.edu.scau.blog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("blogType")
public class BlogType {
    private Integer id;
    private String typeName;
}
