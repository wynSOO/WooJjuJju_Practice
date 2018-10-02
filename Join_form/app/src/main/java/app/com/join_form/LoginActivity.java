package app.com.join_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView join_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        join_go = findViewById(R.id.txt_join_go);

        join_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent join_i = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(join_i);
            }
        });
    }
}
