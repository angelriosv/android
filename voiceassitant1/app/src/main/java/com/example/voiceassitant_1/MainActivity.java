package com.example.voiceassitant_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int VOICE_RECOGNITION_REQUEST = 0x10101;

    //private TextToSpeech mTextToSpeech = null;
    //private boolean speechSynthReady = false;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
    }
    public void speakToMe (View view)  {
        textToSpeech.speak("Que documento desea abrir", TextToSpeech.QUEUE_FLUSH,null,null);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,1);
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"abre el hiper");
        startActivityForResult(intent,VOICE_RECOGNITION_REQUEST);
    }

    public void AbrirMSDS (View view)  {
        textToSpeech.speak("Que archivo MSDS deseas buscar", TextToSpeech.QUEUE_FLUSH,null,null);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent1.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,1);
        startActivityForResult(intent1,VOICE_RECOGNITION_REQUEST);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) {
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            TextView textView = (TextView) findViewById(R.id.speech_to_text);
            String firstMatch = (String) matches.get(0);
            //textView.setText(firstMatch);
            if (firstMatch.equals("hiper")) {
                textToSpeech.speak("Aqui tienes el IPERC", TextToSpeech.QUEUE_FLUSH,null,null);
                abrir_iperc();
            } else if (firstMatch.equals("política integral"))  {
                textToSpeech.speak("Aqui tienes la politica integral", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_integral();
            } else if (firstMatch.equals("política disciplinaria")) {
                textToSpeech.speak("Aqui tienes la política disciplinaria", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_disciplinaria();
            } else if (firstMatch.equals("política de alcohol y drogas") || firstMatch.equals("política alcohol y drogas") || firstMatch.equals("política alcohol drogas")) {
                textToSpeech.speak("Aqui tienes la política de alcohol y drogas", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_alcohol_drogas();
            } else if (firstMatch.equals("política de redes sociales") || firstMatch.equals("política redes sociales")){
                textToSpeech.speak("Aqui tienes la política de redes sociales", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_redes_sociales();
            } else if (firstMatch.equals("política de suspensión de tareas") || firstMatch.equals("política suspensión de tareas") || firstMatch.equals("política suspensión tareas")) {
                textToSpeech.speak("Aqui tienes la política de suspensión de tareas", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_suspension_tareas();
            } else if (firstMatch.equals("política del desarrollo sostenible") || firstMatch.equals("política de desarrollo sostenible") || firstMatch.equals("política desarrollo sostenible")) {
                textToSpeech.speak("Aqui tienes la política de desarrollo sostenible", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_desarrollo_sostenible();
            } else if (firstMatch.equals("política antisoborno")) {
                textToSpeech.speak("Aqui tienes la política antisoborno", TextToSpeech.QUEUE_FLUSH, null , null);
                politica_antisoborno();
            } else if (firstMatch.equals("mini sds agua destilada")) {
                textToSpeech.speak("Aqui tienes el mini sds de agua destilada", TextToSpeech.QUEUE_FLUSH, null , null);
                mini_sds_agua_destilada();
            } else if (firstMatch.equals("mini sds agua purificada")) {
                textToSpeech.speak("Aqui tienes el mini sds de agua purificada", TextToSpeech.QUEUE_FLUSH, null , null);
                mini_sds_agua_purificada();
            } else if (firstMatch.equals("gel pack")) {
                textToSpeech.speak("Aqui tienes el MSDS gel pack", TextToSpeech.QUEUE_FLUSH, null , null);
                sds_gelpack();
            } else if (firstMatch.equals("agua destilada")) {
                textToSpeech.speak("Aqui tienes el MSDS agua destilada", TextToSpeech.QUEUE_FLUSH, null , null);
                sds_agua_destilada();
            } else if (firstMatch.equals("solución absorbente monóxido de carbono")) {
                textToSpeech.speak("Aqui tienes el SDS solución absorbente monóxido de carbono", TextToSpeech.QUEUE_FLUSH, null , null);
                sds_solucion_absorbente_CO();
            } else if (firstMatch.equals("solución absorbente ácido sulfhídrico")) {
                textToSpeech.speak("Aquí tienes el SDS solución ácido sulfhídrico", TextToSpeech.QUEUE_FLUSH, null , null);
                solucion_absorbente_h2s();
            } else if (firstMatch.equals("tubo orbo")) {
                textToSpeech.speak("Aqui tienes el tubo orbo", TextToSpeech.QUEUE_FLUSH, null , null);
                turbo_orbo_32s();
            } else if (firstMatch.equals("acetato de zinc 0.05 normal")) {
                textToSpeech.speak("Aquí tienes el SDS de acetato de zinc 0.05N", TextToSpeech.QUEUE_FLUSH, null , null);
                acetato_de_zinc_005n();
            } else if (firstMatch.equals("acetato de zinc 2 normal")) {
                textToSpeech.speak("Aquí tienes el SDS de acetato de zinc 2N", TextToSpeech.QUEUE_FLUSH, null , null);
                acetato_de_zinc_2n();
            } else if (firstMatch.equals("ácido clorhídrico 6 normal")) {
                textToSpeech.speak("Aquí tienes el SDS de ácido clorhídrico 6N", TextToSpeech.QUEUE_FLUSH, null , null);
                acido_clorhidrico_6n();
            } else {
                textToSpeech.speak("Todavía no se hacer eso", TextToSpeech.QUEUE_FLUSH, null , null);
            }

        }
    }

    private void abrir_iperc() {
        Uri uri = Uri.parse("https://docs.google.com/presentation/d/1IR0szKQjdIGf-G3ahI9UCFQ5PL8W0kgheluTmUWTNOE/edit?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_integral() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1SoDX0oeQM_Z5yiFRkglc0BxafmSyvl2e/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_disciplinaria() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1X7043SA3k_z5MVvpLZ7FjV-dxNDyu8sF/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_alcohol_drogas() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1WN6azysaKsDHX4gQczdSB4oXbzTFNE2Y/view?usp=sharing");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_redes_sociales() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1nyCVMpsLm6GcyqTOQNgitIBslOMb1RSV/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_suspension_tareas() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1ZC0mcPoRirokl1Dg_NLCbDALuDmzPvtk/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_desarrollo_sostenible() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1TvHuJ3GVHJJGe0O554RcXTXuTIo6bnQ-/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void politica_antisoborno() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1gr-nkK93NfuKPRAsPxczaKEW_hVbBpyy/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void mini_sds_agua_destilada() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1ARrwSh7eRIxJT4SrPs0cnneJ2xH6qoJ7/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void mini_sds_agua_purificada() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1-sIGuvR993JtE3mqPDWKOZXz35tt0bYS/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void sds_gelpack() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1Pp6cV83Y-tHOkqJKkbOUe3zGz0wEi1dX/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void sds_agua_destilada() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1JM6PqMDlDzuyxqZtP3WwX6azY0g04ft0/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void sds_solucion_absorbente_CO() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1JJXajc0NC0QP-XxesdimvX6W_M93oM11/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void solucion_absorbente_h2s() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1M7hA8qy_wnPdxOsFJxYKMWvRfZWLnsMG/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void turbo_orbo_32s() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1E4WV4G6sBU5Z7lthlGihJbPR6LJVst-p/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void acetato_de_zinc_005n() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1K6GXqyjEkWyqA5VfuiMND5KhXqSTbBUE/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void acetato_de_zinc_2n() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/12tO5aKRdWCjj6aG8z3oenGOdK9uiCD0f/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }

    private void acido_clorhidrico_6n() {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1llebeGx-7nnMQfrEIK4maHBwkuMU2_em/view?usp=share_link");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
    }
}