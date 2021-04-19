package cn.edu.scau.blog.mapper;

import cn.edu.scau.blog.beans.Blog;
import cn.edu.scau.blog.beans.BlogComment;
import cn.edu.scau.blog.beans.BlogCounter;
import cn.edu.scau.blog.beans.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BlogMapper {
    BlogCounter getBlogCounterByAccount(String account);

    List<Blog> getAllBlogs();

    List<BlogType> getBlogTypes();

    Blog getBlogById(Integer id);

    List<BlogComment> getBlogCommentsById(Integer id);

    void incrBlogFieldById(@Param("id") Integer id,
                           @Param("field") String field);

    void decrBlogFieldById(@Param("id") Integer id,
                           @Param("field") String field);

    List<Blog> getBlogsByBlogType(Integer id);

    int insertBlog(Blog blog);

    int insertBlogComment(BlogComment blogComment);
}
