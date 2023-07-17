package com.shecoder.community.service;

import com.shecoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
// 可以创建多个实例，每次getBean时都会实例化一个bean
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("Instantiate AplhaService");
    }
    @PostConstruct
    //该注解的意思是在构造器constructor之后调用
    public void init(){
        System.out.println(" Initialize AlphaService");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Destroy AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
