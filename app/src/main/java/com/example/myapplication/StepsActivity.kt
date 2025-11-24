package com.example.accountable

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.accountable.databinding.ActivityStepsBinding

class StepsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStepsBinding
    private var stepsGoal = 10000
    private var streak = 5
    private var completedToday = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI()

        binding.btnChangeGoal.setOnClickListener {
            showGoalDialog()
        }

        binding.btnComplete.setOnClickListener {
            completedToday = !completedToday
            if (completedToday) streak++
            updateUI()
        }
    }

    private fun updateUI() {
        binding.txtGoal.text = "Target: $stepsGoal steps"
        binding.txtStreak.text = "You've hit steps $streak days in a row"

        binding.btnComplete.text =
            if (completedToday) "✓ Goal Hit Today" else "Hit Today's Goal"
    }

    private fun showGoalDialog() {
        val input = android.widget.EditText(this)
        input.hint = "Enter new goal"

        AlertDialog.Builder(this)
            .setTitle("Set Steps Goal")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newGoal = input.text.toString().toIntOrNull()
                if (newGoal != null) stepsGoal = newGoal
                updateUI()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
