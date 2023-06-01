/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

//里面标注了@Configuration注解，表明这是一个配置类，功能与@Configuration无异
@SpringBootConfiguration

//实现自动装配的核心注解，是用来激活自动装配的，其中默认路径扫描以及组件装配、排除等都通过它来实现
@EnableAutoConfiguration

//扫描被@Component标注的类，只不过这里是用来过滤Bean的，指定哪些不进行扫描，而且用的是自定义规则
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class), @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
	//根据class来排除，排除指定的类加入spring容器，传入的类型是class类型。且继承自 @EnableAutoConfiguration 中的属性
	@AliasFor(annotation = EnableAutoConfiguration.class)
	Class<?>[] exclude() default {};

	//根据class name来排除，排除特定的类加入spring容器，参数类型是class的全类名字符串数组。同样继承自 @EnableAutoConfiguration
	@AliasFor(annotation = EnableAutoConfiguration.class)
	String[] excludeName() default {};

	//可以指定多个包名进行扫描。继承自 @ComponentScan
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	String[] scanBasePackages() default {};

	//可以指定多个类或接口的class，然后扫描 class 所在包下的所有组件。同样继承自 @ComponentScan
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
	Class<?>[] scanBasePackageClasses() default {};

}
