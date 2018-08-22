package com.example.webdevsummer22018serverjavayogesh.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.webdevsummer22018serverjavayogesh.models.TrueOrFalseExamQuestion;


public interface TrueOrFalseQuestionRepository
	extends CrudRepository<TrueOrFalseExamQuestion, Integer> {
	
}