package com.web.demo.Dao.Mapper;

import com.web.demo.Dao.MyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM user")
    List<MyUser> getAll();

    @Select("SELECT * FROM user WHERE username=#{username}")
    MyUser getOne(String username);

    @Insert("INSERT INTO user(username, password, permission, email, lastIp, lastTime, userUuid, telephone) VALUES(" +
            " #{username}, #{password}, #{permission}, #{email}, #{lastIp}, #{lastTime}, #{userUuid}, #{telephone}) ")
    void insert(MyUser user);

    @Update("UPDATE user SET password=#{password}, email=#{email},telephone=#{telephone}WHERE username=#{username}" )
    void update(MyUser user);


}
