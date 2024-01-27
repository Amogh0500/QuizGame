package com.springbootpractice.quizgame.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpractice.quizgame.Service.QuestionService;
import com.springbootpractice.quizgame.model.Question;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	@GetMapping("category/{c}")
	public ResponseEntity<List<Question>> getByCategory(@PathVariable String c) {
		return questionService.getByCategory(c);
	}
	@PostMapping("add") 
	public ResponseEntity<String> addQuestion(@RequestBody Question q) {
		return questionService.addQuestion(q);
	}
	@DeleteMapping("delete/{id}")
	public void deleteQuestion(@PathVariable int id) {
		questionService.deleteQuestion(id);
	}
}
