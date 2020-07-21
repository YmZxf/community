package com.YmZxf.community.mapper;

import com.YmZxf.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    public void insert(User user);
}
