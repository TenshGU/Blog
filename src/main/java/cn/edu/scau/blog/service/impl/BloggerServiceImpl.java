package cn.edu.scau.blog.service.impl;

import cn.edu.scau.blog.beans.Blogger;
import cn.edu.scau.blog.mapper.BloggerMapper;
import cn.edu.scau.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger getBloggerByAccountAndPassword(String account, String password) {
        return bloggerMapper.getBloggerByAccountAndPassword(account,password);
    }
}
