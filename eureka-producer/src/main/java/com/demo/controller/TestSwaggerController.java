package com.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.protocol.BaseResponse;
import com.demo.protocol.request.UserInfoRequest;
import com.demo.protocol.response.UserInfoResponse;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/testSwagger")
public class TestSwaggerController {

	@ApiOperation(value="模拟标准报文接口", notes="head:标准协议参数,body:业务参数.",consumes="application/json;charset=UTF-8",produces="application/json;charset=UTF-8",protocols="http",tags={"标准协议接口"})
	@ApiImplicitParams({
		 @ApiImplicitParam(name="requestBody",value="请求消息体", required=true, dataType="UserInfoRequest", paramType="body"),
		 @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType="header"),
		 @ApiImplicitParam(name="sessionId",value="会话ID", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="toId",value="目标程序编码", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="fromId",value="来源程序编码", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="appId",value="应用ID", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="orgCode",value="机构ID", required=false, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="transNo",value="交易流水ID", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="currentTime",value="会话时间戳", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="functionCode",value="功能号", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="version",value="接口版本号", required=true, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="reserveField1",value="保留字段1", required=false, dataType="String", paramType="header"),
		 @ApiImplicitParam(name="reserveField2",value="保留字段2", required=false, dataType="String", paramType="header")
	})
	@RequestMapping(value = "/testCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public UserInfoResponse testCall(
			@RequestBody UserInfoRequest requestBody,
			@RequestHeader String name,
			@RequestHeader String sessionId,
			@RequestHeader String toId,
			@RequestHeader String fromId,
			@RequestHeader String appId,
			@RequestHeader String orgCode,
			@RequestHeader String transNo,
			@RequestHeader String currentTime,
			@RequestHeader String functionCode,
			@RequestHeader String version,
			@RequestHeader String reserveField1,
			@RequestHeader String reserveField2
			){
		return null;
	}

}
