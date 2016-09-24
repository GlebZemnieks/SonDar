package ru.sondar.client.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by GlebZemnieks on 9/24/2016.
 */
public class ErrorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        Exception exception = (Exception)getIntent().getSerializableExtra("exception");

        TextView className = new TextView(this);
        className.setText(exception.getClass().toString());
        layout.addView(className);

        TextView errorMsg = new TextView(this);
        errorMsg.setText(exception.getMessage());
        layout.addView(errorMsg);

        setContentView(layout);
    }
}
