package com.example.ap2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)
        val age = intent.getIntExtra("age", 0)
        val gender = intent.getStringExtra("gender") ?: ""

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val backButton: Button = findViewById(R.id.backButton)

        val imc = calculateIMC(weight, height, age, gender)
        val result = interpretIMC(imc, age, gender)

        resultTextView.text = "Seu IMC é $imc\n$result"

        backButton.setOnClickListener {
            finish()
        }
    }

    // Método para calcular o IMC
    private fun calculateIMC(weight: Double, height: Double, age: Int, gender: String): Double {
        return weight / (height * height)
    }

    // Lógica para obter o resultado do IMC
    private fun interpretIMC(imc: Double, age: Int, gender: String): String {
        return if (age in 1..65) {
            when {
                imc < 16 -> "Baixo peso muito grave"
                imc in 16.0..16.999 -> "Baixo peso grave"
                imc in 17.0..18.499 -> "Baixo peso"
                imc in 18.5..24.999 -> "Peso normal"
                imc in 25.0..29.999 -> "Sobrepeso"
                imc in 30.0..34.999 -> "Obesidade grau 1"
                imc in 35.0..39.999 -> "Obesidade grau 2"
                imc >= 40 -> "Obesidade grau 3 (Obesidade mórbida)"
                else -> ""
            }
        } else if (age > 65) {
            when {
                gender == "masculino" -> when {
                    imc < 21.9 -> "Baixo peso"
                    imc in 22.0..27.0 -> "Peso normal"
                    imc in 27.1..30.0 -> "Sobrepeso"
                    imc in 30.1..35.0 -> "Obesidade grau 1"
                    imc in 35.1..39.9 -> "Obesidade grau 2"
                    imc >= 40 -> "Obesidade grau 3 (Obesidade mórbida)"
                    else -> ""
                }
                gender == "feminino" -> when {
                    imc < 21.9 -> "Baixo peso"
                    imc in 22.0..27.0 -> "Peso normal"
                    imc in 27.1..32.0 -> "Sobrepeso"
                    imc in 32.1..37.0 -> "Obesidade grau 1"
                    imc in 37.1..41.99 -> "Obesidade grau 2"
                    imc >= 42 -> "Obesidade grau 3 (Obesidade mórbida)"
                    else -> ""
                }
                else -> ""
            }
        } else {
            "Dados inválidos"
        }
    }

}
