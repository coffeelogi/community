package com.shecoder.community.dao;

import com.shecoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);//userId=0 不按照用户来查， ！=0 处理. offset是每页起始的行号， limit是最多行数，实现分页
    int selectDiscussPostRows(@Param("userId") int userId);//一共多少条数据. //@Param 注释用于给参数取别名，如果动态查询条件，只有一个参数，并且在<if>里使用，则必须加别名


}
