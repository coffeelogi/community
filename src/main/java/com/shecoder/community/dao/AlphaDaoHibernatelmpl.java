package com.shecoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")
//定义bean的缩略名字
public class AlphaDaoHibernatelmpl implements AlphaDao{
    @Override
    public String select(){
        return"Hibernate";
    }
}
