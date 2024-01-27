package com.springbootpractice.quizgame.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootpractice.quizgame.model.Question;

public interface QuestionDAO extends JpaRepository<Question,Integer>{
	List<Question> findByCategory(String category);
	@Query(value="Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery=true)
	List<Question> findRandomQuestionsByCustomer(String category,int numQ);
}
