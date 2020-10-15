package com.example.languagepreference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    void language(String language)
    {
        sharedPreferences.edit().putString("language", language).apply();
        textView.setText(language);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         switch(item.getItemId())
         {
             case R.id.english:
                 Log.i("Item selected" , "english");
                 language("english");
                 return true;
             case R.id.spanish:
                 Log.i("Item selected", "spanish");
                 language("spanish");
                 return true;
             default:
                 return false;
         }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.languagepreference", Context.MODE_PRIVATE);
        String language= sharedPreferences.getString("language", "error");
        if(language.equals("error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert).
                    setTitle("Which language you want to choose?")
                    .setPositiveButton("english", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            language("English");
                            Toast.makeText(MainActivity.this, "Lang has been selected to english", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            language("spanish");
                            Toast.makeText(MainActivity.this, "Lang has been selected to spanish", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }
        else{
            textView.setText(language);
        }
    }
}
