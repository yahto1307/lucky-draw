package com.yahto.hydra.lucky.draw.model;

import java.util.Date;

/**
 * @author yantong
 */
public class KillResult {
    private Long id;

    private Long userId;

    private Long activityId;

    private Long killItemId;

    private Integer isLucky;

    private Date createAt;

    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getKillItemId() {
        return killItemId;
    }

    public void setKillItemId(Long killItemId) {
        this.killItemId = killItemId;
    }

    public Integer getIsLucky() {
        return isLucky;
    }

    public void setIsLucky(Integer isLucky) {
        this.isLucky = isLucky;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "KillResult{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", killItemId=" + killItemId +
                ", isLucky=" + isLucky +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}