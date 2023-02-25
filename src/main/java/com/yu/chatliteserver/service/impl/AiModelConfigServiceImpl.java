package com.yu.chatliteserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.chatliteserver.entity.AiModelConfig;
import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.mapper.AiModelConfigMapper;
import com.yu.chatliteserver.service.IAiModelConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
@Service
public class AiModelConfigServiceImpl extends ServiceImpl<AiModelConfigMapper, AiModelConfig> implements IAiModelConfigService {

    @Override
    public boolean saveAiModelConfig(AiModelConfig aiModelConfig) {
        aiModelConfig.setCreateTime(LocalDateTime.now());
        return this.save(aiModelConfig);
    }
}
