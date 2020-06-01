package com.biz.lession.dao;

import com.biz.lession.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface GradeRepository extends JpaRepository<Grade,Integer> {

    /**
     * 分页查询班级信息
     * @param pageable
     * @return
     */
//    @Query(value = "select * from grade where LastName=?1 order by :#{#pageable} ",
//           countQuery = "select count(*) from grade where LastName=?1",
//          nativeQuery = true)
//    Page<Grade> findAll(String lastname,@Param("pageable") Pageable pageable);


    /**
     * 查询总记录数
     * @param
     * @return
     */
    @Query(value = "select count(*) from grade ",
            nativeQuery = true)
    Integer selectCount();





    /**
     * 修改班级信息
     * @param
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update grade set grade_name=:gradeName where  grade_id=:gradeId",
           nativeQuery = true)
    int updateGrade(@Param("gradeName") String gradeName,@Param("gradeId") Integer gradeId );


    /**
     * 查询班级学生数量
     * @param gradeId
     * @return
     */
    @Query(value = "SELECT count(*) FROM student where grade_id=?1",
            nativeQuery = true)
    Integer findNum(Integer gradeId);


    /**
     * 根据id查询总分
     * @param list
     * @return
     */
    @Query(value = "SELECT SUM(scores) FROM score  where stu_id in (:list)",nativeQuery = true)
    Integer findSumScoresByStuId(@Param("list") List<Integer> list);


    /**
     * 查询班级中的学生的学号
     * @param gradeId
     * @return
     */
    @Query(value = "SELECT s.stu_id FROM student s where s.grade_id = ?1",nativeQuery = true)
    List<Integer> findStuId(Integer gradeId);




    /**
     * 查询班级中的学生的学号
     * @param gradeIds
     * @return
     */
    @Query(value = "SELECT s.stu_id FROM student s where s.grade_id in :gradeIds",nativeQuery = true)
    List<Integer> findStuIds(List<Integer> gradeIds);


    /**
     * 删除班级
     * @param gradeIds
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "delete from grade where grade_id = ?1 ",nativeQuery = true)
    Integer deleteGrade(Integer gradeIds);

}
