package com.mall.cloud.program.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.mall.cloud.common.annotation.container.ApplicationClientBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marklin
 */
@ApplicationClientBootstrap
@EnableDubbo
@EnableMethodCache(basePackages = "com.mall.cloud")
@EnableCreateCacheAnnotation
public class ProgramWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProgramWebApplication.class, args);
  }
}
