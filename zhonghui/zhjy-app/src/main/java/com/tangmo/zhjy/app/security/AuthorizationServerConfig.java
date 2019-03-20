package com.tangmo.zhjy.app.security;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.tangmo.zhjy.app.security.properties.OAuth2ClientProperties;
import com.tangmo.zhjy.app.security.properties.SecurityProperties;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityProperties  securityProperties;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required=false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		System.out.println(tokenStore);
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
		if(jwtAccessTokenConverter!=null) {
			endpoints.accessTokenConverter(jwtAccessTokenConverter);
		}
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder =clients.inMemory();
		if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())){
			for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
				builder
				.withClient(config.getClientId())
				.secret(config.getClientSecret())
				.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
				.authorizedGrantTypes("refresh_token","password")
				.scopes("all","read","write");
			}}
	}

}
