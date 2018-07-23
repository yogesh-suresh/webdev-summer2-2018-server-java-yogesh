//Ref - Jose webdev summer -1 github

package com.example.webdevsummer22018serverjavayogesh.services;

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

import com.example.webdevsummer22018serverjavayogesh.models.Course;

import com.example.webdevsummer22018serverjavayogesh.repositories.CourseRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course")
	public void deleteCourse(@RequestBody int courseId) {
		courseRepository.deleteById(courseId);
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		Optional<Course> data = courseRepository.findById(id);
		if(data.isPresent()) {
			Course course = data.get();
			return course;
		}
		return null;
	}
	
	@PutMapping("/api/course/{cId}")
	public Course updateCourse(@PathVariable("cId") int id,
			@RequestBody Course newCourse) {
		Optional<Course> optional = courseRepository.findById(id);
		
		if(optional.isPresent()) {
			Course course = optional.get();
			course.setTitle(newCourse.getTitle());
			return courseRepository.save(course);
		}
		return null;
	}
	
}