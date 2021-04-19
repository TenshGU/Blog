package cn.edu.scau.blog.controller;

import cn.edu.scau.blog.beans.*;
import cn.edu.scau.blog.service.BlogService;
import cn.edu.scau.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/14
 */
@Controller
public class CommonController {
    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/index")
    public String toIndex(HttpServletRequest request, HttpSession session) {
        Blogger blogger = (Blogger) session.getAttribute("blogger");
        Object blogCounter1 = session.getAttribute("blogCounter");
        if (blogger != null && blogCounter1 == null) {
            BlogCounter blogCounter2 = blogService.getBlogCounterByAccount(blogger.getAccount());
            session.setAttribute("blogCounter",blogCounter2);
        }

        List<Blog> allBlogs = blogService.getAllBlogs();
        request.setAttribute("allBlogs", allBlogs);

        List<Blog> hotBlogs = blogService.getHotBlogs(allBlogs);
        session.setAttribute("hotBlogs",hotBlogs);

        Object blogTypes1 = session.getAttribute("blogType");
        if (blogTypes1 == null) {
            List<BlogType> blogTypes2 = blogService.getBlogTypes();
            session.setAttribute("blogType",blogTypes2);
        }
        return "index";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/comment")
    @ResponseBody
    public ResultJson comment(BlogComment blogComment) {
        ResultJson resultJson = blogService.insertBlogComment(blogComment);
        return resultJson;
    }

    @GetMapping("/toWrite")
    public String toWrite(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("blogger") != null) {
            return "write";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String login(String account, String password,
                        HttpServletRequest request,
                        HttpSession session) {
        Blogger blogger = bloggerService.getBloggerByAccountAndPassword(account,password);
        if (blogger == null) {
            request.setAttribute("loginFail","账号或密码错误");
            return "login";
        } else {
            //查询用户关联的博客信息
            session.setAttribute("blogger", blogger);
            return "redirect:/index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, HttpServletRequest request) {
        Blog blog = blogService.getBlogById(id);
        request.setAttribute("blog", blog);

        List<BlogComment> blogComments = blogService.getBlogCommentsById(id);
        request.setAttribute("blogComments",blogComments);

        blogService.incrBlogFieldById(id, "read_count");
        return "details";
    }

    @GetMapping("/updateBlogField/{id}/{field}/{function}")
    @ResponseBody
    public String updateBlogField(@PathVariable("id") Integer id,
                                @PathVariable("field") String field,
                                @PathVariable("function") Integer func) {
        if (func == 1) {
            blogService.incrBlogFieldById(id,field);
        } else if (func == 2){
            blogService.decrBlogFieldById(id,field);
        }
        return "ok";
    }

    @GetMapping("/type/{id}")
    public String type(@PathVariable("id") Integer id, HttpServletRequest request) {
        if (id == 0) {
            return "forward:/index";
        }
        List<Blog> blogsByBlogType = blogService.getBlogsByBlogType(id);
        request.setAttribute("allBlogs",blogsByBlogType);
        return "index";
    }

    @PostMapping("/submitBlog")
    public String submitBlog(HttpServletRequest request, Blog blog) {
        if (!StringUtils.hasText(blog.getTitle())) {
            request.setAttribute("submitFail","标题不能为空");
            request.setAttribute("failBlog",blog);
            return "write";
        }
        if (blog.getContext().length() == 0) {
            request.setAttribute("submitFail","博文内容不能为空");
            request.setAttribute("failBlog",blog);
            return "write";
        }
        blogService.insertBlog(blog);
        return "redirect:/index";
    }
}