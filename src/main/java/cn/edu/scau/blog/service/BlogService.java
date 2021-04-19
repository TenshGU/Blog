package cn.edu.scau.blog.service;

import cn.edu.scau.blog.beans.*;

import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
public interface BlogService {
    BlogCounter getBlogCounterByAccount(String account);

    List<Blog> getAllBlogs();

    List<BlogType> getBlogTypes();

    Blog getBlogById(Integer id);

    List<BlogComment> getBlogCommentsById(Integer id);

    void incrBlogFieldById(Integer id, String field);

    void decrBlogFieldById(Integer id, String field);

    List<Blog> getBlogsByBlogType(Integer id);

    int insertBlog(Blog blog);

    ResultJson insertBlogComment(BlogComment blogComment);

    List<Blog> getHotBlogs(List<Blog> blogList);
}
