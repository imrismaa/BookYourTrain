package com.example.bookyourtrain

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookyourtrain.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_PASSWORD = "extra_password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            editTxtDateOfBirth.setOnClickListener {
                showDatePickerDialog(editTxtDateOfBirth)
            }

            editTxtDateOfBirth.isFocusable = false

            btnRegister.setOnClickListener {
                val email = editTxtEmail.text.toString().trim()
                val username = editTxtUsername.text.toString().trim()
                val password = editTxtPassword.text.toString().trim()
                val dateOfBirthText = editTxtDateOfBirth.text.toString().trim()

                if (email.isEmpty() || email.isEmpty() || password.isEmpty() || dateOfBirthText.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Please fill out the blank field!", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (dateOfBirthText.isNotEmpty()) {
                        val age = Calendar.getInstance().get(Calendar.YEAR) - dateOfBirthText.split("/")[2].toInt()
                        val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)
                        if (age >= 15) {
                            intentToSecondActivity.putExtra(EXTRA_USERNAME, username)
                            intentToSecondActivity.putExtra(EXTRA_PASSWORD, password)
                            startActivity(intentToSecondActivity)
                        }
                        else {
                            Toast.makeText(this@MainActivity, "You must at least 15 years old", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR) - 15
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = dateFormat.format(selectedDate.time)
                editText.setText(date)
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    override fun onResume() {
        super.onResume()

        binding.editTxtEmail.text.clear()
        binding.editTxtUsername.text.clear()
        binding.editTxtPassword.text.clear()
        binding.editTxtDateOfBirth.text.clear()
    }
}