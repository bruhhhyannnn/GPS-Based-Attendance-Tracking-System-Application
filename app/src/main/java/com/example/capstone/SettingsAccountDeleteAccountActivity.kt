package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.capstone.databinding.ActivitySettingsAccountDeleteAccountBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.auth.FirebaseAuth

class SettingsAccountDeleteAccountActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsAccountDeleteAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsAccountDeleteAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirm.setOnClickListener {
            val dialog: AlertDialog = createDialog()
            dialog.show()
        }
    }

    fun createDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Are you sure?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { _, _ ->
            FirebaseAuth.getInstance()
                .currentUser
                ?.delete()
                ?.addOnSuccessListener {
                    UiUtil.showToast(this, "Account Deleted")
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                ?.addOnFailureListener {
                    UiUtil.showToast(this, "Problems occurred")
                }
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        return builder.create()
    }


}