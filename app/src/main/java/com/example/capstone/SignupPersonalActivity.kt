package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.capstone.databinding.ActivitySignupPersonalBinding
import com.example.capstone.util.UiUtil

class SignupPersonalActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupPersonalBinding

    val titles: Array<String> = arrayOf(
        "None",
        "Dr.",
        "Prof.",
        "Engr.",
        "Dean",
        "Pres."
    )

    var title : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterItems = ArrayAdapter<String>(this, R.layout.list_item, titles)
        binding.autoCompleteText.setAdapter(adapterItems)

        binding.autoCompleteText.setOnItemClickListener { adapterView, view, position, id ->
            title = adapterView.getItemAtPosition(position).toString()
            if (title == "None") {
                title = ""
            }
        }

        binding.personalContinueButton.setOnClickListener {
            validatePersonalInput()
        }
    }

    fun validatePersonalInput() {
        val firstName = binding.firstNameInput.text.toString()
        val lastName = binding.lastNameInput.text.toString()

        if (lastName.isEmpty()) {
            binding.firstNameInput.setError("Enter a valid name")
            return
        }
        if (lastName.isEmpty()) {
            binding.lastNameInput.setError("Enter a valid name")
            return
        }
        val intent = Intent(this, SignupEmailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("first_name", firstName)
        intent.putExtra("last_name", lastName)
        startActivity(intent)
    }
}