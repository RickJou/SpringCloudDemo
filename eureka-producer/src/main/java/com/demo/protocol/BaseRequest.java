package com.demo.protocol;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="会话ID", hidden=false, notes="值唯一", required=true, dataType="String")
	private String sessionId;
	@ApiModelProperty(value="目标程序ID", hidden=false, notes="", required=true, dataType="String")
	private String toId;
	@ApiModelProperty(value="来源程序 ID", hidden=false, notes="", required=true, dataType="String")
	private String fromId;
	@ApiModelProperty(value="应用ID", hidden=false, notes="", required=true, dataType="String")
	private String appId;
	@ApiModelProperty(value="机构ID", hidden=false, notes="", required=false, dataType="String")
	private String orgCode;
	@ApiModelProperty(value="交易流水号", hidden=false, notes="", required=true, dataType="String")
	private String transNo;
	@ApiModelProperty(value="会话时间戳", hidden=false, notes="", required=true, dataType="String")
	private String currentTime;
	@ApiModelProperty(value="功能号", hidden=false, notes="", required=true, dataType="String")
	private String functionCode;
	@ApiModelProperty(value="版本号", hidden=false, notes="", required=true, dataType="String")
	private String version;
	@ApiModelProperty(value="保留字段1", hidden=false, notes="", required=true, dataType="String")
	private String reserveField1;
	@ApiModelProperty(value="保留字段2", hidden=false, notes="", required=true, dataType="String")
	private String reserveField2;
	//@ApiModelProperty(value="业务参数", hidden=false, notes="", required=true, dataType="String")
	//private Map<String, Object> requestBody;

}