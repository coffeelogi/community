package com.shecoder.community.controller;

import com.shecoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获得请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();) {
            writer.write("<h1> she coder 你好</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        // GET请求
        // /students?current=1&limit=20
        @RequestMapping(path = "/students", method = RequestMethod.GET)
        @ResponseBody
        public String getStudents(
              @RequestParam(name = "current", required = false, defaultValue = "1") int current,
        @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){

            System.out.println(current);
            System.out.println(limit);
            return "some students";
        }

        // /student/123
        @RequestMapping(path="/students/{id}", method = RequestMethod.GET)
        @ResponseBody
        public String getStudent(@PathVariable("id") int id){
            System.out.println(id);
            return "a student";
        }
        //POST
        @RequestMapping(path="/student", method=RequestMethod.POST)
        @ResponseBody
        public String saveStudent(String name, int age){
            System.out.println(name); //跟student.html中的name变量名一样就行
            System.out.println(age); //跟student.html中的name变量名一样就行
            return "success";
        }
        //响应HTML数据
    //"Model"对象和"ModelAndView"是Spring MVC框架中常用的用于处理视图和模型数据的对象，但它们之间有一些区别：
        //Model对象：是Spring MVC框架中用于封装模型数据的对象。它是一个接口，通常由控制器方法的参数声明，Spring框架会自动创建并传递给控制器方法。开发者可以使用Model对象的方法来存储和获取模型数据，以便在视图中使用。
        //ModelAndView对象：是Spring MVC框架中的一个包含模型数据和视图名称的对象。它可以同时封装模型数据和视图信息。开发者可以通过实例化ModelAndView对象，并设置模型数据和视图名称，然后将其返回给控制器方法来实现模型数据和视图的传递
        @RequestMapping(path="/teacher", method = RequestMethod.GET)
        public ModelAndView getTeacher(){
            ModelAndView mav= new ModelAndView();
            mav.addObject("name","zhangsan");
            mav.addObject("age",30);
            mav.setViewName("/demo/view");
            return mav;
        }
    //响应HTML数据 方法二，略简单
        @RequestMapping(path="/school", method = RequestMethod.GET)
        public String getSchool(Model model){ //此Model为interface
            model.addAttribute("name","Beijin University");
            model.addAttribute("age", 100);
            return "/demo/view";
        }
    //响应 JSON数据（一般在异步请求：网页不刷新，访问了服务器）
    //JAVA对象 -> JSON字符串(转换） -> JS对象

    @RequestMapping(path="/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary",8000.00);
        return emp;
    }
    //响应多组json数据
    @RequestMapping(path="/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary",8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "lisi");
        emp.put("age", 25);
        emp.put("salary",9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "wangwu");
        emp.put("age", 29);
        emp.put("salary",10000.00);
        list.add(emp);

        return list;
    }

}
