package com.biz.lession.dao;

import com.biz.lession.entity.Score;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import com.biz.lession.vo.StudentVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {

    /**
     * 分页查询学生数据
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM student",
            countQuery = "SELECT count(*) FROM student",
            nativeQuery = true)
    Page<Student> findAll(Pageable pageable);


    /**
     * 查询录入分数的学科id
     * @param stuId
     * @return
     */
    @Query(value = "SELECT subject_id FROM score where stu_id=:stuId and scores is null",
            nativeQuery = true)
    List<Integer> findSubjectId(@Param("stuId") Integer stuId);

    /**
     * 查询已被选择的学科id
     * @param stuId
     * @return
     */
    @Query(value = "SELECT subject_id FROM score where stu_id=:stuId",
            nativeQuery = true)
    List<Integer> findSubjectIds(@Param("stuId") Integer stuId);

//    @Query(value = "select * from student where stu_num like ?1%",
//            nativeQuery = true)
//    List<Student> findAllBySearch(String search1);

//    @Query(value = "SELECT t FROM student t where stu_num like ?1% and stu_birth like %?2% and stu_name like %?3%")
//    List<Student> findAllBySearch1(Integer search1,String search2,String search3);
    @Query(value = "select s from Student s where s.stuNum like  CONCAT(?1,'%')")
    List<Student> findAllBySearch(Integer stuName);

//    /**
//     * 查询学生还未选择的学科id
//     * @param stuId
//     * @return
//     */
//    @Query(value = "SELECT subject_id FROM subject where subject=:stuId and scores is null",
//            nativeQuery = true)
//    List<Integer> findSubjectId(@Param("stuId") Integer stuId);
////    /**
//     * 修改学生数据
//     * @param
//     * @return
//     */
//
//    @Transactional
//    @Modifying
//    @Query(value = "update student set stu_name=:#{#students.stuName},stu_num=:#{#students.stuNum},stu_sex=:#{#students.stuSex} where stu_id=:#{#students.stuId}",
//            nativeQuery = true)
//    int updateStudet(@Param("students") Student students);





//    Page<Student> findByUsernameContainingOrderByCreateTimeDesc(String username,Pageable pageable);

}
