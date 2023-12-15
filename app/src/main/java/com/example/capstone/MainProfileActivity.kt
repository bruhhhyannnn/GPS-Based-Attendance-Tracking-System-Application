package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainProfileBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream


class MainProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainProfileBinding
    private val REQUEST_PERMISSION_CODE = 101
    private lateinit var filePath: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkPermissions()) {
            setupFilePath()
        } else {
            requestPermissions()
        }


        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        filePath = File(Environment.getExternalStorageDirectory(), "Demo.xls")

        binding.excel.setOnClickListener {
            buttonCreateExcel()
        }

        addProfile()
        addClass()
        addSubject()
    }

    private fun checkPermissions(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            REQUEST_PERMISSION_CODE
        )
    }

    private fun setupFilePath() {
        filePath = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "MyData.xls"
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupFilePath()
            } else {
                // Handle permission denial
            }
        }
    }

    fun buttonCreateExcel() {
        val hssfWorkbook = HSSFWorkbook()
        val hssfSheet: HSSFSheet = hssfWorkbook.createSheet("Custom Sheet")

        val hssfRow: HSSFRow = hssfSheet.createRow(0)
        val hssfCell: HSSFCell = hssfRow.createCell(0)

        hssfCell.setCellValue("hello")

        try {
            if (!filePath.exists()) {
                filePath.createNewFile()
            }

            val fileOutputStream = FileOutputStream(filePath)
            hssfWorkbook.write(fileOutputStream)

            fileOutputStream.apply {
                flush()
                close()
            }

            showToast("Excel file created successfully")

        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error creating Excel file")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
//                createExcel(totalPresentValue) // Pass the total present value to createExcel
                binding.present.text = totalPresentValue.toString()
            }
            .addOnFailureListener { exception ->
                UiUtil.showToast(this, "Failed to get present value: ${exception.message}")
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