package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainProfileBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class MainProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.excel.setOnClickListener {
            getPresentValue()
        }

        addProfile()
        addClass()
        addSubject()
    }

    fun getPresentValue() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        var totalPresentValue = 0 // Initialize the total present value

        Firebase.firestore.collection("student")
            .whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val presentValue = document.getLong("present")?.toInt() ?: 0
                    totalPresentValue += presentValue // Accumulate present values
                }
                createExcel(totalPresentValue) // Pass the total present value to createExcel
                binding.present.text = totalPresentValue.toString()
            }
            .addOnFailureListener { exception ->
                UiUtil.showToast(this, "Failed to get present value: ${exception.message}")
            }
    }

    private fun createExcel(totalPresentValue: Int) {
        val database = FirebaseDatabase.getInstance().getReference("Student")
        database.get().addOnSuccessListener { dataSnapshot ->
            // Create a fresh workbook and sheet
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Attendance Data")

            // Create the header row
            val headerRow = sheet.createRow(0)
            val headers = arrayOf("Student Number", "Course", "Last Name", "First Name", "Number of Days Present", "Number of Days Absent", "Number of Days Excused")
            for (i in headers.indices) {
                val cell = headerRow.createCell(i)
                cell.setCellValue(headers[i])
            }

            // Insert Data From Firebase nukwa
            var rowNum = 1
            for (studentSnapshot in dataSnapshot.children) {
                val row = sheet.createRow(rowNum++)

                val studentNum = studentSnapshot.child("studentNum").value.toString()
                val course = studentSnapshot.child("course").value.toString()
                val lastName = studentSnapshot.child("lastName").value.toString()
                val firstName = studentSnapshot.child("firstName").value.toString()
                val numberOfPresentDays = studentSnapshot.child("numberOfPresentDays").value.toString()
                val numberOfAbsentDays = studentSnapshot.child("numberOfAbsentDays").value.toString()
                val numberOfExcusedDays = studentSnapshot.child("numberOfExcusedDays").value.toString()

                val values = arrayOf(studentNum, course, lastName, firstName, numberOfPresentDays, numberOfAbsentDays, numberOfExcusedDays)
                for (i in values.indices) {
                    val cell = row.createCell(i)
                    cell.setCellValue(values[i])
                }
            }

            // Write the WorkBook
            val file = File(getExternalFilesDir(null), "StudentData.xlsx")
            val outputStream = FileOutputStream(file)
            workbook.write(outputStream)
            workbook.close()
            outputStream.close()

            Toast.makeText(this, "Data exported to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        }
    }

    fun addProfile() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid;
        Firebase.firestore.collection("user")
            .document(userId)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val title = it.data?.get("title")?.toString()
                    val firstName = it.data?.get("firstName")?.toString()
                    val lastName = it.data?.get("lastName")?.toString()
                    binding.name.text = title + " " +  firstName + " " + lastName
                }
            }
            .addOnFailureListener {
                UiUtil.showToast(this, "Failed to get name")
                binding.name.text = "default_name"
            }
    }

    fun addClass() {

    }

    fun addSubject() {

    }
}