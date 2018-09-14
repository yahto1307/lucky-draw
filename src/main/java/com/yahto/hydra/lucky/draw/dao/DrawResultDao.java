package com.yahto.hydra.lucky.draw.dao;

import com.yahto.hydra.lucky.draw.model.DrawResult;
import com.yahto.hydra.lucky.draw.model.DrawResultExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author yantong
 */
@Repository
public interface DrawResultDao {
    long countByExample(DrawResultExample example);

    int deleteByExample(DrawResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DrawResult record);

    int insertSelective(DrawResult record);

    List<DrawResult> selectByExample(DrawResultExample example);

    DrawResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DrawResult record, @Param("example") DrawResultExample example);

    int updateByExample(@Param("record") DrawResult record, @Param("example") DrawResultExample example);

    int updateByPrimaryKeySelective(DrawResult record);

    int updateByPrimaryKey(DrawResult record);
}