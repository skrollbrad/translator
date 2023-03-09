package com.example.translator.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.translator.App;
import com.example.translator.R;
import com.example.translator.adapter.WordAdapter;
import com.example.translator.db.AppDatabase;
import com.example.translator.entity.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class WordsActivity extends AppCompatActivity {
    private static final int ADD_WORDS_REQUEST_CODE = 1001;
    private FloatingActionButton addbutton;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;

    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addbutton = findViewById(R.id.add_button);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordsActivity.this,AddWordActivity.class);
                startActivityForResult(intent,ADD_WORDS_REQUEST_CODE );
            }
        });
        recyclerView = findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        wordAdapter = new WordAdapter();
        recyclerView.setAdapter(wordAdapter);
        loadWords();



    }

    private void loadWords() {

        new AsyncTask<Void, Void, List<Word>>() {
            @Override
            protected List<Word> doInBackground(Void... voids) {
                return appDatabase.getWordDao().getWords();
            }

            @Override
            protected void onPostExecute(List<Word> words) {
                wordAdapter.setWords(words);
            }
        }.execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WORDS_REQUEST_CODE && resultCode == RESULT_OK) {
            loadWords();
        }
    }
}
