package fr.android.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout buttonsLayout = (LinearLayout) findViewById(R.id.buttonsLinearLayout);
        Button equalButton = new Button(this);
        equalButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        equalButton.setText(R.string.buttonEqual);
        buttonsLayout.addView(equalButton);

    }

    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.buttonAddition:
            case R.id.buttonSoustraction:
            case R.id.buttonMultiplication:
        }
    }
}