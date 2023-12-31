package com.shecoder.community;

//import org.junit.Test;
//import org.junit.runner.RunWith;
import com.shecoder.community.dao.DiscussPostMapper;
import com.shecoder.community.dao.UserMapper;
import com.shecoder.community.entity.DiscussPost;
import com.shecoder.community.entity.User;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testSelectUser() {
            User user = userMapper.selectById(101);
            System.out.println(user);

            user = userMapper.selectByName("liubei");
            System.out.println(user);

            user = userMapper.selectByEmail("nowcoder101@sina.com");
            System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.shecoder.community.com/101.png"); //客户头像的url  0-1000个png
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);//rows指的是修改了几行，行数
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void testUpdateUser(){
        int rows = userMapper.updateStatus(150,1);
        System.out.println(rows);
        rows = userMapper.updateHeader(150, "http://www.shecoder.community.com/102.png");
        System.out.println(rows);
        rows = userMapper.updatePassword(150,"hello");
        System.out.println(rows);
    }
    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0,0,10);//userId为0，表示不按照userId来查
        for(DiscussPost post: list){
            System.out.println(post);
        }
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);

    }
}