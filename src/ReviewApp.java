//package reviewQuestions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReviewApp extends JFrame implements ActionListener {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	public static String question = "This might be a very, very long question that needs to wrap around to the lower lines.";
	public static String answer = "";
	public static int answerIndex = 0;
	public static final String PATH = "Exam_3_Review.csv";
	public static String qPanelText = "";
	public static int questionNum = 1; // change to -1 later and have a if/then clause to handle?
	public static List<Integer> missedQuestions = new ArrayList<Integer>();
	public static String path = "/Users/marylay/eclipse-workspace/LearningTools_Java/src/Exam_3_Review.csv";
	public static TestBank testBank;
	public static boolean questionAnswered = false;
	
	JTextArea questionArea = new JTextArea();
	JTextArea explanationArea = new JTextArea();
	
	
	
	// Constructor
	public ReviewApp() {
		super("Review assistant");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Panels
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		JPanel questionPanel = new JPanel();
		this.add(questionPanel, BorderLayout.CENTER);
		JPanel eastPanel = new JPanel();
		this.add(eastPanel, BorderLayout.EAST);
		JPanel instructionPanel = new JPanel();
		this.add(instructionPanel, BorderLayout.NORTH);
		JPanel topEastPanel = new JPanel();
		JPanel bottomEastPanel = new JPanel();
		
		
		
		//Buttons
		JButton option1 = new JButton("1");
		option1.addActionListener(this);
		JButton option2 = new JButton("2");
		option2.addActionListener(this);
		JButton option3 = new JButton("3");
		option3.addActionListener(this);
		JButton option4 = new JButton("4");
		option4.addActionListener(this);
		JButton showAnswer = new JButton("Show Answer");
		showAnswer.addActionListener(this);
		JButton nextQ = new JButton("Next Question");
		nextQ.addActionListener(this);

	
		// SOUTH
		southPanel.setLayout(new FlowLayout());
		southPanel.add(nextQ);
		southPanel.add(showAnswer);
		
		// CENTER
		questionPanel.setLayout(new BorderLayout());
		questionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		questionArea.setText(question);
		questionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		questionArea.setEditable(false);
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		explanationArea.setLineWrap(true);
		explanationArea.setWrapStyleWord(true);
		questionPanel.add(questionArea, BorderLayout.CENTER);
		questionPanel.add(explanationArea, BorderLayout.SOUTH);
		
		// NORTH
		JLabel instructions = new JLabel("Select the best answer for each question.");
		instructionPanel.add(instructions);
		
		// EAST 
		/**
		explanationPanel.setLayout(new BorderLayout());
		//JTextArea explanation = new JTextArea(expl);
		explanationArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		explanationArea.setBackground(southPanel.getBackground());
		explanationArea.setLineWrap(true);
		explanationArea.setWrapStyleWord(true);
		explanationPanel.add(explanationArea);
		*/
		eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		eastPanel.add(option1);
		eastPanel.add(option2);
		eastPanel.add(option3);
		eastPanel.add(option4);
		
		
	}
	

	public static void main(String[] args) throws IOException {
		ReviewApp review = new ReviewApp();
		review.setVisible(true);
		
		try {
			testBank = new TestBank(path);
		}
		
		catch (IOException x) {
			System.out.println("There was a problem making the test bank");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("Next Question")) {
			questionAnswered = false;
			questionNum = testBank.generateQNumber();
			questionArea.setText(questionPanelText());
			explanationArea.setText(explanationPanelText(0));
		}
		
		if(actionCommand.equals("1")) {
			questionAnswered = true;
			checkAnswer(1); // currently not necessary bc does nothing but may want to change later
			explanationArea.setText(explanationPanelText(1));
		}
		if(actionCommand.equals("2")) {
			questionAnswered = true;
			checkAnswer(2); // currently not necessary bc does nothing but may want to change later
			explanationArea.setText(explanationPanelText(2));
		}
		if(actionCommand.equals("3")) {
			questionAnswered = true;
			checkAnswer(3); // currently not necessary bc does nothing but may want to change later
			explanationArea.setText(explanationPanelText(3));
		}
		if(actionCommand.equals("4")) {
			questionAnswered = true;
			checkAnswer(4); // currently not necessary bc does nothing but may want to change later
			explanationArea.setText(explanationPanelText(4));
		}
		
	}
	
	/**
	public String questionPanelText() {
		List<String> answers = new ArrayList<>();
		//qPanelText = question;
		Integer qNumber = 1;
		// Extra info --> do later
		
		// True/False
		if (qBankLine[1] == "TRUE") {
			answers.add("TRUE");
			answers.add("FALSE");
			qPanelText = question + "1. TRUE \n 2. FALSE";
		}
		
		// Multiple Choice (skip fill in the blank for now)
		else {
			for (int i = 1; i <= 5; i++) {
				if ((!(qBankLine[i].equals(".")))&&(qBankLine[i]!="")) {
					answers.add(qBankLine[i]);
					//qPanelText = qPanelText + "\n" + qNumber.toString() + qBankLine[i];
					//qNumber++;
				}
			}
			Collections.shuffle(answers);
			
			qPanelText = question;
			for (int i = 0; i < answers.size(); i++) {
				qPanelText = qPanelText + "\n" + qNumber.toString() +". "+ answers.get(i);
				qNumber++; 
			}
		}
		
		// return question = question + \n + option 1 +... etc
		return qPanelText;
	}
	*/
	
	public String questionPanelText() {
		System.out.println(questionNum);
		String text = "";
		String question = testBank.getQuestionFromNumber(questionNum);
		text = question + "\n" + "\n";
		List<String> options = testBank.getAnswerBankFromNumber(questionNum);
		
		for (int i = 0; i < options.size(); i++) {
			text = text + (i+1) + ". " + options.get(i) + "\n";
			// Identify the correct answer
			if (options.get(i).equals(testBank.getAnswerFromNumber(questionNum))) {
				answer = options.get(i);
				answerIndex = i+1;
			}
		}
		return text;
	}
	
	
	public String explanationPanelText(int x) {
		String text = "";
		if ((questionAnswered == true)&& checkAnswer(x)) {
			text = "Correct!\n\n" + testBank.getExplanation(questionNum);
		}
		else if (questionAnswered == true) {
			text = "Incorrect!";
			missedQuestions.add(questionNum);
		}
		else text = ""; // resets panel to blank when a new question is posted
		return text;
	}
	
	public void getQuestionNumber() {
		questionNum = ThreadLocalRandom.current().nextInt(1, testBank.bank.size());
	}
	
	public boolean checkAnswer(int x) {
		return answerIndex==x;
	}

}

/**
 * TODO
 * 1. Add "Show Answer" button (or make explanation panel automatically
 * show the answer when a question is missed)
 * 2. if 2nd option above, then make it impossible to click on
 * any more answers until the next question.
 * 3. Fix size of EAST panel- a little wider, please!
 * 4. Add "Quit" with a popup: "review missed questions"
 * 5. Add check box menu that allows options like:
 * - showing the answer when you get it wrong
 * - allowing multiple guesses (vs. locking your first guess)
 * 6. Hide answer buttons that are not needed (ie, have "True" and "False"
 * buttons appear for True and False questions) (If only 3 options available,
 * then only 3 buttons should be visible)
 * 7. Make buttons unusable before first question is selected.
 * */
