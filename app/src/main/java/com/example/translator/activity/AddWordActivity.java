package com.example.translator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.translator.App;
import com.example.translator.R;
import com.example.translator.db.AppDatabase;
import com.example.translator.entity.Word;

public class AddWordActivity extends AppCompatActivity {
    private EditText wordEditText;
    private EditText translationEditText;
    private Button saveButton;

    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        wordEditText = findViewById(R.id.word_edit_text);
        translationEditText = findViewById(R.id.translation_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordString = wordEditText.getText().toString();
                String translationString = translationEditText.getText().toString();

                Word word = new Word(wordString,translationString);
                saveToDatabase(word);
            }
        });
    }
    private void saveToDatabase(Word word) {
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.getWordDao().insert(word);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                setResult(RESULT_OK);
                finish();
            }
        }.execute();
    }
}