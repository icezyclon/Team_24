package at.tugraz.ist.guessingwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustomWordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_words)

        val btn_back = findViewById(R.id.btn_back_CW) as Button

        btn_back.setOnClickListener {
            finish()

        }

    }
}