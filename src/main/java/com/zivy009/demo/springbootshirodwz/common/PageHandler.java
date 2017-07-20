package com.zivy009.demo.springbootshirodwz.common;

import java.util.List;

/**
 *
 * PageHandler
 * 
 */
public class PageHandler implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final int DEFAULT_PAGE_SIZE = 30;
    /**
     * 
     */

    private int totalCount;
    private int pageSize;
    private int pageIndex;
    private List<?> list;

    /**
     * @param pageIndex
     * @param pageSize
     * @param totalCount
     * @param maxResults
     */
    public PageHandler(Integer pageIndex, Integer pageSize, int totalCount) {
        setPageSize(null != pageSize ? pageSize : DEFAULT_PAGE_SIZE);
        setPageIndex(null != pageIndex ? pageIndex : 1);
      
    }

    public PageHandler(Integer pageIndex, Integer pageSize) {

        this(pageIndex, pageSize, 0);

    }

    public PageHandler(Integer pageIndex) {
        this(pageIndex, DEFAULT_PAGE_SIZE, 0);
    }

 

    /**
     * @return
     */
    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        return (0 == totalPage || 0 != totalCount % pageSize) ? ++totalPage : totalPage;
    }

    /**
     * @return
     */
    public int getFirstResult() {
        return (pageIndex - 1) * pageSize;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if(pageIndex > getTotalPage()){
            pageIndex = getTotalPage() +1;
        }
    }

   

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 当前页面的开始行数。获得开始行
     * 
     * @author zivy
     * @date 2017年7月11日
     * @describe
     * @return
     *
     */
    public int getStartRow() {
        return (pageIndex - 1) * pageSize;
    }

    /**
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the list
     */
    public List<?> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<?> list) {
        if (0 == totalCount && null != list) {
            setTotalCount(list.size());
        }
        this.list = list;
    }

    /**
     * @return
     */
    public boolean isFirstPage() {
        return pageIndex <= 1;
    }

    /**
     * @return
     */
    public boolean isLastPage() {
        return pageIndex >= getTotalPage();
    }

    /**
     * @return
     */
    public int getNextPage() {
        if (isLastPage()) {
            return pageIndex;
        }
        return pageIndex + 1;
    }

    /**
     * @return
     */
    public int getPrePage() {
        if (isFirstPage()) {
            return pageIndex;
        }
        return pageIndex - 1;
    }

}
