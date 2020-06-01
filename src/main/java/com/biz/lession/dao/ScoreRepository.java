package com.biz.lession.dao;

import com.biz.lession.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ScoreRepository extends JpaRepository<Score,Integer> {


    /**
     * 根据id查询平均分
     * @param stuId
     * @return
     */
    @Query(value = "SELECT AVG(s.scores) FROM score s where s.stu_id=?1",nativeQuery = true)
    Integer findAvgScoresByStuId(Integer stuId);

    /**
     * 录入成绩
     * @param subjectId
     * @param stuId
     * @param scores
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update score set scores =?3 where subject_id=?1 and stu_id=?2",nativeQuery = true)
    Integer updateScoreadd(Integer subjectId,Integer stuId,Integer scores);

//    /**
//     * 选课
//     * @param subjectId
//     * @param stuId
//     * @param scores
//     * @return
//     */
//
//    @Query(value = "update score set scores =?3 where subject_id=?1 and stu_id=?2",nativeQuery = true)
//    Integer updateScoreadd(Integer subjectId,Integer stuId,Integer scores);
//
}
