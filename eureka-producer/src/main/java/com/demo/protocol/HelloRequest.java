package com.demo.protocol;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HelloRequest {
	// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示  
	@ApiModelProperty(value="名称", hidden=false, notes="请使用姓名", required=true, dataType="String")
	private String name;
	@ApiModelProperty(value="请开始你的表演", hidden=false, notes="打动我们,就为你转身", required=true, dataType="String")
	private String say;
	
	@ApiModelProperty(value="随机数", hidden=false, notes="你的专属,你的唯一", required=true, dataType="String")
	private Long randomId;
}
