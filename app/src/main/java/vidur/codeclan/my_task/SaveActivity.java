package vidur.codeclan.my_task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
    EditText et1,et2;
    Button bt1,bt2;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        bt1 = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        c = this;
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name = et1.getText().toString();
                String t_details = et2.getText().toString();
                MyDatabase mdb = new MyDatabase(c);

                mdb.open();
                mdb.save(t_name , t_details);
                mdb.close();
                Toast.makeText(getApplicationContext() , "Data Saved Successfully " , Toast.LENGTH_LONG).show();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , ReadActivity.class);
                startActivity(i);
                finish();
            }
        });


        }
}
