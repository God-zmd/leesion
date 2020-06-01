package com.biz.lession.dao;

import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Subject;
import com.biz.lession.vo.SubjectVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject,Integer> {

//    /**
//     * 分页查询学科信息
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select * from subject ",
//           countQuery = "select count (*) from subject",
//          nativeQuery = true)
//    Page<Subject> findAll(Pageable pageable);




    /**
     * 查询学科学生数量
     * @param subjectId
     * @return
     */
    @Query(value = "SELECT count(*) FROM score where subject_id=?1",
            nativeQuery = true)
    Integer findNum(Integer subjectId);


    /**
     * 根据id查询总分
     * @param sbujectId
     * @return
     */
    @Query(value = "SELECT SUM(scores) FROM score  where subject_id = :sbujectId",nativeQuery = true)
    Integer findSumScores(@Param("sbujectId") Integer sbujectId);


    /**
     * 修改学科信息
     * @param
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update subject set subject_name=:subjectName where  subject_id=:subjectId",
            nativeQuery = true)
    int updateSubject(@Param("subjectName") String gradeName,@Param("subjectId") Integer subjectId );


    /**
     * 查询是否有学生引用subject
     * @param sbujectId
     * @return
     */
    @Query(value = "SELECT count(*) FROM score  where subject_id =?1",nativeQuery = true)
    Integer findStuIdScore(Integer sbujectId);
//    /**
//     * 根据stuid查询学科信息
//     * @param
//     * @return
//     */
//    @Query(value = "SELECT s.subject_id,s.subject_name,sc.stu_id FROM subject s left join score sc on s.subject_id=sc.subject_id",nativeQuery = true)
//    Object[] findSubjectBy();


    /**
     * 查询学科
     * @param sbujectIds
     * @return
     */
    @Query(value = "SELECT * FROM subject  where subject_id in :sbujectIds",nativeQuery = true)
    List<Subject> findSubjectBy(@Param("sbujectIds") List<Integer> sbujectIds);


    /**
     * 查询还未被选择的学科
     * @param sbujectIds
     * @return
     */
    @Query(value = "SELECT * FROM subject  where subject_id not in :sbujectIds",nativeQuery = true)
    List<Subject> findSubjectBy2(@Param("sbujectIds") List<Integer> sbujectIds);




}
