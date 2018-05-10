package zrk.test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zrk.test.demo.dao.empty.UserInfo;
import zrk.test.demo.service.UserInfoService;

@RestController
@RequestMapping("/userInfoController")
public class UserInfoController {
	@Autowired
	private UserInfoService service;

	@RequestMapping(value = "/getUserInfo/{userId}", produces = { "application/json;charset=UTF-8" })
	public UserInfo getUserInfo(@PathVariable Integer userId) {
		return service.getUserInfo(userId);
	}
}
