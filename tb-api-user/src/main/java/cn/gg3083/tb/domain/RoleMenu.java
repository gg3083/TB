package cn.gg3083.tb.domain;

import cn.gg3083.tb.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "role_menu")
@Data
public class RoleMenu extends BaseEntity {
    /**
     * 角色权限表编号
     */
    @Id
    @Column(name = "pk_role_perm_id")
    private Integer pkRolePermId;

    /**
     * 角色表id（关联角色表pk_role_id字段）
     */
    @Column(name = "fk_role_id")
    private Integer fkRoleId;

    /**
     * 菜单权限
     */
    @Column(name = "menu_rights")
    private String menuRights;

    /**
     * 增加的权限
     */
    @Column(name = "add_rights")
    private String addRights;

    /**
     * 删除的权限
     */
    @Column(name = "del_righs")
    private String delRighs;

    /**
     * 查看的权限
     */
    @Column(name = "select_rights")
    private String selectRights;

    /**
     * 修改的权限
     */
    @Column(name = "update_rights")
    private String updateRights;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}