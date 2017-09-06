package com.biz.std.repository;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository  extends JpaRepository<Score,Long>,JpaSpecificationExecutor<Score> {

    Score findScoreByStudentAndSubject(Student student, Subject subject);

}
