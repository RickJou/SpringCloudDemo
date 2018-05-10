package zrk.test.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zrk.test.demo.dao.empty.UserInfo;
import zrk.test.demo.dao.mapper.UserInfoMapper;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper mapper;
	
	public UserInfo getUserInfo(Integer userId){
		return mapper.selectByPrimaryKey(userId);
	}	
}
