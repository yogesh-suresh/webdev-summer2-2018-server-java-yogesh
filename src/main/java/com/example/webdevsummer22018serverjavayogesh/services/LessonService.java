package com.example.webdevsummer22018serverjavayogesh.services;

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
import com.example.webdevsummer22018serverjavayogesh.models.Module;
import com.example.webdevsummer22018serverjavayogesh.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavayogesh.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavayogesh.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/course/{cId}/module/{mId}/lesson")
	public Lesson createLesson( @PathVariable("cId") int courseId, 
								@PathVariable("mId") int moduleId, 
								@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		lessonRepository.deleteById(id);
	}
	//optional
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}
	//optional
	@GetMapping("/api/lesson/{id}")
	public Optional<Lesson> findLessonById(@PathVariable("id") int id) {
		return lessonRepository.findById(id);
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		throw new IllegalArgumentException("Invalid module");
		
	}
}