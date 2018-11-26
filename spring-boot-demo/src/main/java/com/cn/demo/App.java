package com.cn.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;



@SpringBootApplication
//@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Configuration
@MapperScan("com.cn.*.service")
@ComponentScans({@ComponentScan("com.cn.*")})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

   @Bean
	public HttpMessageConverters fastJsonHttpMessageConvert() {
		FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteNullNumberAsZero);
		fastConvert.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converters =fastConvert;
		return new HttpMessageConverters(converters);
	}

}
