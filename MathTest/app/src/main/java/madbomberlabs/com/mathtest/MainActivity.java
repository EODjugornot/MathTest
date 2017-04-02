package madbomberlabs.com.mathtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    RadioButton rbEasy, rbMed, rbHard;
    RadioButton rbAdd, rbSub, rbMul, rbDiv, rbRan;
    TextView tvOperator, tvInt1, tvInt2;
    TextView tvCorrect, tvIncorrect;
    TextView test;
    EditText etAns;
    ImageView imgSmiley;
    Float ans;
    Integer x, y, countCorrect, countIncorrect;
    Toast toastNull, toastCorrect, toastIncorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare skill level buttons
        rbEasy = (RadioButton)findViewById(R.id.rbEasy);
        rbMed = (RadioButton)findViewById(R.id.rbMedium);
        rbHard = (RadioButton)findViewById(R.id.rbHard);

        // Declare operator buttons
        rbAdd = (RadioButton)findViewById(R.id.rbAdd);
        rbSub = (RadioButton)findViewById(R.id.rbSub);
        rbMul = (RadioButton)findViewById(R.id.rbMultiply);
        rbDiv = (RadioButton)findViewById(R.id.rbDivide);
        rbRan = (RadioButton)findViewById(R.id.rbRandom);

        // Declare TextView and EditView
        tvOperator = (TextView)findViewById(R.id.tvOperator);
        tvInt1 = (TextView)findViewById(R.id.tvInt1);
        tvInt2 = (TextView)findViewById(R.id.tvInt2);
        etAns = (EditText)findViewById(R.id.etAns);
        tvCorrect = (TextView)findViewById(R.id.tvCorrect);
        tvIncorrect = (TextView)findViewById(R.id.tvIncorrect);
        test = (TextView)findViewById(R.id.test);

        // Declare ImageView
        imgSmiley = (ImageView)findViewById(R.id.imgSmiley);

        // Declare counts
        countCorrect = 0;
        countIncorrect = 0;

        // Declare Toasts
        toastNull = Toast.makeText(this, "Answer field should not be blank", Toast.LENGTH_SHORT);
        toastCorrect = Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT);
        toastIncorrect = Toast.makeText(this, "Your answer was wrong!", Toast.LENGTH_SHORT);

        setOperator();
        setIntegers();

    }

    // Refresh skill on RadioButton change
    public void goSkill(View v)
    {
        setIntegers();
        clearAns();
    }

    // Refresh operator on RadioButton change
    public void goOperator(View v)
    {
        setOperator();
        clearAns();
    }



    // Check answer
    public void goCheck(View v) {

        // Do math based on x and y
        if (rbAdd.isChecked()) {
            ans = (float) x + y;

        } else if (rbSub.isChecked()) {
            ans = (float) x - y;

        } else if (rbMul.isChecked()) {
            ans = (float) x * y;

        } else if (rbDiv.isChecked()) {
            ans = (float) x / y;

        } else if (rbRan.isChecked()) { // Random button sets operator based on string
            if (tvOperator.getText().toString().equals("+")) {
                ans = (float) x + y;

            } else if (tvOperator.getText().toString().equals("-")) {
                ans = (float) x - y;

            } else if (tvOperator.getText().toString().equals("X")) {
                ans = (float) x * y;

            } else if (tvOperator.getText().toString().equals("/")) {
                ans = (float) x / y;
            }

        }


        // Check if user input is blank, compare to ans variable
        if (etAns.getText().toString().equals("")) { // blank field, display error
            toastNull.setGravity(Gravity.CENTER, 0, 150);
            toastNull.show();
            imgSmiley.setImageResource(R.drawable.puzzled_smiley); // Set puzzled smiley

        } else if (Float.parseFloat(etAns.getText().toString()) == ans) { // correct answer
            toastCorrect.setGravity(Gravity.CENTER, 0, 150);
            toastCorrect.show();
            imgSmiley.setImageResource(R.drawable.happy_smiley); // Set happy smiley
            countCorrect++;
            tvCorrect.setText(String.valueOf(countCorrect));
            etAns.setText("");
            clearAns();
            setIntegers();
            setOperator();

        } else { // wrong answer
            toastIncorrect.setGravity(Gravity.CENTER, 0, 150);
            toastIncorrect.show();
            imgSmiley.setImageResource(R.drawable.sad_smiley); // Set sad smiley
            countIncorrect++;
            tvIncorrect.setText(String.valueOf(countIncorrect));
            test.setText(String.valueOf(ans));
        }

    }

    // Button for next problem
    public void goNext(View v)
    {
        // Reset operator and integers, clear answer
        setOperator();
        setIntegers();
        clearAns();
        etAns.setText("");
    }

    // Close application
    public void goClose(View v)
    {
        finish();
    }

    // Clear wrong answer
    public void clearAns()
    {
        test.setText("");
    }

    // Set operator
    public void setOperator()
    {
        // Set operator based on operator RadioButton
        if (rbRan.isChecked())
        {
            ranOperator();

        } else if (rbAdd.isChecked()) {
            tvOperator.setText("+");

        } else if (rbSub.isChecked()) {
            tvOperator.setText("-");

        } else if (rbMul.isChecked()) {
            tvOperator.setText("X");

        } else if (rbDiv.isChecked()) {
            tvOperator.setText("/");
        }
    }

    // Random operator generator
    public void ranOperator()
    {
        int num = (int) (Math.random() * 4 + 1); // random number 1 - 4

        // Set operator based on result of num
        if (num == 1)
        {
            tvOperator.setText("+");

        } else if (num == 2) {
            tvOperator.setText("-");

        } else if (num == 3) {
            tvOperator.setText("X");

        } else if (num == 4) {
            tvOperator.setText("/");
        }

    }

    // Check equation for valid answer
    public void checkAns()
    {
        // Check for invalid answer
        if (ans.isNaN() || (tvOperator.getText().toString().equals("/") && y == 0))
        {
            setIntegers();
        }
    }

    // Set integers
    public void setIntegers()
    {

        // Set numbers based on skill RadioButton
        if (rbEasy.isChecked())
        {
            generateSmNum();
            tvInt1.setText(String.valueOf(x));
            tvInt2.setText(String.valueOf(y));

        } else if (rbMed.isChecked()) {
            generateMedNum();
            tvInt1.setText(String.valueOf(x));
            tvInt2.setText(String.valueOf(y));

        } else if (rbHard.isChecked()) {
            generateLgNum();
            tvInt1.setText(String.valueOf(x));
            tvInt2.setText(String.valueOf(y));
        }

    }

    // Random number generator - small
    public void generateSmNum()
    {
        if (tvOperator.getText().toString().equals("/"))
        {
            int x_num0_9 = (int) (Math.random() * 10 ); // Random number from 0 - 9 for x
            int y_num0_9 = (int) (Math.random() * 9 + 1 ); // Random number from 1 - 9 for y
            x = x_num0_9;
            y = y_num0_9;
        } else {
            int x_num0_9 = (int) (Math.random() * 10 ); // Random number from 0 - 9 for x
            int y_num0_9 = (int) (Math.random() * 10 ); // Random number from 0 - 9 for y
            x = x_num0_9;
            y = y_num0_9;
        }
    }

    // Random number generator - med
    public void generateMedNum()
    {
        if (tvOperator.getText().toString().equals("/"))
        {
            int x_num10_99 = (int) (Math.random() * 100); // Random number from 0 - 99 for x
            int y_num10_99 = (int) (Math.random() * 99 + 1); // Random number from 1 - 99 for y
            x = x_num10_99;
            y = y_num10_99;
        } else {
            int x_num10_99 = (int) (Math.random() * 100); // Random number from 10 - 99 for x
            int y_num10_99 = (int) (Math.random() * 100); // Random number from 10 - 99 for y
            x = x_num10_99;
            y = y_num10_99;
        }
    }

    // Random number generator - large
    public void generateLgNum()
    {
        if (tvOperator.getText().toString().equals("/"))
        {
            int x_num100_999 = (int) (Math.random() * 1000); // Random number from 0 - 999 for x
            int y_num100_999 = (int) (Math.random() * 999 + 1); // Random number from 1 - 999 for y
            x = x_num100_999;
            y = y_num100_999;
        } else {
            int x_num100_999 = (int) (Math.random() * 1000); // Random number from 100 - 999 for x
            int y_num100_999 = (int) (Math.random() * 1000); // Random number from 100 - 999 for y
            x = x_num100_999;
            y = y_num100_999;
        }
    }

}
