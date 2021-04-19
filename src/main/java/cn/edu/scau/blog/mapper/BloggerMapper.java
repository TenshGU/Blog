package cn.edu.scau.blog.mapper;

import cn.edu.scau.blog.beans.Blogger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BloggerMapper {
    Blogger getBloggerByAccountAndPassword(String account, String password);
}
