package fr.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Calculator";
    Button clickedButton;
    private TextView operationTV;
    Button equalButton;
    Button equalButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainLL = findViewById(R.id.mainLL);
        LinearLayout mainLL2 = findViewById(R.id.mainLL2);
        equalButton = new Button(this);
        equalButton.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        equalButton.setText(R.string.res);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        });
        mainLL.addView(equalButton);

        equalButton2 = new Button(this);
        equalButton2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        equalButton2.setText(R.string.res);
        equalButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        });
        mainLL2.addView(equalButton2);

    }

    public void myClickHandler(View view) {
        int clickedButtonId = view.getId();
        clickedButton = (Button) findViewById(clickedButtonId);
        operationTV = (TextView) findViewById(R.id.textViewOperation);
        TextView resTV = (TextView) findViewById(R.id.textViewResult);

        if (clickedButtonId == equalButton.getId()) {
            Integer res = calculate(operationTV.getText().toString());
            if (res == null) {
                resTV.setText("ERR");
            } else {
                resTV.setText(res.toString());
            }
        } else {
            String number = clickedButton.getText().toString();
            operationTV.append(number);
        }

    }

    private Integer calculate(String operation) {
        if (operation.equals("")) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            String[] inputs = operation.split("(?!^)\\b");
            Log.d(TAG, "calculate: INPUTS = " + Arrays.toString(inputs) + " SIZE = " + inputs.length);
            if (inputs.length == 0) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
                return null;
            }
            if (inputs.length == 1) {
                Toast.makeText(this, "Please enter an operand", Toast.LENGTH_SHORT).show();
                return null;
            }
            if (inputs.length == 2) {
                Toast.makeText(this, "Please enter an other number", Toast.LENGTH_SHORT).show();
                return null;
            }
            System.out.println(Arrays.toString(inputs));
            if (inputs.length < 4) {
                String operand = inputs[1];
                int digit1 = Integer.parseInt(inputs[0]);
                int digit2 = Integer.parseInt(inputs[2]);
                operationTV.setText("");
                switch (operand) {
                    case "+":
                        return digit1 + digit2;
                    case "-":
                        return digit1 - digit2;
                    case "*":
                        return digit1 * digit2;
                    case "/":
                        return digit1 / digit2;
                    default:
                        return 0;
                }
            } else {
                Toast.makeText(this, "Only one operand please", Toast.LENGTH_LONG).show();
                operationTV.setText("");
                return 0;
            }
        }

    }

}