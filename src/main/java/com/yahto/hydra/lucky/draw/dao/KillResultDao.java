package com.yahto.hydra.lucky.draw.dao;

import com.yahto.hydra.lucky.draw.model.KillResult;
import com.yahto.hydra.lucky.draw.model.KillResultExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yantong
 */
@Repository
public interface KillResultDao {
    long countByExample(KillResultExample example);

    int deleteByExample(KillResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KillResult record);

    int insertSelective(KillResult record);

    List<KillResult> selectByExample(KillResultExample example);

    KillResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KillResult record, @Param("example") KillResultExample example);

    int updateByExample(@Param("record") KillResult record, @Param("example") KillResultExample example);

    int updateByPrimaryKeySelective(KillResult record);

    int updateByPrimaryKey(KillResult record);
}