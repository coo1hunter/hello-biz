package com.biz.std.repository;

import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>,JpaSpecificationExecutor<Subject> {
    Subject findSubjectByName(String name);

    Subject findSubjectById(Long id);
}
