package com.demo.protocol.response;

import com.demo.protocol.BaseResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户详细信息返回消息体")
public class UserInfoResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示
	@ApiModelProperty(name = "name", value = "名称", required = true)
	private String name;
	@ApiModelProperty(name = "age", value = "年龄", required = true)
	private String age;
	@ApiModelProperty(name = "gender", value = "性别", required = true, allowableValues = "[0,1]", example = "男:1;女:0;")
	private Integer gender;
}
