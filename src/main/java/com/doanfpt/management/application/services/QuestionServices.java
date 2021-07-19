package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.AnswerForm;
import com.doanfpt.management.application.dto.QuestionForm;
import com.doanfpt.management.application.entities.Answer;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.responsitories.ChapterResponsitory;
import com.doanfpt.management.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;

    @Autowired
    ChapterResponsitory chapterResponsitory;

    @Transactional
    public void createNewQuestion(QuestionForm form) {
        Chapter chapter = new Chapter();
        chapter = chapterResponsitory.getOne(form.getChapterId());
        Question question = new Question();
        question.setContent(form.getContent());
        question.setNumber(form.getNumber());
        question.setChapter(chapter);
        List<Answer> listAnswers = new ArrayList<>();
        for (AnswerForm answer : form.getListAnswers()) {
            Answer ans = new Answer();
            ans.setContent(answer.getContent());
            ans.setTrue(answer.isTrue());
            ans.setQuestion(question);
            ans.setCreateBy(Common.getUsernameLogin());
            ans.setCreateAt(Common.getSystemDate());
            ans.setUpdateBy(Common.getUsernameLogin());
            ans.setUpdateAt(Common.getSystemDate());
            listAnswers.add(ans);
        }
        question.setListAnswers(listAnswers);
        question.setCreateBy(Common.getUsernameLogin());
        question.setCreateAt(Common.getSystemDate());
        question.setUpdateBy(Common.getUsernameLogin());
        question.setUpdateAt(Common.getSystemDate());
        questionsRespository.save(question);
    }

    public Page<Question> getAllQuestions(Integer pageNumber) {
    	 if (pageNumber == null) {
             pageNumber = 0;
         }
    	Pageable pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
        return questionsRespository.findAll(pageable);
        
    }

    public Page<Question> getQuestionInChapter(Long chapterId, Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        Pageable pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterId, false);
        return questionsRespository.findByChapter(chapter, pageable);
    }
}
