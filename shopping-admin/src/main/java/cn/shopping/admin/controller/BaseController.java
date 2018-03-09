package cn.shopping.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类
 * @Description:
 * @Author:libin
 * @Date: Created in 16:48 2018/2/26
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


}
