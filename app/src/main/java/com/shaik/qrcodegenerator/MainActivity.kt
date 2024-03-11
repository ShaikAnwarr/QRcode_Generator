package com.shaik.qrcodegenerator


import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.esit_text)
        val button = findViewById<Button>(R.id.button)
        val imageView = findViewById<ImageView>(R.id.qr_code)

        // Add TextWatcher to EditText
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    // Set the text color to grey if the EditText is empty
                    editText.setTextColor(Color.GRAY)
                } else {
                    // Set the text color to black if the EditText has text
                    editText.setTextColor(Color.BLACK)
                }
            }
        })

        button.setOnClickListener {
            val multiFormatWriter = MultiFormatWriter()

            try {
                val bitMatrix: BitMatrix = multiFormatWriter.encode(
                    editText.text.toString(),
                    BarcodeFormat.QR_CODE,
                    300,
                    300
                )

                val barcodeEncoder = BarcodeEncoder()
                val bitmap: android.graphics.Bitmap = barcodeEncoder.createBitmap(bitMatrix)

                imageView.setImageBitmap(bitmap)

            } catch (e: WriterException) {
                throw RuntimeException(e)
            }
        }
    }
}
