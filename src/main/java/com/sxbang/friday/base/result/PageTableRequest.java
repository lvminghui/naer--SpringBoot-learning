package com.sxbang.friday.base.result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 分页查询参数
 */
@Slf4j
@Data
public class PageTableRequest implements Serializable {

    private static final long serialVersionUID = 7328071045193618467L;
    private Integer page;
    private Integer limit;
    private Integer offset;

    public void countOffset() {
		if (null == this.page || null == this.limit) {
            this.offset = 0;
            return;
        }
		this.offset = (this.page - 1) * this.limit;
    }

    @Override
    public String toString() {
        return "PageTableRequest{" +
                "page=" + page +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
