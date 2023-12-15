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
import androidx.core.app.ActivityCompat
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream


class MainProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainProfileBinding
    private var filePath = File(Environment.getExternalStorageDirectory(), "Downloads/MyData.xls")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.excel.setOnClickListener {
            buttonCreateExcel()
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        filePath = File(Environment.getExternalStorageDirectory(), "Attendance_DATE_.xls")


        addProfile()
        addClass()
        addSubject()
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
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