package com.example.webdevsummer22018serverjavayogesh.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavayogesh.models.Lesson;
import com.example.webdevsummer22018serverjavayogesh.models.MultipleChoiceExamQuestion;
import com.example.webdevsummer22018serverjavayogesh.models.TrueOrFalseExamQuestion;
import com.example.webdevsummer22018serverjavayogesh.models.EssayExamQuestion;
import com.example.webdevsummer22018serverjavayogesh.models.Exam;
import com.example.webdevsummer22018serverjavayogesh.models.ExamWidget;
import com.example.webdevsummer22018serverjavayogesh.models.FillInTheBlanksExamQuestion;
import com.example.webdevsummer22018serverjavayogesh.repositories.LessonRepository;


import com.example.webdevsummer22018serverjavayogesh.repositories.ExamRepository;
import com.example.webdevsummer22018serverjavayogesh.repositories.ExamWidgetRepository;
import com.example.webdevsummer22018serverjavayogesh.repositories.*;

@RestController
@CrossOrigin(origins = "*")
public class ExamWidgetService {
	@Autowired
	ExamWidgetRepository repository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;
	@Autowired
	EssayExamQuestionRepository essayExamQuestionRepository;
	@Autowired
	FillInTheBlanksExamQuestionRepository fillIntheBlanksExamQuestionRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<ExamWidget> findAllExamsForLesson(
			@PathVariable("lessonId") int lessonId) {
		List<ExamWidget> list = new ArrayList<ExamWidget>();
		List<ExamWidget> wlist = new ArrayList<ExamWidget>();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			wlist.addAll(lesson.getExamWidget()) ;
		}
			for(ExamWidget ew: wlist) {
				if(ew.getWidgetType().equals("Exam"))
				{
					list.add(ew);
				}
				
			}
			
			return list;
			
		
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;		
	}
	
	
	@DeleteMapping("/api/exam/{eid}")
	public void deleteExam(@PathVariable("eid") int eid)
	{
		examRepository.deleteById(eid);
	}
	
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{eid}")
	public Exam findExamById(@PathVariable("eid") int eid) {
		Optional<Exam> data = examRepository.findById(eid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseExamQuestion newTrueOrFalseExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueOrFalseExamQuestion.setExam(exam);
			return trueFalseRepository.save(newTrueOrFalseExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{examId}/multi")
	public MultipleChoiceExamQuestion createMultipleChoiceExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceExamQuestion newMultipleChoiceExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultipleChoiceExamQuestion.setExam(exam);
			return mutiRepo.save(newMultipleChoiceExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssayExamQuestion.setExam(exam);
			return essayExamQuestionRepository.save(newEssayExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newFillInTheBlanksExamQuestion.setExam(exam);
			return fillIntheBlanksExamQuestionRepository.save(newFillInTheBlanksExamQuestion);
		}
		return null;		
	}
}