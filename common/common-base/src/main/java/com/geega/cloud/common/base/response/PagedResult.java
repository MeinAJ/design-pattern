package com.geega.cloud.common.base.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResult<T> implements Serializable {

    /**
     * 条数
     */
    private int pageSize;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 总记录数
     */
    private long totalRows;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 记录
     */
    private List<T> records;

    /**
     * 空分页对象
     *
     * @param <T> 分页对象类型
     * @return PagedResult<T>
     */
    public static <T> PagedResult<T> emptyData() {
        PagedResult<T> pagedResult = new PagedResult<>();
        pagedResult.setRecords(Collections.emptyList());
        pagedResult.setCurrentPage(1);
        pagedResult.setPageSize(10);
        pagedResult.setTotalPages(0);
        pagedResult.setTotalRows(0);
        return pagedResult;
    }

    /**
     * 改变page对象record类型
     *
     * @param newRecord 目标记录集
     * @param <T>       目标记录类型
     * @return
     */
    public <T> PagedResult<T> convertRecord(List<T> newRecord) {
        PagedResult<T> pagedResult = new PagedResult<>();
        BeanUtils.copyProperties(this, pagedResult);
        pagedResult.setRecords(newRecord);
        return pagedResult;
    }

}
