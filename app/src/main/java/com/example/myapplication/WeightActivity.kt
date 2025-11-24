package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityWeightBinding

class WeightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load from prefs:
        updateUIFromPrefs()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnUpdateWeight.setOnClickListener { showUpdateDialog() }
    }

    private fun updateUIFromPrefs() {
        val start = Preferences.startingWeight
        val goal = Preferences.goalWeight
        val current = Preferences.currentWeight
        val lost = start - current

        binding.txtStarting.text = "Starting weight: ${start}kg"
        binding.txtGoal.text = "Goal weight: ${goal}kg"
        binding.txtCurrent.text = "Current weight: ${current}kg"
        binding.txtProgress.text = "Progress: -${"%.1f".format(lost)}kg since start"
        binding.txtProgress.setTextColor(
            if (lost > 0) ContextCompat.getColor(this, R.color.white) 
            else ContextCompat.getColor(this, R.color.gray_medium)
        )
    }

    private fun showUpdateDialog() {
        val input = android.widget.EditText(this)
        input.hint = "Enter current weight (kg)"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL

        AlertDialog.Builder(this)
            .setTitle("Weekly Check-in")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val v = input.text.toString().toFloatOrNull()
                if (v != null) {
                    Preferences.currentWeight = v
                }
                updateUIFromPrefs()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
