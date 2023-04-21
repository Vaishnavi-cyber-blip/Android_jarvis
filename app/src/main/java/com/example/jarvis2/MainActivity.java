package com.example.jarvis2;

import static com.example.jarvis2.Functions.wishMe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private SpeechRecognizer recognizer;
    private TextView textView;
    private TextToSpeech tts;
    FloatingActionButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.floatingActionButton);
        textView = findViewById(R.id.textView);

//        button.setOnClickListener(view -> startRecording());

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        //findViewById();
        
        initialize_Text_to_speech();

        initialize_Result();
    }

    private void initialize_Text_to_speech() {
        tts = new TextToSpeech(this, i -> {

            if(tts.getEngines().size()==0)
            {
                Toast.makeText(MainActivity.this, "Engine is not available", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String s = wishMe();
                speak("Hi i am wednesday..."+s);

            }
        });
    }
    private void speak(String s)
    {

        tts.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
    }
//    private void findViewById()
//    {
//
//        textView = findViewById(R.id.textView);
//    }

    private void initialize_Result()
    {
        if (SpeechRecognizer.isRecognitionAvailable(this))
        {
            recognizer = SpeechRecognizer.createSpeechRecognizer(this);
            recognizer.setRecognitionListener(new RecognitionListener()
            {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle)
                {
                    ArrayList<String> result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    Toast.makeText(MainActivity.this, ""+result.get(0), Toast.LENGTH_SHORT).show();
                    textView.setText(result.get(0));
                    response(result.get(0));

                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }

    }

    private void response(String s)
    {
        if(s.contains("hi"))
        {
            speak("Hello");
        }

        if(s.contains("fine"))
        {
            speak("its good to know that you are fine...how may i help you?");
        }
        else if(s.contains("I am not fine"))
        {
            speak("did you broke up? had a bad day? or a fought with someone?");
        }

    }

//    public void startRecording()
//    {
//        Toast.makeText(this, "me vaishnavi", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
//
//        recognizer.startListening(intent);
//
//
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startRecord(View view) {

//        Toast.makeText(this, "me vaishnavi", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);

        recognizer.startListening(intent);
    }
}