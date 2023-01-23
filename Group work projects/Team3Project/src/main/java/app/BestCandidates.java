package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dao;

/**
 * @author Miska
 */

@WebServlet("/bestcandidates")
public class BestCandidates extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        dao dao = new dao();
        ArrayList <Question> questions = dao.readAllQuestions();
        ArrayList <Candidate> candidates = dao.readAllCandidates();
        ArrayList <Integer> usersanswers = new ArrayList<>();
        String answer = "";
        int answerInt = 0;

        for (int i = 0; i < questions.size(); i++) {
            answer = request.getParameter("" + (i + 1));
            if (answer != null) {
                answerInt = Integer.valueOf(answer);
                usersanswers.add(answerInt);
            } else {
                answerInt = 3;
                usersanswers.add(answerInt);
            }
        }

        int difference = 0;
        int differenceSum = 0;
        CandidatesAnswers candidatesAnswers = new CandidatesAnswers();
        ArrayList <CandidatesAnswers> candidateAnswersList = new ArrayList<>();

        for (int i = 0; i < candidates.size(); i++) {
            differenceSum = 0;
            Candidate c = candidates.get(i);
            candidateAnswersList = dao.readCandidatesAnswers(c.getEhdokas_id());
            
            if (candidateAnswersList.size() != 0) {
            	for (int ii = 0; ii < questions.size(); ii++) {
            		try {
            		candidatesAnswers = candidateAnswersList.get(ii);
                	difference = usersanswers.get(ii) - candidatesAnswers.getVastaus();
                	differenceSum = differenceSum + Math.abs(difference);
            		} catch (IndexOutOfBoundsException e) {
            			continue;
            		}
            	}            
            	candidates.get(i).setPisteet(differenceSum);
            } else {
            	candidates.get(i).setPisteet(100);
            }
        }

        Collections.sort(candidates);
        candidates.subList(3, candidates.size()).clear();
        request.setAttribute("candidates", candidates);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/bestcandidates.jsp");
        rd.forward(request, response);

    }

}