package ssj.domain;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author djp
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    //构造方法
    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
