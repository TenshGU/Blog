package cn.edu.scau.blog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Alias("blogCounter")
public class BlogCounter {
    private Integer blogPieces;
    private Integer readTimes;
    private Integer likeTimes;
    private Integer commentTimes;
}
