package com.example.testcsound;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    ToggleButton togglebutton;
    TextView label;
    double min=20;
    double max=20020;
    double loginterval=Math.log(max/min);
    CSDPlayer csoundObj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        seekbar=findViewById(R.id.seekBar);
        togglebutton=findViewById(R.id.toggleButton);
        label=findViewById(R.id.label);

        csoundObj=new CSDPlayer();
        csoundObj.pause();
        csoundObj.startCsound(getApplicationContext(),R.raw.test);

        label.setText(Math.round(min)+" Hz");
        csoundObj.getCsound().SetChannel("slider",min);


        togglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    csoundObj.play();
                } else {
                    csoundObj.pause();
                }

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                double value=min*Math.exp(progress*loginterval/100);

                csoundObj.getCsound().SetChannel("slider",value);
                String l=Math.round(value)+" Hz";
                label.setText(l);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onDestroy() {
        csoundObj.stop();
        super.onDestroy();
    }


}
