package ssj.component;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import ssj.domain.JwtToken;
import ssj.util.JwtUtils;

@Component
public class SsjRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("SsjRealm doGetAuthorizationInfo() 方法授权 ");
        String token = principalCollection.toString();
        String username = JwtUtils.getClaim(token,"username");
        if (username == null || username == "") {
            throw new AuthenticationException("token认证失败");
        }
        //查询当前
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //其实这里应该是查询当前用户的角色或者权限去的,意思就是将当前用户设置一个svip和vip角色
        //权限设置一级权限和耳机权限 正常来说应该是去读取数据库只添加当前用户的角色权限的
        info.addRole("vip");
        info.addRole("svip");
        info.addStringPermission("一级权限");
        info.addStringPermission("二级权限");
        System.out.println("方法结束咯-------》》》");

        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证-----------》》》》");
        System.out.println("1.toString ------>>> " + authenticationToken.toString());
        System.out.println("2.getCredentials ------>>> " + authenticationToken.getCredentials().toString());
        System.out.println("3. -------------》》 " +authenticationToken.getPrincipal().toString());
        String jwt = (String) authenticationToken.getCredentials();
        String userName = JwtUtils.getUserName(jwt);

        return new SimpleAuthenticationInfo(jwt, jwt, "SsjRealm");
    }
}
