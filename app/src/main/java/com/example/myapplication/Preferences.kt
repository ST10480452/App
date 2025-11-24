package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    private const val PREFS_NAME = "accountable_prefs"
    private const val KEY_STEPS_GOAL = "steps_goal"
    private const val KEY_STEPS_STREAK = "steps_streak"
    private const val KEY_STEPS_COMPLETED = "steps_completed"

    private const val KEY_CAL_GOAL = "cal_goal"
    private const val KEY_CAL_WEEKLY = "cal_weekly"
    private const val KEY_CAL_STATUS_PREFIX = "cal_status_" // optional per-day

    private const val KEY_STARTING_WEIGHT = "starting_weight"
    private const val KEY_GOAL_WEIGHT = "goal_weight"
    private const val KEY_CURRENT_WEIGHT = "current_weight"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Steps
    var stepsGoal: Int
        get() = prefs.getInt(KEY_STEPS_GOAL, 10000)
        set(v) = prefs.edit().putInt(KEY_STEPS_GOAL, v).apply()

    var stepsStreak: Int
        get() = prefs.getInt(KEY_STEPS_STREAK, 0)
        set(v) = prefs.edit().putInt(KEY_STEPS_STREAK, v).apply()

    var stepsCompletedToday: Boolean
        get() = prefs.getBoolean(KEY_STEPS_COMPLETED, false)
        set(v) = prefs.edit().putBoolean(KEY_STEPS_COMPLETED, v).apply()

    // Calories
    var calorieGoal: Int
        get() = prefs.getInt(KEY_CAL_GOAL, 1400)
        set(v) = prefs.edit().putInt(KEY_CAL_GOAL, v).apply()

    var calorieWeeklyCount: Int
        get() = prefs.getInt(KEY_CAL_WEEKLY, 0)
        set(v) = prefs.edit().putInt(KEY_CAL_WEEKLY, v).apply()

    // Weight
    var startingWeight: Float
        get() = prefs.getFloat(KEY_STARTING_WEIGHT, 85f)
        set(v) = prefs.edit().putFloat(KEY_STARTING_WEIGHT, v).apply()

    var goalWeight: Float
        get() = prefs.getFloat(KEY_GOAL_WEIGHT, 75f)
        set(v) = prefs.edit().putFloat(KEY_GOAL_WEIGHT, v).apply()

    var currentWeight: Float
        get() = prefs.getFloat(KEY_CURRENT_WEIGHT, 83f)
        set(v) = prefs.edit().putFloat(KEY_CURRENT_WEIGHT, v).apply()

    // Generic helpers if needed (per-day statuses)
    fun setBooleanKey(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()
    fun getBooleanKey(key: String, default: Boolean = false) = prefs.getBoolean(key, default)
}
