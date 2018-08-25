package cn.gg3083.tb.common.domain;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete = 1;
}
