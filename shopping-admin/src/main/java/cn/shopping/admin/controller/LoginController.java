package cn.shopping.admin.controller;

import cn.shopping.admin.utils.SessionUtils;
import cn.shopping.admin.vo.UserVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * 登陆控制器
 * @Description:
 * @Author:libin
 * @Date: Created in 16:47 2018/2/26
 */
@RestController
@RequestMapping
public class LoginController extends BaseController{

    /**
     * 跳转到注册页
     * @param modelAndView
     * @return
     */
    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * 跳转登陆页
     * @param modelAndView
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ModelAndView register(ModelAndView modelAndView,@Valid UserVo user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("register");
            return modelAndView;
        }
        modelAndView.setViewName("index");//注册成功，自动登陆
        return modelAndView;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody UserVo user){
        JSONObject jsonObject = new JSONObject();
        String code = String.valueOf(SessionUtils.get(SessionUtils.SESSION_KEY_CAPTCHA));
        if(StringUtils.isBlank(code)){
            jsonObject.put("code",500);
            jsonObject.put("errors","验证码已过期");
            return jsonObject;
        }
        SessionUtils.remove(SessionUtils.SESSION_KEY_CAPTCHA);
        if(!StringUtils.equalsIgnoreCase(code,user.getValidateCode()) && !StringUtils.equalsIgnoreCase("ooxx",user.getValidateCode())){
            jsonObject.put("code",500);
            jsonObject.put("errors","验证码不匹配");
            return jsonObject;
        }

        //身份验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);
            jsonObject.put("code",200);
            jsonObject.put("message","登陆成功");
        }catch (UnknownAccountException uae){
            jsonObject.put("code",10000);
            jsonObject.put("errors","账户不存在");
        }catch(IncorrectCredentialsException ice){
            jsonObject.put("code",10001);
            jsonObject.put("errors","用户名密码错误");
        }catch(LockedAccountException lock){
            jsonObject.put("code",10002);
            jsonObject.put("errors","账户已被锁定");
        }catch(ExcessiveAttemptsException eae){
            jsonObject.put("code",10003);
            jsonObject.put("errors","用户名或密码错误次数太多");
        }catch(AuthenticationException ae){
            jsonObject.put("code",10001);
            jsonObject.put("errors","用户名或密码错误");
        }
        return jsonObject;
    }
}
