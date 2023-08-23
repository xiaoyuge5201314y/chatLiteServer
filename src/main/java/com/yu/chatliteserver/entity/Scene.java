package com.yu.chatliteserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴东宇
 * @since 2023-06-23 23:17:03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_scene")
@ApiModel(value = "Scene对象", description = "")
public class Scene extends Model<Scene> {

    @TableId("id")
    private String id;

    @TableField("scene_name")
    private String sceneName;

    @TableField("scene_prompt")
    private String scenePrompt;

    @TableField("scene_description")
    private String sceneDescription;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
