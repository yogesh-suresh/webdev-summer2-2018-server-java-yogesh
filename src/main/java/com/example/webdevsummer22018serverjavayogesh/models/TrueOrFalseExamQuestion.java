package com.example.webdevsummer22018serverjavayogesh.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends Question {
	private boolean answer;

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}