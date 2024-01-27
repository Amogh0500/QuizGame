package com.springbootpractice.quizgame.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootpractice.quizgame.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
}
