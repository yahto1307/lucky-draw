package com.yahto.hydra.lucky.draw.dao;

import com.yahto.hydra.lucky.draw.model.Item;
import com.yahto.hydra.lucky.draw.model.ItemExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yantong
 */
@Repository
public interface ItemDao {
    long countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    /**
     * 减去奖品库存
     *
     * @param activityId
     * @param id
     * @return
     */
    int reduceItemCount(@Param("activityId") Long activityId, @Param("id") Long id);
}