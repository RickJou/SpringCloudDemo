package zrk.test.demo.dao.mapper;

import zrk.test.demo.dao.empty.UserInfo;

public interface UserInfoMapper {
    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}