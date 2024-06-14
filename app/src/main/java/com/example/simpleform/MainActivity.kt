package com.example.simpleform

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private var calendar = Calendar.getInstance()
    private lateinit var datePickerDialog : DatePickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnApply: Button = findViewById(R.id.btnApply)
        val etFirstname: EditText = findViewById(R.id.etFirstname)
        val etLastname: EditText = findViewById(R.id.etLastname)
        val etDate: EditText = findViewById(R.id.etBirth)
        val etCountry: EditText = findViewById(R.id.etCountry)

        btnApply.setOnClickListener {
            val firstname = etFirstname.text.toString()
            val lastname = etLastname.text.toString()
            val date = etDate.text.toString()
            val country = etCountry.text.toString()
            Log.d("MainActivity", "$firstname $lastname, was born on $date, in $country")
        }

        etDate.setOnClickListener {
            showDatePicker(etDate)
        }

    }

    private fun showDatePicker(editText: EditText){
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(this, {_, year, month, day ->
            val selectedDate = "${day}/ ${month+1}/ ${year}"
            editText.setText(selectedDate)
        }, year, month, day)
        datePickerDialog.show()
    }
}