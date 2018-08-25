package cn.gg3083.tb.domain;

import cn.gg3083.tb.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "menu")
@Data
public class Menu extends BaseEntity {
    /**
     * 权限表编号
     */
    @Id
    @Column(name = "pk_menu_id")
    private Integer pkMenuId;

    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 栏目的url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 栏目名称
     */
    @Column(name = "menu_name")
    private String menuName;

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