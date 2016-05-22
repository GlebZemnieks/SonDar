package ru.sondar.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.sondar.core.logging.EmptyLogging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        TextView view = new TextView(this);
        view.setText((new EmptyLogging()).toString());
        layout.addView(view);
        setContentView(layout);
    }
}
