package fr.android.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<Integer, Integer> valueByIdButton = new HashMap<Integer, Integer>() {{
        put(R.id.button0, 0);
        put(R.id.button1, 1);
        put(R.id.button2, 2);
        put(R.id.button3, 3);
        put(R.id.button4, 4);
        put(R.id.button5, 5);
        put(R.id.button6, 6);
        put(R.id.button7, 7);
        put(R.id.button8, 8);
        put(R.id.button9, 9);

        // For TableLayout buttons
        put(R.id.button02, 0);
        put(R.id.button12, 1);
        put(R.id.button22, 2);
        put(R.id.button32, 3);
        put(R.id.button42, 4);
        put(R.id.button52, 5);
        put(R.id.button62, 6);
        put(R.id.button72, 7);
        put(R.id.button82, 8);
        put(R.id.button92, 9);
    }};

    private HashMap<Integer, Character> operatorByIdButton = new HashMap<Integer, Character>() {{
        put(R.id.buttonAddition, '+');
        put(R.id.buttonSubtraction, '-');
        put(R.id.buttonDivide, '/');
        put(R.id.buttonMultiply, '*');

        // For TableLayout buttons
        put(R.id.buttonAddition2, '+');
        put(R.id.buttonSubtraction2, '-');
        put(R.id.buttonDivide2, '/');
        put(R.id.buttonMultiply2, '*');
    }};

    private TextView operationTV;
    private TextView resultatTV;
    private Integer num1 = 0;
    private Integer num2 = 0;
    private Character operator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operationTV = findViewById(R.id.operationTV);
        resultatTV = findViewById(R.id.resultatTV);
        LinearLayout buttonsLayout = findViewById(R.id.buttonsLinearLayout);
        Button equalButton = new Button(this);
        equalButton.setId(R.id.buttonEqual);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        });
        equalButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        equalButton.setText(R.string.equal);
        buttonsLayout.addView(equalButton);

    }

    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.button0: addInNum(valueByIdButton.get(R.id.button0)); break;
            case R.id.button1: addInNum(valueByIdButton.get(R.id.button1)); break;
            case R.id.button2: addInNum(valueByIdButton.get(R.id.button2)); break;
            case R.id.button3: addInNum(valueByIdButton.get(R.id.button3)); break;
            case R.id.button4: addInNum(valueByIdButton.get(R.id.button4)); break;
            case R.id.button5: addInNum(valueByIdButton.get(R.id.button5)); break;
            case R.id.button6: addInNum(valueByIdButton.get(R.id.button6)); break;
            case R.id.button7: addInNum(valueByIdButton.get(R.id.button7)); break;
            case R.id.button8: addInNum(valueByIdButton.get(R.id.button8)); break;
            case R.id.button9: addInNum(valueByIdButton.get(R.id.button9)); break;
            case R.id.buttonAddition: setOperator(operatorByIdButton.get(R.id.buttonAddition)); break;
            case R.id.buttonSubtraction: setOperator(operatorByIdButton.get(R.id.buttonSubtraction)); break;
            case R.id.buttonMultiply: setOperator(operatorByIdButton.get(R.id.buttonMultiply)); break;
            case R.id.buttonDivide: setOperator(operatorByIdButton.get(R.id.buttonDivide)); break;
            case R.id.buttonEqual: compute(); break;

            // For tableLayout Buttons
            case R.id.button02: addInNum(valueByIdButton.get(R.id.button02)); break;
            case R.id.button12: addInNum(valueByIdButton.get(R.id.button12)); break;
            case R.id.button22: addInNum(valueByIdButton.get(R.id.button22)); break;
            case R.id.button32: addInNum(valueByIdButton.get(R.id.button32)); break;
            case R.id.button42: addInNum(valueByIdButton.get(R.id.button42)); break;
            case R.id.button52: addInNum(valueByIdButton.get(R.id.button52)); break;
            case R.id.button62: addInNum(valueByIdButton.get(R.id.button62)); break;
            case R.id.button72: addInNum(valueByIdButton.get(R.id.button72)); break;
            case R.id.button82: addInNum(valueByIdButton.get(R.id.button82)); break;
            case R.id.button92: addInNum(valueByIdButton.get(R.id.button92)); break;
            case R.id.buttonAddition2: setOperator(operatorByIdButton.get(R.id.buttonAddition2)); break;
            case R.id.buttonSubtraction2: setOperator(operatorByIdButton.get(R.id.buttonSubtraction2)); break;
            case R.id.buttonMultiply2: setOperator(operatorByIdButton.get(R.id.buttonMultiply2)); break;
            case R.id.buttonDivide2: setOperator(operatorByIdButton.get(R.id.buttonDivide2)); break;
            case R.id.buttonEqual2: compute(); break;
        }
    }

    private void addInNum(Integer numToAdd) {
        if (!operatorIsDefined()) {
            num1 = num1*10 + numToAdd;
        } else {
            num2 = num2*10 + numToAdd;
        }
        updateOperationView();
    }

    private void updateOperationView() {
        String operation = num2 == 0 ? num1.toString() : num1 + "" + operator.toString() + "" + num2;
        operationTV.setText(operation);
    }

    private void setResultView(String result) {
        resultatTV.setText(result);
    }

    private void setOperator(Character operator) {
        this.operator = operator;
        updateOperationView();
    }

    private void compute() {
        double result = 0;
        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1 / num2; break;
        }
        setResultView(String.valueOf(result));
        resetValue();
    }

    private void resetValue() {
        operator = ' ';
        num1 = 0;
        num2 = 0;
    }

    private boolean operatorIsDefined() {
        return operator != ' ';
    }
}