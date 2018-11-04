package com.yahto.hydra.lucky.draw.dao;

import com.yahto.hydra.lucky.draw.model.KillItem;
import com.yahto.hydra.lucky.draw.model.KillItemExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yantong
 */
@Repository
public interface KillItemDao {
    long countByExample(KillItemExample example);

    int deleteByExample(KillItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KillItem record);

    int insertSelective(KillItem record);

    List<KillItem> selectByExample(KillItemExample example);

    KillItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KillItem record, @Param("example") KillItemExample example);

    int updateByExample(@Param("record") KillItem record, @Param("example") KillItemExample example);

    int updateByPrimaryKeySelective(KillItem record);

    int updateByPrimaryKey(KillItem record);

    int prizeKill(@Param("killItemId") Long killItemId);
}