package edu.unca.psychogeography;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Psychogeography extends Activity {
	private Button mNextButton;
	private TextView mStatementTextView;
	private static final float fontSize = 30;
	private static final String TAG = "Psychogeography" ;
	
	private String[] color = new String[] {
			"green", "blue", "orange",
			"yellow", "white", "black",
			"purple", "maroon", "burgundy",
	};

	// Array of statement Objects
	private Statements[] mStatementBank = new Statements[] {
			new Statements(R.string.statementBlocksForward),
			new Statements(R.string.statementBlocksRight),
			new Statements(R.string.statementBlocksBack),
			new Statements(R.string.statementBlocksLeft),
			new Statements(R.string.statementClimbForward),
			new Statements(R.string.statementClimbRight),
			new Statements(R.string.statementClimbBack),
			new Statements(R.string.statementClimbLeft), 
			new Statements(R.string.statementFollowShirt),};
	private int mCurrentIndex = 0;
	private int maxLength = mStatementBank.length - 1;

	// String substitution
	private String substituteQuestion(String string, int startingIndex) {
		int loc;
		
		//Block variance
		if (startingIndex == 0 || startingIndex == 1 || startingIndex == 2
				|| startingIndex == 3) {
			loc = 3;
			int rand = mStatementBank[mCurrentIndex].randInt(1, 4);
			if(rand == 1) {
				string = string.replace("Go blocks", "Go " + rand + " block");
			} else {
				string = string.replace("Go blocks", "Go " + rand + " blocks");
			}
		}
		
		//Shirt Color
		if(startingIndex == 8 || startingIndex == 9 || startingIndex == 10 || startingIndex == 11) {
			loc = 23;
			int randColor = mStatementBank[mCurrentIndex].randInt(0, color.length - 1);
			int randNum = mStatementBank[mCurrentIndex].randInt(1, 4);
			if(randNum == 1) {
				string = string.replace("for blocks", "for " + randNum + " block");
			} else {
				string = string.replace("for blocks", "for " + randNum + " blocks");
			}		
			string = string.substring(0, loc)
					+ color[randColor] + " "
					+ string.substring(loc, string.length());		
		}
		return string;
	}

	// Refresh Question
	private void updateQuestion() {
		mCurrentIndex = mStatementBank[mCurrentIndex].randInt(0, maxLength);
		int statement = mStatementBank[mCurrentIndex].getStatement();
		String revisedStatement = this.getString(statement);
		// Substituting values out for variable information
		revisedStatement = substituteQuestion(revisedStatement, mCurrentIndex);
		mStatementTextView.setText(revisedStatement);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate(Bundle called");
		setContentView(R.layout.activity_psychogeography);

		// Array of statements
		mStatementTextView = (TextView) findViewById(R.id.question_text_view);
		mStatementTextView.setTextSize(fontSize);
		
		// Concat random variable with statement
		int rand = mStatementBank[mCurrentIndex].randInt(0, maxLength);
		mCurrentIndex = rand;
		updateQuestion();

		// Next Button
		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int rand = mStatementBank[mCurrentIndex].randInt(0, maxLength);
				mCurrentIndex = rand;
				updateQuestion();
			}
		});
	} //End of onCreate(Bundle)
	
	@Override 
    public void onStart() { 
    	super.onStart(); 
    	Log.d(TAG, "onStart() called");
    }
    
    @Override 
    public void onPause() { 
    	super.onPause(); 
    	Log.d(TAG, "onPause() called"); 
    	} 
    @Override 
    public void onResume() { 
    	super.onResume(); 
    	Log.d(TAG, "onResume() called"); 
    	}
    @Override 
    public void onStop() { 
    	super.onStop(); 
    	Log.d(TAG, "onStop() called"); 
    	} 
    @Override public void onDestroy() { 
    	super.onDestroy(); 
    	Log.d(TAG, "onDestroy() called");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.psychogeography, menu);
		return true;
	}

}
