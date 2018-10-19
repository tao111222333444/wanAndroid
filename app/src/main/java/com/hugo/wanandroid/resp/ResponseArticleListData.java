package com.hugo.wanandroid.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/18.
 * 版本：v1.0
 * 描述：首页文章列表
 */

public class ResponseArticleListData implements Serializable {

    private int curPage;
    private List<ResponseArticleData> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<ResponseArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<ResponseArticleData> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
