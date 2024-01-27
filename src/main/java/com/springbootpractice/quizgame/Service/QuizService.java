package com.springbootpractice.quizgame.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootpractice.quizgame.DAO.QuestionDAO;
import com.springbootpractice.quizgame.DAO.QuizDao;
import com.springbootpractice.quizgame.model.Question;
import com.springbootpractice.quizgame.model.QuestionWrapper;
import com.springbootpractice.quizgame.model.Quiz;
import com.springbootpractice.quizgame.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDAO questionDao; 
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Question> questions = questionDao.findRandomQuestionsByCustomer(category,numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Question> questionsFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList<>();
		for(Question q : questionsFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUsers.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
	}
	public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right=0;
		int i=0;
//		for(Question q : question) {
//			if(q.getCorrectAnswer().equals(response.getResponse().get(i)))
//				right++;
//			i++;
//		}
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
				right++;
				
			i++; 	
		}
		
	}
		return new ResponseEntity(right,HttpStatus.OK);

	}
}
