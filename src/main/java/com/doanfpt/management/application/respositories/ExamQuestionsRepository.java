package com.doanfpt.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.ExamQuestions;

@Repository
public interface ExamQuestionsRepository extends JpaRepository<ExamQuestions, Long>,
        PagingAndSortingRepository<ExamQuestions, Long>, JpaSpecificationExecutor<ExamQuestions> {
    public ExamQuestions findByExamQuestionsIdAndIsDelete(Long examQuestionId, boolean isDelete);

    public List<ExamQuestions> findByDrivingLicenseAndIsDelete(DrivingLicense drivingLicense, boolean isDelete);
}
