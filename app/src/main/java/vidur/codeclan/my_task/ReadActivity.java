package vidur.codeclan.my_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        tv1 = (TextView)findViewById(R.id.textView);

        MyDatabase mdb = new MyDatabase(this);
        mdb.open();
        String result = mdb.read();
        mdb.close();

        tv1.setText(result);

    }
}
