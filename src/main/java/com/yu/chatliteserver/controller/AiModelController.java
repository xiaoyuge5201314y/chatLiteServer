package com.yu.chatliteserver.controller;

import com.yu.chatliteserver.service.IAiModelService;
import com.yu.chatliteserver.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
@RestController
@RequestMapping("/aiModel")
public class AiModelController {

    @Autowired
    private IAiModelService iAiModelService;

    // 不分页列表
    @GetMapping("list")
    public R getAiModelList() {
        return R.ok(iAiModelService.list());
    }
}
