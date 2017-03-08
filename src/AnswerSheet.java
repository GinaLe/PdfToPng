import java.util.ArrayList;
import java.util.HashMap;

public class AnswerSheet {
	ArrayList<Integer> answers;

	public AnswerSheet() {
		answers = new ArrayList<Integer>();
	}
	
	public void fillAnswer(int index, int val){
		answers.add(index, val);
	}
	public int getAnswer(int index){
		return answers.get(index);
	}

}
