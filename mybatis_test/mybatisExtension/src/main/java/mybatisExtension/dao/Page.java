package mybatisExtension.dao;

import mybatisExtension.bean.Empinfo;

import java.util.List;

public class Page {
    private Integer start;
    private Integer end;
    private Integer count;
    private List<Empinfo> emps;

    public Page(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Empinfo> getEmps() {
        return emps;
    }

    public void setEmps(List<Empinfo> emps) {
        this.emps = emps;
    }
}
