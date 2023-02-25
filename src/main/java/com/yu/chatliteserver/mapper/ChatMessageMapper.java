package com.yu.chatliteserver.mapper;

import com.yu.chatliteserver.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-23 05:17:00
 */
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    @Select("SELECT * FROM tb_chat_message WHERE user_id = #{userId}")
    List<ChatMessage> selectByUserId(String userId);
}
