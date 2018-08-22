package com.example.webdevsummer22018serverjavayogesh.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer22018serverjavayogesh.models.MultipleChoiceExamQuestion;


public interface MultipleChoicesQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer> {
	
}