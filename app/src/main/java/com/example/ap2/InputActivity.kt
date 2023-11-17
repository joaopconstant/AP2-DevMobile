package com.example.ap2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val ageEditText: EditText = findViewById(R.id.ageEditText)
        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)

        // Configurar o adaptador do Spinner
        val genderAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        genderSpinner.adapter = genderAdapter

        calculateButton.setOnClickListener {
            // Obter os valores dos campos quando o botão for clicado
            val height = heightEditText.text.toString().toDouble()
            val weight = weightEditText.text.toString().toDouble()
            val age = ageEditText.text.toString().toInt()
            val gender = genderSpinner.selectedItem.toString()

            // Criar a Intent e passar os dados para a próxima Activity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            startActivity(intent)
        }
    }
}

