SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS blogger;
DROP TABLE IF EXISTS blog_type;
DROP TABLE IF EXISTS blog_text;
DROP TABLE IF EXISTS blog_comment;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE blogger (
    account VARCHAR(20) PRIMARY KEY,
    PASSWORD VARCHAR(64) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    image VARCHAR(64),
    brief VARCHAR(200),
    create_time DATETIME NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE blog_type (
    id INT(5) PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(20)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE blog_text (
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    brief VARCHAR(100) NOT NULL,
    context VARCHAR(50000),
    create_time DATETIME NOT NULL,
    last_mod DATETIME NOT NULL,
    read_count INT(10) DEFAULT 1 NOT NULL,
    like_count INT(10) DEFAULT 0 NOT NULL,
    blog_type_id INT(5),
    blogger_account VARCHAR(20),
    CONSTRAINT fk_btn FOREIGN KEY(blog_type_id) REFERENCES blog_type(id) ON DELETE CASCADE,
    CONSTRAINT fk_bid FOREIGN KEY(blogger_account) REFERENCES blogger(account) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE blog_comment (
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    nickname VARCHAR(20) NOT NULL, 
    comment_text VARCHAR(200) NOT NULL,
    comment_time DATETIME NOT NULL,
    blog_text_id INT(11),
    CONSTRAINT fk_com_blog FOREIGN KEY(blog_text_id) REFERENCES blog_text(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO blogger 
VALUES(123456,123456,'admin','/image/profile/admin.jpg','这是admin的博客','2018-10-1 10:10:10.63');

INSERT INTO blogger 
VALUES(101010,101010,'user','/image/profile/user.jpg','这是user的博客','2019-10-1 10:10:10.63');

INSERT INTO blog_type 
VALUES(1,'技术'),(2,'生活'),
(3,'情感'),(4,'美食'),
(5,'汽车'),(6,'游戏'),(7,'科技');

INSERT INTO blog_comment
VALUES(1,'user','过来支持一下，文章写的很好','2021-4-16 23:11:10',2);

ALTER TABLE blog_text MODIFY COLUMN context VARCHAR(50000);