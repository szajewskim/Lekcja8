package com.example.marek.zad_8;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ListView lv;
    private String[] lang;
    private String[] phrases;
    private MediaPlayer mediaPlayer;
    MediaPlayer mpButtonClick1;
    MediaPlayer mpButtonClick2;


    static final private int ALERT_DIALOG_PLAIN = 1;
    static final private int ALERT_DIALOG_BUTTONS = 2;
    static final private int ALERT_DIALOG_LIST = 3;
    static final private int CUSTOM_ALERT_DIALOG = 4;


    private Button btnNewAlertDialog;
    private Button btnNewAlertDialogButton;
    private Button btnNewAlertDialogList;
    private Button btnNewCustomAlertDialog;
    private Button button6;



    private void initResources() {
        Resources res = getResources();
        lang = res.getStringArray(R.array.languages);
        //  phrases = res.getStringArray(R.array.hello_world);
    }
    private void initLanguagesListView(){
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1,lang));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
                if (pos == 0)
                {

                    Intent intent = new Intent(getApplicationContext(), Telefon1.class);
                    startActivity(intent);
                }
                else if (pos == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), Telefon2.class);
                    startActivity(intent);
                }
                else if (pos == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), Telefon3.class);
                    startActivity(intent);
                }


            }
        });
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.langagues);
        btnNewAlertDialog = (Button) findViewById(R.id.button2);
        btnNewAlertDialogButton = (Button) findViewById(R.id.button3);
        btnNewAlertDialogList = (Button) findViewById(R.id.button4);
        btnNewCustomAlertDialog = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,Nagrywanie.class);
                startActivity(intent);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,Plik.class);
                startActivity(intent);

            }
        });



        mpButtonClick1 = MediaPlayer.create(this, R.raw.m);
        mpButtonClick2 = MediaPlayer.create(this, R.raw.n);
        initButtonsClick();

        initResources();
        initLanguagesListView();


    }

    private void initButtonsClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button2:
                        showDialog(ALERT_DIALOG_PLAIN);
                        break;
                    case R.id.button3:
                        showDialog(ALERT_DIALOG_BUTTONS);
                        break;
                    case R.id.button4:
                        showDialog(ALERT_DIALOG_LIST);
                        break;
                    case R.id.button5:
                        showDialog(CUSTOM_ALERT_DIALOG);
                        break;

                    default:
                        break;
                }
            }
        };
        btnNewAlertDialog.setOnClickListener(listener);
        btnNewAlertDialogButton.setOnClickListener(listener);
        btnNewAlertDialogList.setOnClickListener(listener);
        btnNewCustomAlertDialog.setOnClickListener(listener);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ALERT_DIALOG_PLAIN:
                return createPlainAlertDialog();
            case ALERT_DIALOG_BUTTONS:
                return createAlertDialogWithButtons();
            case ALERT_DIALOG_LIST:
                return createAlertDialogWithList();
            case CUSTOM_ALERT_DIALOG:
                return createCustomAlertDialog();
            default:
                return null;


        }
    }

    private Dialog createAlertDialogWithList() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Pierwsza nutka", "Druga nutka"};
        dialogBuilder.setTitle("Piosenki");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if(position == 0) {
                    // playSound();
                    if(mpButtonClick1.isPlaying() == true) {
                        // Pause the music player
                        mpButtonClick1.pause();
                        mpButtonClick1.seekTo(0);
                    }
                    // If it's not playing
                    else
                        // Resume the music player
                        mpButtonClick1.start();

                }
                else {
                    //playSound2();
                    if(mpButtonClick2.isPlaying() == true) {
                        // Pause the music player
                        mpButtonClick2.pause();
                        mpButtonClick2.seekTo(0);
                        // mpButtonClick2.pause();
                    }
                    // If it's not playing
                    else
                        // Resume the music player
                        mpButtonClick2.start();
                }

            }
        });
        return dialogBuilder.create();
    }
    public void playSound(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.m);
        mediaPlayer.start();
    }
    public void playSound2(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.n);
        mediaPlayer.start();
    }
    public void stop ( View v)
    {
        if ( mediaPlayer != null )
            mediaPlayer.stop();
    }


    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Wychodzę");
                MainActivity.this.finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowaleś wyjście");

            }
        });
        return dialogBuilder.create();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }


    private Dialog createPlainAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Witaj człowieku");
        dialogBuilder.setMessage("Tutaj możesz pooglądać telefony i posłuchać muzyki!");
        return dialogBuilder.create();

    }
    private Dialog createCustomAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View layout = getCustomDialogLayout();
        dialogBuilder.setView(layout);
        dialogBuilder.setTitle("CUSTOM ALERT DIALOG!");
        return dialogBuilder.create();
    }


    public View getCustomDialogLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.dialog, (ViewGroup)findViewById(R.id.wlasny));
    }
}


