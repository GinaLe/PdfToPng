import java.util.ArrayList;
import java.util.HashMap;

public class AnswerSheet {
	ArrayList<Integer> answers;
	int numQuestions;
	int numCorrect, numIncorrect;
	double percentageCorrect, percentageIncorrect;

	public AnswerSheet(int numQuestions) {
		answers = new ArrayList<Integer>();
		this.numQuestions= numQuestions;
		
	}
	
	public int getNumQuestions() {
		return numQuestions;
	}

	
	public void fillAnswer(int index, int val){
		answers.add(index, val);
	}
	public int getAnswer(int index){
		return answers.get(index);
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public void setNumCorrect(int numCorrect) {
		this.numCorrect = numCorrect;
	}

	public int getNumIncorrect() {
		return numIncorrect;
	}

	public void setNumIncorrect(int numIncorrect) {
		this.numIncorrect = numIncorrect;
	}

	public double getPercentageCorrect() {
		return percentageCorrect;
	}

	public void setPercentageCorrect(double percentageCorrect) {
		this.percentageCorrect = percentageCorrect;
	}

	public double getPercentageIncorrect() {
		return percentageIncorrect;
	}

	public void setPercentageIncorrect(double percentageIncorrect) {
		this.percentageIncorrect = percentageIncorrect;
	}
	

}
