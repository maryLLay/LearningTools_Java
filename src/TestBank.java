//package LearningTools_Java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestBank {
	
	public List<String[]> bank = new ArrayList<String[]>();
	public List<String> bank2 = new ArrayList<String>();

	// Constructor
	public TestBank(String path) throws IOException {
		String line = "";
		
		int linesAdded = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			while ((line = br.readLine()) != null) {
				//bank.add(line.split(","));
				bank.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
				linesAdded++;
			}
			System.out.println("lines = " + linesAdded);
			
		}
		catch (FileNotFoundException e) {
			System.err.println("Problem opening file");
		}
		
	}
	
	// get line
	public String[] getLine() {
		int x = ThreadLocalRandom.current().nextInt(1, bank.size());
		return bank.get(x);
	}
	
	// get question from number
	public String getQuestionFromNumber(int x) {
		String question = bank.get(x)[0];
		return question;
	}
	
	// get answer from number
	public String getAnswerFromNumber(int x) {
		String answer = bank.get(x)[6];
		return answer;
	}
	
	// get answer bank from number
	public List<String> getAnswerBankFromNumber(int x) {
		List<String> ansBank = new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			if ((!(bank.get(x)[i].equals(".")))&&(!(bank.get(x)[i].isBlank()))){
				ansBank.add(bank.get(x)[i]);
				index++;
			}
		}
		Collections.shuffle(ansBank);
		return ansBank;
	}
	
	// get explanation
	public String getExplanation(int x) {
		String explanation = bank.get(x)[7];
		return explanation;
	}
	
	// generate question
	public int generateQNumber() {
		int x = ThreadLocalRandom.current().nextInt(0, bank.size());		
		return x;
	}
	
	// get question
	public String getQ(String[] line) {
		return line[0];
	}
	
	// get answers
	
	// get right answer
	
	
	
	
	/**
	 * Ideally, I'd like to have both getQ() and getA() functions,
	 * but that would require some sort of token that gets passed
	 * back and forth between the caller and the function so that
	 * getA() knows WHICH question it is fetching the answer for.
	 */
}
