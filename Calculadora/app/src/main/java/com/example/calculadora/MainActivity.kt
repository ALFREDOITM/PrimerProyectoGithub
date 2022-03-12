package com.example.calculadora

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonClear.setOnClickListener{
            binding.editTextNumber.text.clear()
        }

        binding.buttonDelete.setOnClickListener {
            binding.editTextNumber.setText(binding.editTextNumber.text.dropLast(1))
        }

        binding.buttonEquals.setOnClickListener {
            when(operator){
                '+' -> {binding.editTextNumber.setText("${firstValue + binding.editTextNumber.text.toString().toDouble()}")}
                '-' -> {binding.editTextNumber.setText("${firstValue - binding.editTextNumber.text.toString().toDouble()}")}
                'x' -> {binding.editTextNumber.setText("${firstValue * binding.editTextNumber.text.toString().toDouble()}")}
                '÷' -> {binding.editTextNumber.setText("${firstValue / binding.editTextNumber.text.toString().toDouble()}")}
                else -> binding.editTextNumber.setText("ERROR")
            }
            operator = '0'
            firstValue = 0.0
        }

        binding.buttonPercentage.setOnClickListener {
            binding.editTextNumber.setText(percentage(operator))
        }
    }

    fun percentage(operatorToUse: Char) : String{
        when(operatorToUse){
            '+' -> {return "${firstValue + (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '-' -> {return "${firstValue - (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '0' -> {return  "${binding.editTextNumber.text.toString().toDouble()/100}"}
            else -> {return "Error"}
        }
    }

    fun getOperator(view: View){
        val button = view as Button
        operator = button.text.get(0)
        firstValue = binding.editTextNumber.text.toString().toDouble()
        binding.editTextNumber.text.clear()
    }

    fun numberButtonClicked(view: View){
        val button = view as Button

        if (button.id.equals(binding.buttonDecimal.id)){
            if (!binding.editTextNumber.text.contains('.')){
                binding.editTextNumber.text.append(button.text)
            }
        }
        else{
            binding.editTextNumber.text.append(button.text)
        }
        //binding.editTextNumber.text.append(button.text)
        /*
        when(button.id){
            binding.buttonDecimal.id -> {
                if (!binding.editTextNumber.text.contains('.')){
                    binding.editTextNumber.text.append(".")
                }
            }
            binding.button0.id -> { binding.editTextNumber.text.append("0")}
            binding.button1.id ->{ binding.editTextNumber.text.append("1")}
            binding.button1.id ->{ binding.editTextNumber.text.append("2")}
            binding.button1.id ->{ binding.editTextNumber.text.append("3")}
            binding.button1.id ->{ binding.editTextNumber.text.append("4")}
            binding.button1.id ->{ binding.editTextNumber.text.append("5")}
            binding.button1.id ->{ binding.editTextNumber.text.append("6")}
            binding.button1.id ->{ binding.editTextNumber.text.append("7")}
            binding.button1.id ->{ binding.editTextNumber.text.append("8")}
            binding.button1.id ->{ binding.editTextNumber.text.append("9")}
            else -> {
                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
            }
        }
        */
    }
}