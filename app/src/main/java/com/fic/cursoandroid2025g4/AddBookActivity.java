package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.cursoandroid2025g4.controller.BookController;

public class AddBookActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etAuthor;
    private Button btnSave;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            String status = spinner.getSelectedItem().toString();
            saveBook(title,author,status);
        });

    }

    private void saveBook(String title, String author, String status){
        BookController bookController = new BookController(this);
        boolean result = bookController.addBook(title,author,status);

        if(result){
            Toast.makeText(this, getString(R.string.book_saved_success), Toast.LENGTH_SHORT).show();
            clearForm();
            showBookActivity();
        }else{
            Toast.makeText(this, getString(R.string.error_book_save), Toast.LENGTH_SHORT).show();

        }
    }

    private void showBookActivity(){
        Intent intent =  new Intent(AddBookActivity.this, BookActivity.class);
        startActivity(intent);
    }

    private void clearForm(){
        etTitle.setText("");
        etAuthor.setText("");
        spinner.setSelection(0);
    }

    private void initViews(){
        etAuthor = findViewById(R.id.etAuthor);
        etTitle = findViewById(R.id.etTitle);
        btnSave = findViewById(R.id.btnSave);
        initSpinner();
    }

    private void initSpinner(){
        spinner = findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_book,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



}