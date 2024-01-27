package com.springbootpractice.quizgame.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootpractice.quizgame.DAO.QuestionDAO;
import com.springbootpractice.quizgame.model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDAO questionDao;
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST); 
		
	}
	public ResponseEntity<List<Question>> getByCategory(String c) {
//		List<Question> myCategory = new ArrayList<>();
		// TODO Auto-generated method stub
//		for(Question question : questionDao.findAll()) {
//			if(question.getCategory()==c)
//				myCategory.add(question);
//		}
		try {
			return new ResponseEntity<>(questionDao.findByCategory(c),HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}
	public ResponseEntity<String> addQuestion(Question q) {
		// TODO Auto-generated method stub
			questionDao.save(q);
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	public void deleteQuestion(int id) {
		// TODO Auto-generated method stub
		for(Question question : questionDao.findAll()) {
			if(question.getId()==id) {
				questionDao.delete(question);
			}
		}
	}
}
