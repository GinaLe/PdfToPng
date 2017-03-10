import java.util.ArrayList;
import java.util.HashMap;
public class AnswerSheetSet {
	AnswerSheet key;
	ArrayList<AnswerSheet >set;
	ArrayList<Integer> QNumIncorrect;
	ArrayList<Double> QPercentageIncorrect;
	
	
	


public AnswerSheetSet(AnswerSheet key) {
		super();
		this.key = key;
		this.set = new ArrayList<AnswerSheet>();
		QPercentageIncorrect=new ArrayList<Double>();
		QNumIncorrect= new ArrayList<Integer>();
		System.out.println(key.getNumQuestions());
		for(int i=0;i<key.getNumQuestions();i++){
			QNumIncorrect.add( 0);
			QPercentageIncorrect.add(0.0);
		}
		
	}




public AnswerSheet getKey() {
	return key;
}

public void setKey(AnswerSheet key) {
	this.key = key;
}

public AnswerSheet getSheet(int index){
	return set.get(index);
}

public void addSheet(AnswerSheet sheet){
	set.add( sheet);
}
public void calcResults(){
	key.setNumCorrect(key.getNumQuestions());
	key.setNumIncorrect(0);
	for(int i=0;i<set.size();i++){
		int numCorrect=0;
		for(int j=0;j<key.getNumQuestions();j++){
			if(key.getAnswer(j)==set.get(i).getAnswer(j)){
				numCorrect++;
			}else{
				
				QNumIncorrect.set(j, QNumIncorrect.get(j)+1);
			}
		}
		set.get(i).setNumCorrect(numCorrect);
		set.get(i).setNumIncorrect(key.getNumQuestions()-numCorrect);
	}
	
	for(int i=0;i<key.getNumQuestions();i++){
		QPercentageIncorrect.set(i, ((double)QNumIncorrect.get(i)/set.size()));
	}
	for(int i=0;i<set.size();i++){
		set.get(i).setPercentageCorrect(100*(double)set.get(i).getNumCorrect()/key.getNumQuestions());
		set.get(i).setPercentageIncorrect(100*(double)set.get(i).getNumIncorrect()/key.getNumQuestions());
	}
}
public String getData1(){
	StringBuilder data= new StringBuilder("");
	data.append(key.getNumCorrect());
	data.append(", ");
	data.append(key.getNumIncorrect());
	data.append(", ");
	data.append(key.getPercentageCorrect());
	data.append(", ");
	data.append(key.getPercentageIncorrect());
	data.append("\n");
	for(int i=0;i<set.size();i++){
		System.out.println(set.get(i).getNumCorrect());
		data.append(set.get(i).getNumCorrect()+", ");
		data.append(set.get(i).getNumIncorrect()+", ");
		data.append(set.get(i).getPercentageCorrect()+", ");
		data.append(set.get(i).getPercentageIncorrect()+", ");
		data.append("\n");
	}
	return data.toString();
}

public String getData2() {
	StringBuilder data= new StringBuilder("");
	for(int i = 0; i < key.getNumQuestions(); i++) {
		data.append(QNumIncorrect.get(i) + ", ");
		data.append(QPercentageIncorrect.get(i) + ", ");
		data.append("\n");
	}
	
	return data.toString();
}

/***
 * A class to represent a set of answers from a page
 */

}