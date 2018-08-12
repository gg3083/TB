package com.gg3083.tb.domain.role;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "role")
@Data
public class Role {
    /**
     * 角色编号
     */
    @Id
    @Column(name = "pk_role_id")
    private Integer pkRoleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否已经删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}