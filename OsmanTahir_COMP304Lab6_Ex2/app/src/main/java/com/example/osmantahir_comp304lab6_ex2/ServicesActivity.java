package com.example.osmantahir_comp304lab6_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ServicesActivity extends AppCompatActivity {

    private TextView textView;
    Button buttonStart;
    Button buttonStop;

    public static final String INFO_INTENT = "com.example.inika.samplelab6_2.INFO_UPDATE";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        textView = (TextView) findViewById(R.id.textView);
        buttonStart = (Button) findViewById(R.id.btnStartService);
        buttonStop = (Button) findViewById(R.id.btnStopService);




    }
    //
    public void startService(View view) {

        startService(new Intent(getBaseContext(), SimpleService.class));

        Intent intent = getIntent();
        String name = intent.getStringExtra(INFO_INTENT);
        textView.setText(name);



    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(),
                SimpleService.class));
    }

    //This will handle the broadcast
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        //@Override
        public void onReceive(Context context, Intent intent) {
            //textView.setText("Here");
            String action = intent.getAction();
            if (action.equals(SimpleService.INFO_INTENT)) {
                String info = intent.getStringExtra(INFO_INTENT);
                textView.setText(info);
            }
        }
    };






    public void onResume()
    {
        super.onResume();
        //This needs to be in the activity that will end up receiving the broadcast
        registerReceiver(receiver, new IntentFilter(INFO_INTENT));

    }






}

