package com.example.expensenote_30

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //สร้างตัวแปรสำหรับหน้า UI Elements
    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnClear: Button
    private lateinit var btnAdd: Button
    private lateinit var tvRecentItem: TextView
    private lateinit var tvTotal: TextView

    //สร้างตัวแปรสำหรับเก็บยอดรวม
    private var totalAmount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // เชื่อมโยง View กับตัวแปร
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnClear = findViewById<Button>(R.id.btnClear)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        tvRecentItem = findViewById<TextView>(R.id.tvRecentItem)
        tvTotal = findViewById<TextView>(R.id.tvTotal)



        // ปุ่มเพิ่มรายการ
        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            //ตรวจสอบเงื่อนไขว่า ผู้ใช้งานได้กรอกจำนวนเงินมาหรือไม่
            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //ตรวสอบว่าผู้ใช้งานได้กรอกจำนวนเงินเป็นตัวเลขหรือไม่
            val amount = amountText.toDoubleOrNull()
            if (amount == null ) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินให้ถูกต้อง", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ทำการเพิ่มรายการและยอดรวม
            totalAmount += amount


            val showNote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"

            tvRecentItem.text = "รายการล่าสุด: $showNote - %.2f บาท".format(amount)

            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(totalAmount)

            etAmount.text.clear()
            etNote.text.clear()


        }
        // ปุ่มเคลียร์
        btnClear.setOnClickListener {
            totalAmount = 0.0
            etAmount.text.clear()
            etNote.text.clear()
            tvRecentItem.text = "รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"


        }


    }
}
