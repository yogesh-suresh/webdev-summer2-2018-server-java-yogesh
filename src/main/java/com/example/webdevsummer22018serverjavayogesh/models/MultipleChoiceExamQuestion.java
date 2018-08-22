package com.example.webdevsummer22018serverjavayogesh.models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends Question {
	private String options;
	private int correctOption;
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
}