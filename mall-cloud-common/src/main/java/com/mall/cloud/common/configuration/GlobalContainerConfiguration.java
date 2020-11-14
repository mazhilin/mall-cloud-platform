package com.mall.cloud.common.configuration;

import com.mall.cloud.common.threadlocal.ApplicationThreadLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <p>封装Qicloud项目GlobalContainerConfiguration类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public class GlobalContainerConfiguration {
	/**
	 * 构建全局Cors配置
	 *
	 * @return 返回cors
	 */
	private CorsConfiguration buildConfig() {
		final CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowCredentials(Boolean.TRUE);
		cors.addAllowedHeader("*");
		cors.addAllowedOrigin("*");
		cors.addAllowedMethod("*");
		return cors;
	}

	/**
	 * 设置CorsFilter拦截器
	 *
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}


	@Bean
	public ApplicationThreadLocal applicationThreadLocal() {
		return new ApplicationThreadLocal();
	}

}
