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
//    @RequestMapping("/login")  // ����url��ַӳ�䣬����Struts��action-mapping
//    public String testLogin(@RequestParam(value="username")String username, String password, HttpServletRequest request) {
//        // @RequestParam��ָ����url��ַӳ���б��뺬�еĲ���(��������required=false)
//        // @RequestParam�ɼ�дΪ��@RequestParam("username")
//
//        if (!"admin".equals(username) || !"admin".equals(password)) {
//            return "loginError"; // ��תҳ��·����Ĭ��Ϊת��������·������Ҫ����spring-servlet�����ļ������õ�ǰ׺�ͺ�׺
//        }
//        return "loginSuccess";
//    }
    @RequestMapping("/login")  // ����url��ַӳ�䣬����Struts��action-mapping
    public String testLogin() {
        // @RequestParam��ָ����url��ַӳ���б��뺬�еĲ���(��������required=false)
        // @RequestParam�ɼ�дΪ��@RequestParam("username")

//        if (!"admin".equals(username) || !"admin".equals(password)) {
//            return "loginError"; // ��תҳ��·����Ĭ��Ϊת��������·������Ҫ����spring-servlet�����ļ������õ�ǰ׺�ͺ�׺
//        }
        System.out.println("���������!");
        return "loginSuccess";
    }
}
