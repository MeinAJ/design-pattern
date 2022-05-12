package com.geega.cloud.common.base.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
@Data
@Validated
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页：每页数据量.默认10
     */
    @Range(min = 1, max = 1000, message = "仅支持每页1-1000条数据")
    private int pageSize = 10;

    /**
     * 分页：页号,默认1
     */
    @Range(min = 1, message = "页号必须大于零")
    private int pageNo = 1;

    /**
     * 分页： 页号，兼容支持page参数
     */
    public void setPage(int page) {
        this.pageNo = page;
    }

    /**
     * 分页： 页号，兼容支持page参数
     */
    public int getPage() {
        return this.pageNo;
    }

    /**
     * 分页：页号,默认0,这个字段是为了解决数据库从0开始查询的问题。
     */
    public int getDbPageNo() {
        return this.pageNo - 1;
    }

}
