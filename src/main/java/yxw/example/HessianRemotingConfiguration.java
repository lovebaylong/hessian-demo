package yxw.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import yxw.example.user.remoting.UserRemoting;

@Configuration
public class HessianRemotingConfiguration {

    @Bean("userRemoting")
    HessianProxyFactoryBean proxyUserRemoting() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/demoserver/remoting/UserRemoting");
        proxy.setServiceInterface(UserRemoting.class);
        return proxy;
    }
}
