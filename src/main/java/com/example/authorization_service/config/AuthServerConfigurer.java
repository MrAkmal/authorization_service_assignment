package com.example.authorization_service.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfigurer extends AuthorizationServerConfigurerAdapter {


    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        InMemoryClientDetailsService service = new InMemoryClientDetailsService();
//
//        BaseClientDetails clientDetails = new BaseClientDetails();
//
//        clientDetails.setClientId("client");
//        clientDetails.setClientSecret("secret");
//        clientDetails.setScope(List.of("read"));
//        clientDetails.setAuthorizedGrantTypes(List.of("password"));
//
//        service.setClientDetailsStore(Map.of("client",clientDetails));
//        clients.withClientDetails(service);


        clients.inMemory()
                .withClient("client")
                .secret("secret")
//                .authorizedGrantTypes("password")
                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .authorizedGrantTypes("client_credentials")
                .scopes("read")
                .redirectUris("http://localhost:8090/home");

    }


//    @Bean
//    public TokenStore tokenStore() {
//        return new JwkTokenStore("http://localhost:8090/home",jwtAccessTokenConverter());
//    }


//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(jwtKey);
//        return converter;
//    }

}
