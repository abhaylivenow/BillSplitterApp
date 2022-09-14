package com.example.billsplittertest

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var edtTotalAmount: EditText
    private lateinit var edtTipAmount: EditText
    private lateinit var edtTax: EditText
    private lateinit var edtNumberOfPeople: EditText

    private lateinit var txtIndividualAmount: TextView

    private lateinit var btnAmount: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00802b")))

        edtTotalAmount = findViewById(R.id.edt_total_amount)
        edtTipAmount = findViewById(R.id.edt_tip)
        edtTax= findViewById(R.id.edt_tax)
        edtNumberOfPeople = findViewById(R.id.edt_number_of_people)
        btnAmount = findViewById(R.id.btn_get_amount)
        btnReset = findViewById(R.id.btn_reset)
        txtIndividualAmount = findViewById(R.id.individual_amount)

        btnAmount.setOnClickListener {
            verifyData()
            if(verifyData()) {
                txtIndividualAmount.text = "Individual Amount: ${getIndividualAmount()}"
            }
        }

        btnReset.setOnClickListener {
            edtTotalAmount.setText("")
            edtTax.setText("")
            edtTipAmount.setText("")
            edtNumberOfPeople.setText("")
            txtIndividualAmount.text = "Individual Amount: N/A"
        }
    }

    private fun verifyData(): Boolean {
        if(edtTotalAmount.text.isNullOrEmpty()) {
            edtTotalAmount.error = "Cannot be empty"
            return false
        }
        if(edtTipAmount.text.isNullOrEmpty()) {
            edtTipAmount.error = "Cannot be empty"
            return false
        }
        if(edtTax.text.isNullOrEmpty()) {
            edtTax.error = "Cannot be empty"
            return false
        }
        if(edtNumberOfPeople.text.isNullOrEmpty()) {
            edtNumberOfPeople.error = "Cannot be empty"
            return false
        }
        return true
    }

    private fun getIndividualAmount(): Int {
        val totalAmount = edtTotalAmount.text.toString().toInt()
        val tip = edtTipAmount.text.toString().toInt()
        val taxInPercent = edtTax.text.toString().toInt()
        val numberOfPeople = edtNumberOfPeople.text.toString().toInt()

        val taxAmount = (taxInPercent * totalAmount) / 100

        val payableAmount = totalAmount + taxAmount + tip

        return payableAmount / numberOfPeople
    }
}








