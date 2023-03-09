package com.example.translator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.translator.R;
import com.example.translator.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> words;
    public WordAdapter() {
        words = new ArrayList<>();
    }

    public void setWords(List<Word> words) {
        if (this.words.isEmpty()) this.words.clear();
        this.words.addAll(words);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.word_item_view, viewGroup, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        Word word = words.get(i);
        wordViewHolder.bind(word);
    }



    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView englishVersionTextView;
        private TextView russianVersionTextView;


        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            englishVersionTextView = itemView.findViewById(R.id.english_version_text_view);
            russianVersionTextView = itemView.findViewById(R.id.russian_version_text_view);

        }

        public void bind(Word word) {
            englishVersionTextView.setText(word.getEnglishVersion());
            russianVersionTextView.setText(word.getRussianVersion());
        }
    }

}
