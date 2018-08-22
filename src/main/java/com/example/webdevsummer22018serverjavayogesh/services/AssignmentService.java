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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.webdevsummer22018serverjavayogesh.models.Assignment;
import com.example.webdevsummer22018serverjavayogesh.models.Course;
import com.example.webdevsummer22018serverjavayogesh.models.ExamWidget;
import com.example.webdevsummer22018serverjavayogesh.models.Lesson;
import com.example.webdevsummer22018serverjavayogesh.models.Widget;
import com.example.webdevsummer22018serverjavayogesh.repositories.AssignmentRepository;
import com.example.webdevsummer22018serverjavayogesh.repositories.LessonRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}

	@GetMapping("/api/assignment/{id}")
	public Assignment findAssignmentById(@PathVariable("id") int aid) {
		Optional<Assignment> data = assignmentRepository.findById(aid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@GetMapping("/api/lesson/{lid}/assignment")
	public List<ExamWidget> findAllAssignmentsForLesson(
			@PathVariable("lid") int lessonId) {
		List<ExamWidget> list = new ArrayList<ExamWidget>();
		List<ExamWidget> wlist = new ArrayList<ExamWidget>();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			wlist.addAll(lesson.getExamWidget()) ;
		}
			for(ExamWidget w: wlist) {
				if(w.getWidgetType().equals("Assignment"))
				{
					
                     list.add(w);
				}
				
			}
			
			return list;
					
	}

	@DeleteMapping("/api/assignment/{id}")
	public void deleteAssignment(@PathVariable("id") int aid)
	{
		assignmentRepository.deleteById(aid);
	}

	@PostMapping("/api/lesson/{lid}/assignment")
	public Assignment createAssignment(
			@PathVariable("lid") int lessonId,
			@RequestBody Assignment newAssignment) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if(data.isPresent()) {
			Lesson lesson = data.get();
			newAssignment.setLesson(lesson);
			return assignmentRepository.save(newAssignment);
		}
		return null;		
	}

}