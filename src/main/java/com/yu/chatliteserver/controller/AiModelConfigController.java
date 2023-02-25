package com.yu.chatliteserver.controller;

import com.yu.chatliteserver.entity.AiModelConfig;
import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.request.AiModelConfigFormRequest;
import com.yu.chatliteserver.service.IAiModelConfigService;
import com.yu.chatliteserver.vo.AiModelConfigVO;
import com.yu.chatliteserver.vo.R;
import com.yu.chatliteserver.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器 ai模型配置
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
@RestController
@RequestMapping("/aiModelConfig")
public class AiModelConfigController {

    @Autowired
    public IAiModelConfigService iAiModelConfigService;

    /**
     * 新增
     * @param aiModelConfigFormRequest
     * @return
     */
    @PostMapping("")
    public R addModelConfig(AiModelConfigFormRequest aiModelConfigFormRequest) {
        AiModelConfig aiModelConfig = new AiModelConfig();
        BeanUtils.copyProperties(aiModelConfigFormRequest, aiModelConfig);
        boolean success = iAiModelConfigService.saveAiModelConfig(aiModelConfig);
        if (success) {
            AiModelConfigVO aiModelConfigVO = new AiModelConfigVO();
            BeanUtils.copyProperties(aiModelConfig, aiModelConfigVO);
            return R.ok(aiModelConfigVO);
        } else {
            throw new RuntimeException("新增失败");
        }
    }

    /**
     * 列表不分页
     * @return
     */
    @GetMapping("list")
    public R getAiModelConfigList() {
        List<AiModelConfig> aiModelConfigs = iAiModelConfigService.list();

        List<AiModelConfigVO> list = aiModelConfigs.stream().map(aiModelConfig -> {
            AiModelConfigVO aiModelConfigVO = new AiModelConfigVO();
            BeanUtils.copyProperties(aiModelConfig, aiModelConfigVO);
            return aiModelConfigVO;
        }).collect(Collectors.toList());
        return R.ok(list);
    }
}
