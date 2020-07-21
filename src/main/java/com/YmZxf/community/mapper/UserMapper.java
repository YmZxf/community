package com.YmZxf.community.mapper;

import com.YmZxf.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    public void insert(User user);
    @Select("select * from tb_user where token=#{token}")
    User findByToken(@Param("token") String token);

}
