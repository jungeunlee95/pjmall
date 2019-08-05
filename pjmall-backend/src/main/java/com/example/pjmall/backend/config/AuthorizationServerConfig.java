package com.example.pjmall.backend.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
// password or authorization code
//		clients.inMemory() 
//			.withClient("pjmall2")
//			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//			.scopes("read", "write", "trust")
//			.resourceIds("sparklr")
//			.accessTokenValiditySeconds(60);

		
		// client credentials
		/*
		 INSERT
		   INTO OAUTH_CLIENT_DETAILS (
				CLIENT_ID,
				RESOURCE_IDS,
				CLIENT_SECRET,
				SCOPE,
				AUTHORIZED_GRANT_TYPES,
				WEB_SERVER_REDIRECT_URI,
				AUTHORITIES,
				ACCESS_TOKEN_VALIDITY,
				REFRESH_TOKEN_VALIDITY,
				ADDITIONAL_INFORMATION,
				AUTOAPPROVE )
		VALUES ('pjmall', 'pjmall_api', '1234', 'read,write,trust', 'password,client_credentials', '', 'ROLE_CLIENT', null, null, '{}', '');
		*/
//		clients.inMemory() 
//			.withClient("pjmall")
//			.authorizedGrantTypes("password", "client_credentials")
//			.authorities("ROLE_CLIENT")
//			.scopes("read", "write", "trust")
//			.resourceIds("pjmall_api")
//			.secret("1234");
			//.accessTokenValiditySeconds(60);
		
		clients
			.jdbc(dataSource());
	}
	
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	// OAuth2 서버가 작동하기 위한 Endpoint에 대한 정보를 설정
        endpoints
        	.tokenStore( new JdbcTokenStore(dataSource()) )
        		.authenticationManager(authenticationManager);
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        return new BasicDataSource();
    }
}