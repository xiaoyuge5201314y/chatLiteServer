
package com.yu.chatliteserver.controller.chatController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.swing.Icon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.chatliteserver.entity.ChatConversation;
import com.yu.chatliteserver.entity.ChatConversationDetail;
import com.yu.chatliteserver.service.IChatConversationDetailService;
import com.yu.chatliteserver.service.IChatConversationService;
import com.yu.chatliteserver.service.ISceneService;
import com.yu.chatliteserver.service.IUserService;
import com.yu.chatliteserver.vo.R;

import cn.dev33.satoken.stp.StpUtil;

@Api
@RestController
@RequestMapping("/api/chat/scenarios")
public class ChatScenariosController {

    @Autowired
    private IChatConversationService iChatConversationService;
    @Autowired
    private IChatConversationDetailService iChatConversationDetailService;
    @Autowired
    private ISceneService iSceneService;

    /**
     * 创建一个场景的聊天会话
     */
    @PostMapping("/{id}")
    @ApiOperation(value = "创建一个场景的聊天会话")
    @Transactional
    public R createChatSessionForScenario(@PathVariable("id") String senceId) {
        // 首先根据用户id和场景id生成一条会话表数据
        ChatConversation chatConversation = iChatConversationService.addSenceConversation(senceId);
        String conversationId = chatConversation.getId();
        // 再生成一条会话详情表数据
        iChatConversationDetailService
                .addConversationDetail(conversationId, senceId);
        // 返回前端创建完成
        return R.ok(chatConversation.getId());
    }

    @ApiOperation(value = "获取场景列表")
    @GetMapping("/getSceneList")
    public R getSceneList() {
        return R.ok(iSceneService.list());
    }

}
