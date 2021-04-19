package cn.edu.scau.blog.service;

import cn.edu.scau.blog.beans.Blogger;

public interface BloggerService {
    Blogger getBloggerByAccountAndPassword(String account, String password);
}
