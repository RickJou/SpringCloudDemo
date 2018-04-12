package com.demo.protocol.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserInfoRequest", description = "用户信息请求参数包装类")
public class UserInfoRequest {
	// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示
	@ApiModelProperty(name = "name", value = "名称", required = true)
	private String name;
	@ApiModelProperty(name = "age", value = "年龄", required = true)
	private String age;
	@ApiModelProperty(name = "gender", value = "性别", required = true, allowableValues = "[0,1]", example = "男:1;女:0;")
	private Integer gender;

}
