package com.yahto.hydra.lucky.draw.prize;

/**
 * Created by yahto on 2018/9/13 9:48 PM
 *
 * @author yahto
 */
public class PrizePoolBean {
    private Long id;
    private Integer begin;
    private Integer end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
