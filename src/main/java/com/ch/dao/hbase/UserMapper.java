
package com.ch.dao.hbase;

import com.ch.entity.UserMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserMo selectUserById(@Param("id") Long id);
}
