package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.capstone.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth

class MainSettingsActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.circleManagement.setOnClickListener {
            startActivity(Intent(this, SettingsCircleManagementActivity::class.java))
        }
        binding.appPermission.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.account.setOnClickListener {
            startActivity(Intent(this, SettingsAccountActivity::class.java))
        }
        binding.about.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.logout.setOnClickListener {
            val dialog: AlertDialog = createDialog()
            dialog.show()
        }
    }

    fun createDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Are you sure?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { _, _ ->
            logout()
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        return builder.create()
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}