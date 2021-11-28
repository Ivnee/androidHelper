package com.geek.infoandroid.android.Level2.les2.CostumeViewTopANDDialogsMinusuem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton alert1 = findViewById(R.id.alert1);
        MaterialButton alert3 = findViewById(R.id.alert3);
        MaterialButton alertList = findViewById(R.id.alert_list);
        MaterialButton alertSingleList = findViewById(R.id.alert_one);
        MaterialButton alertMultiList = findViewById(R.id.alert_many);
        MaterialButton alertView = findViewById(R.id.alert_view);
        alert1.setOnClickListener(alert1Listener);
        alert3.setOnClickListener(alert3Listener);
        alertList.setOnClickListener(alertListListener);
        alertSingleList.setOnClickListener(alertSingleListener);
        alertMultiList.setOnClickListener(alertMulti);
        alertView.setOnClickListener(alertViewListener);
        MaterialButton dialogFragment = findViewById(R.id.dialog_frag);
        MaterialButton bottomDialog = findViewById(R.id.bottom_dialog);
        bottomDialog.setOnClickListener(bottomDialogListener);
        dialogFragment.setOnClickListener(dialogListener);

    }
    private  View.OnClickListener bottomDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BottomDialog bottomDialog = new BottomDialog();
            bottomDialog.show(getSupportFragmentManager(),"asdasd");
        }
    };
    private View.OnClickListener dialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Frag frag = new Frag();
            frag.show(getSupportFragmentManager(),"nulll");
        }
    };
    private View.OnClickListener alert1Listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("мой титул").setMessage("Мое сообщение").setIcon(R.mipmap.ic_launcher).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private View.OnClickListener alert3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("myTitle").setMessage("MyMessage")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"NONO",Toast.LENGTH_SHORT).show();
                }
            }).setNeutralButton("hz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"HZ GDE KDK CHE", Toast.LENGTH_SHORT).show();
                }
            }).setCancelable(false);
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    private View.OnClickListener alertListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            String[] items = {"asd","zel","graves","nyam","soul","key","sesk","dnise"};
            builder.setTitle("title").setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"это же элемент "+which,Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    };
    private int currentItem;
    public View.OnClickListener alertSingleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] items = {"asd","zel","graves","nyam","soul","key","sesk","dnise"};
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("TITLE").setSingleChoiceItems(items, currentItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    currentItem = which;
                    Toast.makeText(getApplicationContext(),"asdfasdf " + which,Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    private View.OnClickListener alertMulti = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final String[] items = {"asd","zel","graves","nyam","soul","key","sesk","dnise"};
            final boolean[] chosen = {false, true, false,true,true,false,false,false};
            builder.setTitle("TITLE EEEE").setMultiChoiceItems(items, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    chosen[which]=isChecked;
                }
            }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i <chosen.length ; i++) {
                        if(chosen[i]){
                            sb.append(items[i]);
                        }
                    }
                    String text = sb.toString();
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    public View.OnClickListener alertViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final View contentView = getLayoutInflater().inflate(R.layout.alerd_fragment,null);
            builder.setTitle("MyTitle").setView(contentView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    };

}