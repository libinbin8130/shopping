package cn.shopping.admin.vo;

import cn.shopping.admin.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 登陆用户属性
 * @Description:
 * @Author:libin
 * @Date: Created in 10:39 2018/2/28
 */
@Getter
@Setter
public class UserVo {
    private Long id;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private Integer age;
    private Byte sex;
    private String mobile;
    private String email;
    @NotEmpty
    private String validateCode;
}
