package com.yu.chatliteserver.controller;

import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.request.UserRequest;
import com.yu.chatliteserver.service.IUserService;
import com.yu.chatliteserver.vo.R;
import com.yu.chatliteserver.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-16 01:26:07
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService iUserService;

    // 用户列表
    @GetMapping("/list")
    public R userList() {
        List<User> userList = iUserService.list();
        List<UserVO> list = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return R.ok(list);
    }

    // 新增用户
    @PostMapping("")
    public UserVO addUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        boolean success = iUserService.saveUser(user);
        if (success) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        } else {
            throw new RuntimeException("新增用户失败");
        }
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        boolean success = iUserService.deleteUser(id);
        if (!success) {
            throw new RuntimeException("删除用户失败");
        }
    }

    // 修改用户
    @PutMapping("/{id}/{version}")
    public UserVO updateUser(@PathVariable (value="id") String id,@PathVariable(value = "version") Integer version, @RequestBody UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setId(id);
        boolean success = iUserService.updateUser(user,version);
        if (success) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        } else {
            throw new RuntimeException("修改用户失败");
        }
    }

    // 注销用户
    @PostMapping("logout/{id}")
    public R logoutUser(@PathVariable String id) {
        // 根据实际需求实现
        return R.ok();
    }

    // 冻结用户
    @PostMapping("freeze/{id}")
    public R freezeUser(@PathVariable String id) {
        // 根据实际需求实现
        return R.ok();
    }

}
