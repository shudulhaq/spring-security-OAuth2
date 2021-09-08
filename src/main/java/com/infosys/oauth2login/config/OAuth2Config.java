package com.infosys.oauth2login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private String clientId = "latihan";
    private String clientSecret = "latihan-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAuV6Fp0wRSj8bsTQuHVBoM0bS4AZt7tIFyJsjf/WTDR2ZuPI1\n" +
            "NG8el1tWdXSeMX8NdYohxrUeQnC6FJW2x6wkzx6CjbpBy83WS5uLaYAVMRitQBvr\n" +
            "ma26QHXFG3x+Aap4NQI2hwEyTqrQB56CQAzhcA7OaeVlYU4lEV+H/BVP5vUoUSfN\n" +
            "7MbxP7cZ3KfYlXff+0f2EWO7f+jU5rzS30quGTS09bklT+2D2mIw57o5ndgEN030\n" +
            "I44PaQRXehbQMAE94Vc3xC9AiU0rqlvLD2GdZOwg5FlprvRSMnB4XUQK8kZfTHGU\n" +
            "P9JmiRCD+vjyXW/4ti1yYmFlFtkv5J8zcRFQzwIDAQABAoIBAHFe3/tLyJ9SLYuZ\n" +
            "UELZdIE35aziAcY4aRUh3PpbpU42NlgoumWROHJ1iFFZu46mGAM1MicnipQ0AXqp\n" +
            "fhpsrC6IiGxJ6AfPoiqnvQIPNebLItq8uMFKwUi/PofrAd3e9pnk+phjIPOAAK9I\n" +
            "QH0P46j3RUL666AxfPY9KRwBpNapOp5JJZTL2UQabbyXuG1ZZEaqeqPhtEX99gUH\n" +
            "kOO0Cjls6+4Llg6l6qwUP94cnpibNdFoLHReo9CgCeU/riZlBlE1qDoMLOGZ99K0\n" +
            "nURRvHb8DtCBoqMvkkp7bE1Efnt+HA2XMHc2KzitPr6LmK7r5Ar0n+k76GFu2fUS\n" +
            "++5vB6ECgYEA6X0RE+d2t5hbDc6DQrkVIFm0pIjZHQy4s3uIZOXEaCihZQJunSf+\n" +
            "C4xvRqJtzLmjP6Q6wWEClMDJX+GSM/F0dyc2Pgo2/8CG1UgBBeDfS2T+Hur90uNx\n" +
            "aQBdPMs8tN3Nj4g86p2zpoTwaRz4bWbKfi+xMTSjhO5KqIQTOoFWekcCgYEAyz3H\n" +
            "4zQyaSWn/QocH2PDhn5XKIOsJjL/g2nDlhONNbhtXYxc4x3ofKxUJlSzXXCnyxkV\n" +
            "ytDiAfzVDeqKl6HsNHPffN1C3ysQVv43xSm2ZOZ+ZDN5yrPXFLc0pNxNpRlPNc45\n" +
            "qCYiKGyCkbdSHFRw22wNVYP3UmlgQNCoEYETsTkCgYEAnVUSKehhfctjJ8WNe2t+\n" +
            "CeOIrworysBkwUSvntWY6FrgjlD6OlnVfPCvltgixr4ce+jtUptNAvF5gGteejPs\n" +
            "t9GrYli9wM55zMQNNGnX7VTrS+grC20JYajGKw4EJRgEyQATlECZ6s5PtXS5Dfci\n" +
            "4a6/ZpnCyfNMSYtd7aFzpUcCgYADfFfFScvjicXLzTSWRTvBPsF37GozbYR8WIAG\n" +
            "PueZGESn6hRdwNGP8dSvo5w/6gK2tQNuqw51QkTVLEScPSaz75jRp2nSMgbhtxJ1\n" +
            "yzVZnaplRYy9MUBrFolMmoqqXjjBj3vlqmHDl7jVjvNVkQ0udjf0U4MWYsBH3ExF\n" +
            "oJUZ4QKBgErLG70R64X5ue+XrGsBYbuRU+UNeOyoNc+wqMDu4mnqr6+51vojqyRS\n" +
            "36O3I7sBHfAAro2Uy/NsCCGCqWtNlZWUT1ISdieZgXnD12dv1JUnA1wuWYz+EGcY\n" +
            "kNCxhmXjebb0nPtFwdPMLX5HfvHv91XjKhyijQcYKqtlTysb4dXM\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuV6Fp0wRSj8bsTQuHVBo\n" +
            "M0bS4AZt7tIFyJsjf/WTDR2ZuPI1NG8el1tWdXSeMX8NdYohxrUeQnC6FJW2x6wk\n" +
            "zx6CjbpBy83WS5uLaYAVMRitQBvrma26QHXFG3x+Aap4NQI2hwEyTqrQB56CQAzh\n" +
            "cA7OaeVlYU4lEV+H/BVP5vUoUSfN7MbxP7cZ3KfYlXff+0f2EWO7f+jU5rzS30qu\n" +
            "GTS09bklT+2D2mIw57o5ndgEN030I44PaQRXehbQMAE94Vc3xC9AiU0rqlvLD2Gd\n" +
            "ZOwg5FlprvRSMnB4XUQK8kZfTHGUP9JmiRCD+vjyXW/4ti1yYmFlFtkv5J8zcRFQ\n" +
            "zwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
