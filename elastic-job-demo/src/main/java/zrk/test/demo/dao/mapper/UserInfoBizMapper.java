package zrk.test.demo.dao.mapper;

import zrk.test.demo.dao.empty.UserInfo;

public interface UserInfoBizMapper {
	
    UserInfo selectByTaskOver(Integer taskOverId);
   
}