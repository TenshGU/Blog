package cn.edu.scau.blog.service.impl;

import cn.edu.scau.blog.beans.*;
import cn.edu.scau.blog.mapper.BlogMapper;
import cn.edu.scau.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public BlogCounter getBlogCounterByAccount(String account) {
        return blogMapper.getBlogCounterByAccount(account);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

    @Override
    public List<BlogType> getBlogTypes() {
        return blogMapper.getBlogTypes();
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public void incrBlogFieldById(Integer id, String field) {
        blogMapper.incrBlogFieldById(id,field);
    }

    @Override
    public void decrBlogFieldById(Integer id, String field) {
        blogMapper.decrBlogFieldById(id,field);
    }

    @Override
    public List<BlogComment> getBlogCommentsById(Integer id) {
        return blogMapper.getBlogCommentsById(id);
    }

    @Override
    public List<Blog> getBlogsByBlogType(Integer id) {
        return blogMapper.getBlogsByBlogType(id);
    }

    @Override
    public int insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    @Override
    public ResultJson insertBlogComment(BlogComment blogComment) {
        if (!StringUtils.hasText(blogComment.getNickname())) {
            return ResultJson.fail().addInfo("failMsg","用户名不能为空");
        }
        if (blogComment.getCommentText().length() > 100) {
            return ResultJson.fail().addInfo("failMsg","评论字数不能超过100字");
        }
        blogMapper.insertBlogComment(blogComment);
        return ResultJson.success();
    }

    @Override
    public List<Blog> getHotBlogs(List<Blog> blogList) {
        Stream<Blog> stream = blogList.stream();
        List<Blog> hotBlogs = stream.sorted((o1, o2) -> {
            double hot1 = o1.getReadCount() * 0.2
                    + o1.getBlogComments().size() * 0.4
                    + o1.getLikeCount() * 0.4;
            double hot2 = o2.getReadCount() * 0.2
                    + o2.getBlogComments().size() * 0.4
                    + o2.getLikeCount() * 0.4;
            if (hot1 >= hot2) {
                return -1;
            } else {
                return 1;
            }
        }).limit(5).collect(Collectors.toList());
        return hotBlogs;
    }
}
