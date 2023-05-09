package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddNoteActivity extends AppCompatActivity {


    private EditText editTextNote;
    private RadioGroup radioGroupPriority;
    private RadioButton radioButtonGreen;
    private RadioButton radioButtonYellow;
    private RadioButton radioButtonRed;
    private Button buttonSaveNote;

    private AddNoteViewModel addNoteViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        addNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if(shouldClose){
                    finish();
                }

            }
        });

        initViews();


        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();


            }
        });
    }


    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        radioButtonGreen = findViewById(R.id.radioButtonGreen);
        radioButtonRed = findViewById(R.id.radioButtonRed);
        radioButtonYellow = findViewById(R.id.radioButtonYellow);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);

    }

    private void saveNote() {
        String text = editTextNote.getText().toString().trim();
        int priority = getPriority();

        Note note = new Note(text, priority);
        addNoteViewModel.saveNote(note);



    }


    private int getPriority() {
        int priority;
        if (radioButtonGreen.isChecked()) {
            priority = 0;
        } else if (radioButtonYellow.isChecked()) {
            priority = 1;
        } else priority = 2;

        return priority;

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);

    }


}