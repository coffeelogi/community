package com.shecoder.community.entity;

/*
*封装分页相关信息
 */
public class Page {

    //当前的页面
    private int current = 1;
    //显示上限
    private int limit = 10;
    //数据总数（用于计算总页数）
    private int rows;
    //查询路径（用于复用分页链接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >=1 && limit <=100){
            this.limit = limit;
        }

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >=0){
            this.rows = rows;
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //获得当前页的起始行
    public int getOffset(){
        //current*limit-limit   截止当前页的总行数 - 当前页行数 = 就是当前页起始行数
        return (current - 1) * limit;
    }

    //获得总页数
    public int getTotal(){
        //rows/limit[+1]  不能整除，就会多出一页
        if(rows % limit == 0){
            return rows/limit;
        }else{
            return rows/limit + 1;
        }
    }
    //获得起始页
    public int getFrom(){
        int from = current - 2;
        return from < 1 ? 1 : from;
    }
    //获取结束页
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
