package cn.gg3083.tb.domain;

import cn.gg3083.tb.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_role")
@Data
public class UserRole extends BaseEntity {
    /**
     * 用户角色表编号
     */
    @Id
    @Column(name = "pk_user_role_id")
    private Integer pkUserRoleId;

    /**
     * 角色id（关联角色表pk_role_id字段）
     */
    @Column(name = "fk_role_id")
    private Integer fkRoleId;

    /**
     * 用户id（关联用户信息表pk_user_id字段）
     */
    @Column(name = "fk_user_id")
    private Integer fkUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间(自动更新)
     */
    @Column(name = "update_time")
    private Date updateTime;

}