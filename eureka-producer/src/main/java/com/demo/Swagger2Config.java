package com.demo;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//启用swagger2
@Configuration
@EnableSwagger2
@Import({
	springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class
})
@SuppressWarnings({"rawtypes","unchecked"})
public class Swagger2Config {
	//@Autowired
	//private TypeResolver typeResolver;
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)//Docket，Springfox的主API配置机制初始化为swagger规范2.0
	    	.apiInfo(apiInfo())
	        .select()//select()返回一个ApiSelectorBuilder实例，对通过swagger公开的端点进行精细控制
	          //.apis(RequestHandlerSelectors.any())//允许选择RequestHandler使用谓词。这里的例子使用any谓词（默认）。提供的谓词是any，none，withClassAnnotation，withMethodAnnotation和 basePackage。
		      .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外  
	          //.paths(PathSelectors.any())//允许选择Path使用谓词。这里的例子使用any谓词（默认）。开箱即用的，我们提供了谓词regex，ant，any，none。
	          .build()//ApiSelectorBuilder需要在配置apis和paths之后构建
	        //.pathMapping("/")//当servlet有路径映射时，添加一个servlet路径映射。
	        //.directModelSubstitute(LocalDate.class, String.class)//	便利规则生成器，代替LocalDate与String渲染模型的属性时
	        //.genericModelSubstitutes(ResponseEntity.class)
	        /*.alternateTypeRules(//便捷规则生成器，用泛型类型参数替换一个类型参数。
	            newRule(typeResolver.resolve(DeferredResult.class,//DeferredResult<ResponseEntity<T>> 用T一般来代替 。
	                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                typeResolver.resolve(WildcardType.class)))*/
	        //.useDefaultResponseMessages(false)//是否需要使用默认http响应代码。
	        //允许全局覆盖不同http方法的响应消息。在这个例子中，我们覆盖了所有GET请求的500错误代码
	        /*.globalResponseMessage(RequestMethod.GET,
	            newArrayList(new ResponseMessageBuilder()
	                .code(500)
	                .message("500 message")
	                .responseModel(new ModelRef("Error"))//表明它将使用响应模型Error（将在其他地方定义）
	                .build()))*/
	        //设置用于保护apis的安全机制。支持的方案是ApiKey，BasicAuth和OAuth
	        //这里我们使用ApiKey作为由名称标识的安全模式
	        //name:mykey,keyname:api_key,passAs:header
	        //.securitySchemes(newArrayList(new ApiKey("mykey", "api_key", "header")))
	        //提供一种全局设置操作安全上下文的方法。这里的做法是:securityContext()。
	        //.securityContexts(newArrayList(securityContext()))
	        //.enableUrlTemplating(true)
	        /*.globalOperationParameters(//允许全局配置默认的路径/请求/头参数，在Spring Controller方法签名中不在需要重复配置(如认证信息)
	            newArrayList(new ParameterBuilder()
	                .name("someGlobalParameter")
	                .description("Description of someGlobalParameter")
	                .modelRef(new ModelRef("string"))
	                .parameterType("query")
	                .required(true)
	                .build()))*/
	        //.tags(new Tag("Pet Service", "All apis relating to pets")) //添加标签是定义服务/操作可以选择的所有可用标签的一种方式。目前这只有名称和说明。
	        //应用程序中是否有“不可达”模型？无法访问的时候，我们有模型，我们希望被描述，但没有明确用于任何操作。
	        //以下例子是返回一个序列化为字符串的模型的操作。我们希望传达字符串模式的期望。这是完成这个的一种方式。
	        //.additionalModels(typeResolver.resolve(AdditionalModel.class)) 
	        ;
	  }

	  private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示  
		  return new ApiInfoBuilder()  
		        .title("Eureka-Producer API")// API 标题  
		        .description("eureka服务生产者对外提供的接口")// API描述  
		        .version("1.0")// 版本号  
		        .build();  
	  }
	  /**
	   * 此SecurityContext适用的路径的选择器。
	   * @return
	   */
	  /*private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .forPaths(PathSelectors.regex("/anyPath.*"))
	        .build();
	  }*/

	  List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    //这里我们使用安全方案中定义的相同密钥 mykey
	    return newArrayList(new SecurityReference("mykey", authorizationScopes));
	  }

	  @Bean
	  SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()//可选swagger-ui安全配置:oauth和apiKey
	        .clientId("test-app-client-id")
	        .clientSecret("test-app-client-secret")
	        .realm("test-app-realm")
	        .appName("test-app")
	        .scopeSeparator(",")
	        .additionalQueryStringParams(null)
	        .useBasicAuthenticationWithAccessCodeGrant(false)
	        .build();
	  }

	  /**
	   * 可选的swagger-ui ui配置目前仅支持验证网址
	   * @return
	   */
	  @Bean
	  UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder()
	        .deepLinking(true)
	        .displayOperationId(false)
	        .defaultModelsExpandDepth(1)
	        .defaultModelExpandDepth(1)
	        .defaultModelRendering(ModelRendering.MODEL)
	        .displayRequestDuration(false)
	        .docExpansion(DocExpansion.FULL)
	        .filter(true)
	        .maxDisplayedTags(null)
	        .operationsSorter(OperationsSorter.ALPHA)
	        .showExtensions(false)
	        .tagsSorter(TagsSorter.ALPHA)
	        //.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
	        .validatorUrl(null)
	        .build();
	  }
	  
	  
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
	    return () -> {
	    	List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
	    	
	    	TreeMap allService = getAllServiceByEureka();
	    	allService.forEach((serviceName,value)->{
	    		List<String> serviceInstances = (List<String>) value;
	    		serviceInstances.forEach(serviceUri->{
	    			SwaggerResource wsResource = new SwaggerResource();
			        wsResource.setName(serviceName+"-"+serviceUri);
			        wsResource.setSwaggerVersion("2.0");
			        wsResource.setUrl("");
			        wsResource.setLocation(serviceUri+"/v2/api-docs");
			        //wsResource.setUrl(serviceUri);
			        //wsResource.setUrl(serviceUri+"/v2/api-docs");
			        resources.add(wsResource);
	    		});
	    	});
	        return resources;
	    };
	    
	}
	
	/**
	 * 获取Eureka上面的所有服务
	 * @return TreeMap<服务名,List<该服务的所有在线运行节点>>
	 */
	private TreeMap getAllServiceByEureka(){
    	List<ServiceInstance> temp = new ArrayList<>();

		List<String> allServiceId = discoveryClient.getServices();
		for (String serviceId : allServiceId) {
			List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
			if (list != null && !list.isEmpty()) {
				temp.addAll(list);
			}
		}
		
		//获取所有的服务和所在的地址
		TreeMap nameAndUri = new TreeMap<>();
		for (ServiceInstance serviceInstance : temp) {
			if(!nameAndUri.containsKey(serviceInstance.getServiceId().toString())){
				nameAndUri.put(serviceInstance.getServiceId().toString(), new ArrayList<>());
			}
			ArrayList list = (ArrayList) nameAndUri.get(serviceInstance.getServiceId().toString());
			list.add(serviceInstance.getUri().toString());
		}
		
		return nameAndUri;
    }
	
}
