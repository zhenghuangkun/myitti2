package com.awslabplatform_admin.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageInfo {

    private final static int PAGESIZE = 10; //默认显示的记录数
    private final static int PAGEFROM = 0; //数据的起始位置

    private int draw = 0;			//绘制计数器
	private long recordsTotal; 			// 总记录
    private long  recordsFiltered; 			// 过滤后的记录数
    private List<?> data; 				//显示的记录
    private int from;
    private int size;
    private int nowpage; 					// 当前页
    private int pagesize;					// 每页显示的记录数

	@JSONField(serialize = false)
    private Map<String, Object> condition; 	//查询条件
    private String sort;					// 排序字段
    private String order;					// asc，desc

    public PageInfo() {
    }

    /**
     * dataTable使用
     * @param start
     * @param length
     * @param draw
     */
    public PageInfo(int start, int length, int draw) {

    	/*绘制计数器*/
    	this.draw = draw;

    	//记录数据起始位置
        if (start < 0) {
            this.from = PAGEFROM;
        } else {
            this.from = start;
        }

    	//记录每页显示的记录数
        if (length < 0) {
            this.size = PAGESIZE;
        } else {
            this.size = length;
        }

    }


	/**
	 * dataGrid使用
	 * @param nowpage
	 * @param pagesize
	 */
	public PageInfo(int nowpage, int pagesize) {

        //计算当前页
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            //当前页
            this.nowpage = nowpage;
        }
        //记录每页显示的记录数
        if (pagesize < 0) {
            this.pagesize = PAGESIZE;
        } else {
            this.pagesize = pagesize;
        }
        //计算开始的记录和结束的记录
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
    }

    // 构造方法
    public PageInfo(int nowpage, int pagesize, String sort, String order) {

        // 计算当前页
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            // 当前页
            this.nowpage = nowpage;
        }
        // 记录每页显示的记录数
        if (pagesize < 0) {
            this.pagesize = PAGESIZE;
        } else {
            this.pagesize = pagesize;
        }
        // 计算开始的记录和结束的记录
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
        // 排序字段，正序还是反序
        this.sort = sort;
        this.order = order;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
    public void putCondition(String key,Object value) {
        if(this.condition  == null){
			this.condition = new HashMap<>();
		}
		this.condition.put(key,value);
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
