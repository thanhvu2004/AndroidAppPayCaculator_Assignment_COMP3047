package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText hours_input = findViewById(R.id.hours_input);
        EditText rate_input = findViewById(R.id.rate_input);
        Button submit_btn = findViewById(R.id.submit_btn);
        TextView pay_output = findViewById(R.id.pay_output);
        TextView ot_output = findViewById(R.id.ot_output);
        TextView total_output = findViewById(R.id.total_output);
        TextView tax_output = findViewById(R.id.tax_ouput);
        submit_btn.setOnClickListener(v -> {
            calculatePay(hours_input, rate_input, pay_output, ot_output, total_output, tax_output);
        });
    }

    public void calculatePay(EditText hours_input, EditText rate_input,
                             TextView pay_output, TextView ot_output,
                             TextView total_output, TextView tax_output) {
        double hours = Double.parseDouble(hours_input.getText().toString());
        double rate = Double.parseDouble(rate_input.getText().toString());

        double pay, otp;

        if (hours <= 40) {
            otp = 0;
            pay = hours * rate;
        } else {
            otp = (hours - 40) * (rate * 1.5);
            pay = 40 * rate;
        }
        pay_output.setText(String.valueOf(pay));
        ot_output.setText(String.valueOf(otp));
        total_output.setText(String.valueOf(pay + otp));
        tax_output.setText(String.valueOf((pay + otp) * 0.15));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}