package web.kwong.test;

/**
 * Created by Administrator on 2015/7/21.
 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {
//    @RequestMapping("/login")  // 请求url地址映射，类似Struts的action-mapping
//    public String testLogin(@RequestParam(value="username")String username, String password, HttpServletRequest request) {
//        // @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
//        // @RequestParam可简写为：@RequestParam("username")
//
//        if (!"admin".equals(username) || !"admin".equals(password)) {
//            return "loginError"; // 跳转页面路径（默认为转发），该路径不需要包含spring-servlet配置文件中配置的前缀和后缀
//        }
//        return "loginSuccess";
//    }
    @RequestMapping("/login")  // 请求url地址映射，类似Struts的action-mapping
    public String testLogin() {
        // @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
        // @RequestParam可简写为：@RequestParam("username")

//        if (!"admin".equals(username) || !"admin".equals(password)) {
//            return "loginError"; // 跳转页面路径（默认为转发），该路径不需要包含spring-servlet配置文件中配置的前缀和后缀
//        }
        System.out.println("进入控制类!");
        return "loginSuccess";
    }
}
