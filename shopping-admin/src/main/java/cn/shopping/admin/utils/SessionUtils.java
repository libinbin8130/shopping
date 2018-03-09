package cn.shopping.admin.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * httpSession工具类
 * @Description:
 * @Author:libin
 * @Date: Created in 17:59 2018/2/28
 */
public class SessionUtils {

    public static final String SESSION_KEY_CAPTCHA = "session_captcha";

    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpSession getCurrentSession(){
        return getHttpServletRequest().getSession();
    }

    public static void put(String name,Object value){
        getCurrentSession().setAttribute(name,value);
    }

    public static Object get(String name){
        return null==getCurrentSession()?null:getCurrentSession().getAttribute(name);
    }
    public static void remove(String name){
        getCurrentSession().removeAttribute(name);
    }
}
