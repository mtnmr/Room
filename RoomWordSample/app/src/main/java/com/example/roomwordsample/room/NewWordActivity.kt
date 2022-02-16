package com.example.roomwordsample.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.roomwordsample.R

class NewWordActivity : AppCompatActivity() {

    private lateinit var editTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editTextView = findViewById(R.id.edit_word)
        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTextView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val word = editTextView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }


    //固定のキーを作成してくれるぽい？hash的な
    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}