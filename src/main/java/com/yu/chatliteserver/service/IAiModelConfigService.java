package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.AiModelConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
public interface IAiModelConfigService extends IService<AiModelConfig> {

    boolean saveAiModelConfig(AiModelConfig aiModelConfig);
}
