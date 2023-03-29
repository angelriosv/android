package com.example.voice_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private TextView textView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        textView = findViewById(R.id.textView);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
        //intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,3 );

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }
            @Override
            public void onBeginningOfSpeech() {
            }
            @Override
            public void onRmsChanged(float rmsdB) {
            }
            @Override
            public void onBufferReceived(byte[] buffer) {
            }
            @Override
            public void onEndOfSpeech() {
            }
            @Override
            public void onError(int error) {
            }
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String string = "";
                textView.setText("");
                if (matches != null){
                    string = matches.get(0);
                    textView.setText(string);

                    if (string.equals("abre el hiper") || string.equals("muéstrame el hiper")){
                        textToSpeech.speak("Abriendo el hiper ", TextToSpeech.QUEUE_FLUSH, null, null);
                        abrir_iperc();
                    } else {
                        if (string.equals("")) {
                            textToSpeech.speak("No te he entendido", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else {
                            textToSpeech.speak(string, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                }
            }
            @Override
            public void onPartialResults(Bundle partialResults) {
            }
            @Override
            public void onEvent(int eventType, Bundle params) {
            }
        });
    }

    public void startButton(View view) throws InterruptedException {
        textToSpeech.speak("Dime una instrucción", TextToSpeech.QUEUE_FLUSH, null, null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speechRecognizer.startListening(intent);

    }

    private void abrir_iperc() {
        Uri uri = Uri.parse("https://docs.google.com/presentation/d/1IR0szKQjdIGf-G3ahI9UCFQ5PL8W0kgheluTmUWTNOE/edit?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }
}
