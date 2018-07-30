package com.example.webdevsummer22018serverjavayogesh.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavayogesh.models.Topic;
import com.example.webdevsummer22018serverjavayogesh.models.Widget;



public interface WidgetRepository extends CrudRepository<Widget,Integer>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Widget w WHERE w.topic.id=:topicId")
	void deleteWidgetsByLessonId(
		@Param("topicId") int topicId);
	
	@Query("SELECT w from Widget w WHERE w.topic=:topic ORDER BY w.id")
	List<Widget> findAllWidgetsByTopicSorted(@Param("topic") Topic topic);
	

}