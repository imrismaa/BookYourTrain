package com.example.bookyourtrain

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookyourtrain.databinding.ActivityThirdBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private var dates = ArrayList<String>()
    companion object{
        const val EXTRA_USERNAME2 = "extra_username2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val train = intent.getStringExtra(FourthActivity.EXTRA_TRAIN)
        val paket = intent.getStringExtra(FourthActivity.EXTRA_PAKET)
        val date = intent.getStringExtra(FourthActivity.EXTRA_DATE)
        dates.add(date.toString())
        val classTrain = intent.getStringExtra(FourthActivity.EXTRA_CLASS)
        val departure = intent.getStringExtra(FourthActivity.EXTRA_DEPARTURE)
        val destination = intent.getStringExtra(FourthActivity.EXTRA_DESTINATION)
        val username = intent.getStringExtra(SecondActivity.EXTRA_USERNAMELOGIN)
        binding.txtWelcome.text = "Welcome, imrismaa!"

        with(binding){
            txtDeparture.text = "Departure\n$departure"
            txtDestination.text = "Destination\n$destination"
            txtWelcome.text = "Welcome, imrismaa!"
            txtDate.text = "$date"
            txtTrain.text = "$train $classTrain"
            txtPaket.text = "$paket"

            if (departure == null || destination == null || date == null || train == null || paket == null || classTrain == null) {
                card1.visibility = android.view.View.GONE
            }
            else {
                card1.visibility = android.view.View.VISIBLE
            }

            btnShowCalendar.setOnClickListener {
                showDatePicker()
            }

            btnBookForm.setOnClickListener {
                val intentToFourthActivity = Intent(this@ThirdActivity, FourthActivity::class.java)
                intentToFourthActivity.putExtra(EXTRA_USERNAME2, username)
                startActivity(intentToFourthActivity)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, {
                view, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDateFormatted = dateFormat.format(selectedDate.time)

            if(dates.contains(selectedDateFormatted)) {
                Toast.makeText(this@ThirdActivity, "You have a trip on this day!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@ThirdActivity, "You don't have any trip on this day! Wanna book some?", Toast.LENGTH_SHORT).show()
            }
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }

    override fun onResume() {
        super.onResume()
        binding.txtWelcome.text = "Welcome, imrismaa!"
    }
}