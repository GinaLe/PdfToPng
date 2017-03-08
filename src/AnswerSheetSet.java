import java.util.HashMap;
public class AnswerSheetSet {
	AnswerSheet key;
	AnswerSheet[] set;
	
	


public AnswerSheetSet(AnswerSheet key, AnswerSheet[] set) {
		super();
		this.key = key;
		this.set = set;
	}




/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	HashMap<Integer, Integer> answers;

	public AnswerSheet() {
		answers = new HashMap<Integer, Integer>();
	}
	
	public void fillAnswer(int index, int val){
		answers.put(index, val);
	}

}
}