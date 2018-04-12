package com.demo.protocol;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="标准协议部分")
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "响应码", required = true, dataType = "String", allowEmptyValue = false)
	private String responseCode;
	@ApiModelProperty(value = "响应信息", required = true, dataType = "String", allowEmptyValue = false)
	private String message;
	@ApiModelProperty(value = "来源程序编码", required = true, dataType = "String", allowEmptyValue = false)
	private String fromId;
	@ApiModelProperty(value = "目标程序编码", required = true, dataType = "String", allowEmptyValue = false)
	private String toId;
	@ApiModelProperty(value = "交易流水号", required = true, dataType = "String", allowEmptyValue = false)
	private String transNo;
	@ApiModelProperty(value = "会话ID", required = true, dataType = "String", allowEmptyValue = false)
	private String sessionId;
	@ApiModelProperty(value = "会话时间戳", required = true, dataType = "String", allowEmptyValue = false)
	private String currentTime;
	@ApiModelProperty(value = "功能号", required = true, dataType = "String", allowEmptyValue = false)
	private String functionCode;
	@ApiModelProperty(value = "版本号", required = true, dataType = "String", allowEmptyValue = false)
	private String version;
	@ApiModelProperty(value = "保留字段1", required = false, dataType = "String", allowEmptyValue = true)
	private String reserveField1;
	@ApiModelProperty(value = "保留字段2", required = false, dataType = "String", allowEmptyValue = true)
	private String reserveField2;
}