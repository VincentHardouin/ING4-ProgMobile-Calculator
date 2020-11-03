package fr.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
        put(R.id.button27, 0);
        put(R.id.button25, 1);
        put(R.id.button24, 2);
        put(R.id.button23, 3);
        put(R.id.button21, 4);
        put(R.id.button20, 5);
        put(R.id.button19, 6);
        put(R.id.button17, 7);
        put(R.id.button16, 8);
        put(R.id.button15, 9);
    }};

    private HashMap<Integer, Character> operatorByIdButton = new HashMap<Integer, Character>() {{
        put(R.id.buttonAddition, '+');
        put(R.id.buttonSubtraction, '-');
        put(R.id.buttonDivide, '/');
        put(R.id.buttonMultiply, '*');

        // For TableLayout buttons
        put(R.id.button14, '+');
        put(R.id.button18, '-');
        put(R.id.button26, '/');
        put(R.id.button22, '*');
    }};

    private TextView operationTV;
    private TextView resultatTV;
    private Integer num1 = 0;
    private Integer num2 = 0;
    private Character operator = ' ';
    Button equalButton2;
    Button equalButton;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operationTV = findViewById(R.id.textViewOperation);
        resultatTV = findViewById(R.id.textViewResult);
        LinearLayout mainLL = findViewById(R.id.mainLL);
        LinearLayout mainLL2 = findViewById(R.id.mainLL2);
        equalButton = new Button(this);
        equalButton.setId(R.id.buttonEqual);
        equalButton.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        equalButton.setText(R.string.equal);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        });
        mainLL.addView(equalButton);

        equalButton2 = new Button(this);
        equalButton2.setId(R.id.buttonEqual2);
        equalButton2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        equalButton2.setText(R.string.equal);
        equalButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        });
        mainLL2.addView(equalButton2);

        handler = new Handler();
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
//            case R.id.buttonEqual: compute(); break;
            case R.id.buttonEqual: new calculAsync().execute(); break;

            // For tableLayout Buttons
            case R.id.button27: addInNum(valueByIdButton.get(R.id.button27)); break;
            case R.id.button25: addInNum(valueByIdButton.get(R.id.button25)); break;
            case R.id.button24: addInNum(valueByIdButton.get(R.id.button24)); break;
            case R.id.button23: addInNum(valueByIdButton.get(R.id.button23)); break;
            case R.id.button21: addInNum(valueByIdButton.get(R.id.button21)); break;
            case R.id.button20: addInNum(valueByIdButton.get(R.id.button20)); break;
            case R.id.button19: addInNum(valueByIdButton.get(R.id.button19)); break;
            case R.id.button17: addInNum(valueByIdButton.get(R.id.button17)); break;
            case R.id.button16: addInNum(valueByIdButton.get(R.id.button16)); break;
            case R.id.button15: addInNum(valueByIdButton.get(R.id.button15)); break;
            case R.id.button14: setOperator(operatorByIdButton.get(R.id.button14)); break;
            case R.id.button18: setOperator(operatorByIdButton.get(R.id.button18)); break;
            case R.id.button22: setOperator(operatorByIdButton.get(R.id.button22)); break;
            case R.id.button26: setOperator(operatorByIdButton.get(R.id.button26)); break;
//            case R.id.buttonEqual2: compute(); break;
            case R.id.buttonEqual2: new calculAsync().execute(); break;
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

    /*
    private void compute() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                double result = 0;
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                }
                final double res = result;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ECE", "Running from the handler");
                        setResultView(String.valueOf(res));
                        resetValue();
                    }
                });
            }
        };
        new Thread(runnable).start();
    }
     */

    private void resetValue() {
        operator = ' ';
        num1 = 0;
        num2 = 0;
    }

    private boolean operatorIsDefined() {
        return operator != ' ';
    }

    private class calculAsync extends AsyncTask<Void, Double, Double> {

        @Override
        protected Double doInBackground(Void... voids) {
            Log.d("ECE", "Executing doInBackground");
            double result = 0;
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Double value) {
            Log.d("ECE", "Executing onPostExecute, value=" + value);
            setResultView(String.valueOf(value));
            resetValue();
        }
    }
}

