package com.yu.chatliteserver.mapper;

import com.yu.chatliteserver.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-16 01:26:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update tb_user set username = #{username}, email = #{email}, version = version + 1 where id = #{id} and version = #{version}")
    int updateByIdWithVersion(User user);
}
