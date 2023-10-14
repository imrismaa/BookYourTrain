package com.example.bookyourtrain

import android.app.DatePickerDialog
import android.content.Intent
import java.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.bookyourtrain.databinding.ActivityFourthBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    companion object {
        const val EXTRA_TRAIN = "extra_train"
        const val EXTRA_DEPARTURE = "extra_departure"
        const val EXTRA_DESTINATION = "extra_destination"
        const val EXTRA_CLASS = "extra_class"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_PAKET = "extra_paket"
        const val EXTRA_USERNAME3 = "extra_username3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var destinationPrice = 0

        with (binding) {
            val username2 = intent.getStringExtra(ThirdActivity.EXTRA_USERNAME2)
            editTxtDate.setOnClickListener {
                showDatePicker(editTxtDate)
            }
            editTxtDate.isFocusable = false

            val stations = resources.getStringArray(R.array.stations)

            val adapterDeparture = ArrayAdapter(this@FourthActivity,
                R.layout.spinner_item, stations)
            spinnerDeparture.adapter = adapterDeparture

            var selectedDeparture = ""
            spinnerDeparture.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedDeparture = stations[position]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            val adapterDestination = ArrayAdapter(this@FourthActivity,
                R.layout.spinner_item, stations)
            spinnerDestination.adapter = adapterDestination

            var selectedDestination = ""
            spinnerDestination.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedDestination = stations[position]
                        if (selectedDeparture == selectedDestination) {
                            Toast.makeText(this@FourthActivity, "Invalid departure and destination!", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            destinationPrice = calculatePrice(selectedDeparture, selectedDestination)
                            price.text = "Rp. $destinationPrice"
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            var classPrice = destinationPrice

            val trains = resources.getStringArray(R.array.train)
            val adapterTrains = ArrayAdapter(this@FourthActivity,
                R.layout.spinner_item, trains)
            spinnerTrain.adapter = adapterTrains

            var selectedTrain = ""
            spinnerTrain.onItemSelectedListener =
                object:AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedTrain = trains[position]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            val classes = resources.getStringArray(R.array.classes)
            val adapterClasses = ArrayAdapter(this@FourthActivity,
                R.layout.spinner_item, classes)
            spinnerClass.adapter = adapterClasses

            var selectedClass = ""
            spinnerClass.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedClass = classes[position]

                        when (selectedClass) {
                            "Economy" -> {
                                classPrice = destinationPrice + 30000
                            }
                            "Business" -> {
                                classPrice = destinationPrice + 80000
                            }
                            "Executive" -> {
                                classPrice = destinationPrice + 150000
                            }
                            "Luxury" -> {
                                classPrice = destinationPrice + 200000
                            }
                        }
                        price.text = "Rp. $classPrice"
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            var currentPrice = classPrice
            var paket = ""
            btnPaketSatu.setOnClickListener {
                currentPrice = classPrice + 20000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_satu)
                choosenPaket.setText(R.string.paket_satu)
            }
            btnPaketDua.setOnClickListener {
                currentPrice = classPrice + 12000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_dua)
                choosenPaket.setText(R.string.paket_dua)
            }
            btnPaketTiga.setOnClickListener {
                currentPrice = classPrice + 10000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_tiga)
                choosenPaket.setText(R.string.paket_tiga)
            }
            btnPaketEmpat.setOnClickListener {
                currentPrice = classPrice + 25000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_empat)
                choosenPaket.setText(R.string.paket_empat)
            }
            btnPaketLima.setOnClickListener {
                currentPrice = classPrice + 15000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_lima)
                choosenPaket.setText(R.string.paket_lima)
            }
            btnPaketEnam.setOnClickListener {
                currentPrice = classPrice + 45000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_enam)
                choosenPaket.setText(R.string.paket_enam)
            }
            btnPaketTujuh.setOnClickListener {
                currentPrice = classPrice + 50000
                price.text = "Rp. $currentPrice"
                paket = getString(R.string.paket_tujuh)
                choosenPaket.setText(R.string.paket_tujuh)
            }

            btnBook.setOnClickListener {
                val date = editTxtDate.text.toString().trim()
                val departure = spinnerDeparture.selectedItem.toString().trim()
                val destination = spinnerDestination.selectedItem.toString().trim()
                val train = spinnerTrain.selectedItem.toString().trim()
                val classTrain = spinnerClass.selectedItem.toString().trim()

                if (date.isEmpty() || departure.isEmpty() || destination.isEmpty() || train.isEmpty() || classTrain.isEmpty()) {
                    Toast.makeText(this@FourthActivity, "Please fill out the blank field", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intentToThirdActivity = Intent(this@FourthActivity, ThirdActivity::class.java)
                    intentToThirdActivity.putExtra(EXTRA_TRAIN, train)
                    intentToThirdActivity.putExtra(EXTRA_CLASS, classTrain)
                    intentToThirdActivity.putExtra(EXTRA_DATE, date)
                    intentToThirdActivity.putExtra(EXTRA_PAKET, paket)
                    intentToThirdActivity.putExtra(EXTRA_DEPARTURE, departure)
                    intentToThirdActivity.putExtra(EXTRA_DESTINATION, destination)
                    intentToThirdActivity.putExtra(EXTRA_USERNAME3, username2)
                    startActivity(intentToThirdActivity)
                }
            }
        }
    }

    private fun showDatePicker(editText: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
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

    private val hargaTiketMap = mapOf(
        "Jakarta (Pasar Senen)-Bandung" to 180000,
        "Jakarta (Pasar Senen)-Yogyakarta (Tugu)" to 280000,
        "Jakarta (Pasar Senen)-Solo (Solo Balapan)" to 285000,
        "Jakarta (Pasar Senen)-Surabaya (Pasar Turi)" to 350000,
        "Bandung (Bandung)-Jakarta (Pasar Senen)" to 180000,
        "Bandung (Bandung)-Yogyakarta (Tugu)" to 250000,
        "Bandung (Bandung)-Solo (Solo Balapan)" to 270000,
        "Bandung (Bandung)-Surabaya (Pasar Turi)" to 280000,
        "Surabaya (Pasar Turi)-Solo (Solo Balapan)" to 180000,
        "Surabaya (Pasar Turi)-Yogyakarta (Tugu)" to 200000,
        "Surabaya (Pasar Turi)-Jakarta (Pasar Senen)" to 350000,
        "Surabaya (Pasar Turi)-Bandung (Bandung)" to 280000,
        "Solo (Solo Balapan)-Surabaya (Pasar Turi)" to 180000,
        "Solo (Solo Balapan)-Yogyakarta (Tugu)" to 30000,
        "Solo (Solo Balapan)-Jakarta (Pasar Senen)" to 285000,
        "Solo (Solo Balapan)-Bandung (Bandung)" to 270000,
        "Yogyakarta (Tugu)-Solo (Solo Balapan)" to 30000,
        "Yogyakarta (Tugu)-Surabaya (Pasar Turi)" to 200000,
        "Yogyakarta (Tugu)-Jakarta (Pasar Senen)" to 280000,
        "Yogyakarta (Tugu)-Bandung (Bandung)" to 250000
    )

    private fun calculatePrice(departure: String, destination: String) : Int {
        val key = "$departure-$destination"
        return hargaTiketMap[key] ?: 0
    }

    override fun onResume() {
        super.onResume()

        binding.editTxtDate.text.clear()
        binding.spinnerDeparture.setSelection(0)
        binding.spinnerDestination.setSelection(0)
        binding.spinnerTrain.setSelection(0)
        binding.spinnerClass.setSelection(0)
        binding.price.text = "Price : "
    }
}