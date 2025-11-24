package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutBinding

    private val workouts = mapOf(
        "Chest" to listOf(
            "Push-ups: 3x15",
            "Wide push-ups: 3x12",
            "Diamond push-ups: 3x10",
            "Plank: 3 x 45s"
        ),
        "Back" to listOf(
            "Superman: 3x15",
            "Reverse snow angels: 3x12",
            "Prone arm raises: 3x15",
            "Plank to downward dog: 3x10"
        ),
        "Legs" to listOf(
            "Squats: 3x20",
            "Lunges: 3x15 per leg",
            "Wall sit: 3 x 45s",
            "Calf raises: 3x25"
        ),
        "Arms" to listOf(
            "Tricep dips (chair): 3x12",
            "Arm circles: 3x20",
            "Plank taps: 3x20",
            "Diamond push-ups: 3x10"
        )
    )

    private var currentGroup = "Chest"
    private var completed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // default
        showGroup(currentGroup)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnChest.setOnClickListener { showGroup("Chest") }
        binding.btnBackMuscle.setOnClickListener { showGroup("Back") }
        binding.btnLegs.setOnClickListener { showGroup("Legs") }
        binding.btnArms.setOnClickListener { showGroup("Arms") }

        binding.btnMarkWorkout.setOnClickListener {
            completed = !completed
            if (completed) {
                // optional: you can track number of workouts in prefs
            }
            binding.btnMarkWorkout.text = if (completed) "✓ Completed" else "Mark as completed"
        }
    }

    private fun showGroup(group: String) {
        currentGroup = group
        val text = workouts[group]?.joinToString(separator = "\n\n") { "• $it" } ?: ""
        binding.txtExercises.text = "$group\n\n$text"
        binding.btnMarkWorkout.text = if (completed) "✓ Completed" else "Mark as completed"
    }
}
