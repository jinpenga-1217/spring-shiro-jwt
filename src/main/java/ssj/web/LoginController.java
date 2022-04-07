package ssj.web;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ssj.util.JwtUtils;

import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {

    private Logger logger;

    public final Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(getClass());
        }
        return logger;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        String token= JwtUtils.createToken(username, password);
        getLogger().info(token);
        return "登录成功,token : " + token;
    }

    @PostMapping("/validate")
    public String validate(){
        try {
            boolean result = JwtUtils.verify("token");
        } catch (Exception exception) {
            return exception.toString();
        }
        return "校验完成";
    }

    @RequiresRoles("vsvip")
    @PostMapping("/getInfo")
    public String getInfo(){
        getLogger().info("123123");
        return "校验完成";
    }

}
