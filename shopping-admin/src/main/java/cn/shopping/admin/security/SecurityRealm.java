package cn.shopping.admin.security;

import cn.shopping.admin.vo.UserVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义 Realm,从数据中获取身份信息，进行验证
 * @Description:
 * @Author:libin
 * @Date: Created in 10:47 2018/3/9
 */
public class SecurityRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        UserVo user = new UserVo();
        user.setUserName("admin");
        user.setPassword(new Md5Hash("123456").toHex());
        if(null == user){
            throw new UnknownAccountException();//没找到账号
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, //用户名
                user.getPassword(), //密码
                //    ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt,采用明文访问时，不需要此句
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
