package com.yu.chatliteserver.controller.chatController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;
import com.yu.chatliteserver.entity.AiModel;
import com.yu.chatliteserver.entity.ChatConversation;
import com.yu.chatliteserver.entity.ChatConversationDetail;
import com.yu.chatliteserver.mapper.AiModelMapper;
import com.yu.chatliteserver.mapper.ChatConversationDetailMapper;
import com.yu.chatliteserver.mapper.ChatConversationMapper;
import com.yu.chatliteserver.request.chat.ConversationRequest;
import com.yu.chatliteserver.request.chat.SendChatMessageRequest;
import com.yu.chatliteserver.serverRequest.ChatHttp;
import com.yu.chatliteserver.service.IUserService;
import com.yu.chatliteserver.util.MyJsonUtil;
import com.yu.chatliteserver.vo.R;
import com.yu.chatliteserver.vo.chat.ChatResponseVO;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器 chat会话
 * </p>
 *
 * @author 吴东宇
 * @since 2023-04-09 15:03:11
 */
@RestController
@RequestMapping("api/conversations")
@Api(tags = "会话")
public class ChatConversationController {

    @Autowired
    private ChatConversationMapper conversationMapper;

    @Autowired
    private ChatConversationDetailMapper conversationDetailMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AiModelMapper aiModelMapper;

    @Autowired
    private IUserService userService;

    // 创建会话
    @ApiOperation(value = "创建会话")
    @PostMapping("")
    public R createConversation(@RequestBody ConversationRequest conversationRequest) {
        String userId = request.getHeader("userId");
        System.out.println(conversationRequest);
        System.out.println(userId);

        ChatConversation conversation = new ChatConversation();
        conversation.setId(UUID.randomUUID().toString());
        conversation.setUserId(userId);
        conversation.setAiModelId("1");
        conversation.setCreateTime(LocalDateTime.now());
        conversation.setUpdateTime(LocalDateTime.now());
        conversation.setVersion(1);

        conversationMapper.insert(conversation);
        return R.ok("创建会话成功");
    }

    @ApiOperation(value = "添加会话详情")
    @PostMapping("/{conversation_id}/details")
    public String addConversationDetail(@PathVariable("conversation_id") String conversationId, @RequestParam("messages") String messages) {
        ChatConversationDetail conversationDetail = new ChatConversationDetail();
        conversationDetail.setId(UUID.randomUUID().toString());
        conversationDetail.setConversationId(conversationId);
        conversationDetail.setMessages(messages);
        conversationDetail.setCreateTime(LocalDateTime.now());
        conversationDetail.setUpdateTime(LocalDateTime.now());
        conversationDetail.setVersion(1);

        conversationDetailMapper.insert(conversationDetail);
        return "添加会话详情成功";
    }

    @ApiOperation(value = "获取会话分页列表")
    @GetMapping("/page")
    public R getConversations(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        IPage<ChatConversation> pageData = new Page<>(page, size);
        QueryWrapper<ChatConversation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");

        return R.ok(conversationMapper.selectPage(pageData, queryWrapper).getRecords());
    }
    @ApiOperation(value = "获取会话不分页列表")
    @GetMapping("/list")
    public R getConversationsNotPage() {
        QueryWrapper<ChatConversation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", request.getHeader("userId"));
        return R.ok(conversationMapper.selectList(queryWrapper));
    }
    @ApiOperation(value = "发送消息")
    @PostMapping("/message")
    @Transactional
    public R sendChatMessage(@RequestBody SendChatMessageRequest chatSendMessageRequest) {
        String id = request.getHeader("userId");
        int times = userService.getTimesById(id);
        if (times == 0) {
            return R.error("剩余聊天次数不足");
        }
        userService.setAvailableTimes(id,times-1);
        String conversationId = chatSendMessageRequest.getConversationId();
        if (StringUtil.isNullOrEmpty(conversationId)) {
            String userId = request.getHeader("userId");

            ChatConversation conversation = new ChatConversation();
            conversation.setId(UUID.randomUUID().toString());
            conversation.setUserId(userId);
            conversation.setAiModelId("2");
            conversation.setCreateTime(LocalDateTime.now());
            conversation.setUpdateTime(LocalDateTime.now());
            conversation.setVersion(1);

            conversationMapper.insert(conversation);
            conversationId = conversation.getId();
        }
        // 查询会话详情表 如果没有就创建一个
        QueryWrapper<ChatConversationDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId);
        ChatConversationDetail conversationDetail= conversationDetailMapper.selectOne(queryWrapper);
        if (conversationDetail == null) {
            conversationDetail = new ChatConversationDetail();
            conversationDetail.setId(UUID.randomUUID().toString());
            conversationDetail.setConversationId(conversationId);
            conversationDetail.setMessages("");
            conversationDetail.setCreateTime(LocalDateTime.now());
            conversationDetail.setUpdateTime(LocalDateTime.now());
            conversationDetail.setVersion(0);
            conversationDetailMapper.insert(conversationDetail);
        }
        String messagesStr = conversationDetail.getMessages();
        List<Message> messages = new ArrayList<Message>();
        // 将message json数据转换成数组
        // 历史消息存在时 获取历史消息
        if (!StringUtil.isNullOrEmpty(messagesStr)) {
           List<Message> historyMessages =  (List)MyJsonUtil.toJSONObject(messagesStr);
           messages.addAll(historyMessages);
        }
        // 将请求的消息添加到数组中并发送chatgpt
        // 获取模型信息
        ChatConversation conversation = conversationMapper.selectById(conversationId);
        AiModel aiModel = aiModelMapper.selectById(conversation.getAiModelId());
        Message message = new Message();
        message.setRole("user");
        message.setContent(chatSendMessageRequest.getContent());
        messages.add(message);
        ChatRequestDTO chatRequestDTO = new ChatRequestDTO();
        // 取出最近十条记录
        int firstRecordIndex = 0;
        if (messages.size()>=10) {
            firstRecordIndex = messages.size()-10;
        }
        chatRequestDTO.setMessages(messages.subList(firstRecordIndex,messages.size()));
        chatRequestDTO.setModel(aiModel.getName());

        // 有了详情之后 发送消息给chatgpt
        Message responseMessage =  ChatHttp.chat(chatRequestDTO);
        // 将请求和响应消息添加到历史记录
        messages.add(responseMessage);
        // 将返回结果保存到数据库
        messagesStr = MyJsonUtil.stringify(messages);
        conversationDetail.setMessages(messagesStr);
        conversationDetailMapper.updateById(conversationDetail);
        ChatResponseVO chatResponseVO = ChatResponseVO.builder()
                .role(responseMessage.getRole())
                .content(responseMessage.getContent())
                .conversationId(conversationId)
                .build();
        return R.ok(chatResponseVO);
    }

    @ApiOperation(value = "获取会话消息不分页列表(历史记录)")
    @GetMapping("/message/list/{id}")
    public R getConversationsMessagesNotPage(@PathVariable(value = "id") String conversationId) {
        LambdaQueryWrapper<ChatConversationDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatConversationDetail::getConversationId,conversationId);
        ChatConversationDetail conversationDetail = conversationDetailMapper.selectOne(queryWrapper);
        String messages = conversationDetail.getMessages();
        return R.ok(MyJsonUtil.toJSONObject(messages));
    }


//    @ApiOperation(value = "获取会话详情列表")
//    @GetMapping("/{conversation_id}/details")
//    public List<ChatConversationDetail> getConversationDetails(@PathVariable("conversation_id") String conversationId, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
//        IPage<ChatConversationDetail> pageData = new Page<>(page, size);
//        QueryWrapper<ChatConversationDetail> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("conversation_id", conversationId);
//        queryWrapper.orderByAsc("create_time");
//
//        return conversationDetailMapper.selectPage(pageData, queryWrapper).getRecords();
//    }

//    @ApiOperation(value = "更新会话")
//    @PutMapping("/{conversation_id}")
//    public String updateConversation(@PathVariable("conversation_id") String conversationId, @RequestParam("ai_model_id") String aiModelId) {
//        ChatConversation conversation = conversationMapper.selectById(conversationId);
//        if (conversation == null) {
//            return "会话不存在";
//        }
//
//        conversation.setAiModelId(aiModelId);
//        conversation.setUpdateTime(LocalDateTime.now());
//        conversation.setVersion(conversation.getVersion() + 1);
//
//        conversationMapper.updateById(conversation);
//        return "更新会话成功";
//    }

    @ApiOperation(value = "删除会话")
    @DeleteMapping("/{conversation_id}")
    public R deleteConversation(@PathVariable("conversation_id") String conversationId) {
        conversationMapper.deleteById(conversationId);

        // 删除会话相关的会话详情
        QueryWrapper<ChatConversationDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId);
        conversationDetailMapper.delete(queryWrapper);

        return R.ok("删除会话及其相关会话详情成功");
    }
}
