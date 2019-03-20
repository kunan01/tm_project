package com.tangmo.zhjy.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.tangmo.zhjy.app.security.properties.SecurityProperties;

@Configuration
public class TokenStoreConfig {

	@Autowired
	private RedisConnectionFactory  redisConnectionFactory;

	@Bean
	@ConditionalOnProperty(prefix="tangmo.zhjy.oauth2",name="storeType",havingValue="redis")
	public TokenStore redisTokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}
	

	@Configuration
	@ConditionalOnProperty(prefix="tangmo.zhjy.oauth2",name="storeType",havingValue="jwt",matchIfMissing=true)
	public static class JwtTokenConfig{

		@Autowired
		private SecurityProperties securityProperties;

		@Bean
		public TokenStore jwtTokenStore(){
			return new JwtTokenStore(jwtAccessTokenConverter());
		}

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
			//签名
			jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
			return jwtAccessTokenConverter;
		}

	}
}
