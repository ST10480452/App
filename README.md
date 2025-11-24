# Accountable - Minimalist Fitness Tracker

A clean, minimalist black and white fitness tracking app for Android.

## Features

### 📊 **Steps Tracking**
- Set daily step goals
- Track your streak
- Mark goals as completed daily

### 🍽️ **Calorie Tracking**
- Set daily calorie targets
- Track weekly performance (days on target)
- Simple logging: stayed within or went over

### 💪 **Workout Plans**
- Pre-built bodyweight workouts
- Four muscle groups: Chest, Back, Legs, Arms
- No equipment needed
- Mark workouts as completed

### ⚖️ **Weight Tracking**
- Set starting and goal weight
- Weekly check-ins
- Track progress over time

## Design Philosophy

**Minimalist & Smart:**
- Pure black and white color scheme
- Clean typography with plenty of whitespace
- Intuitive navigation
- No distractions, just what you need

## Technical Details

- **Language:** Kotlin
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 35 (Android 15)
- **Architecture:** Single Activity per feature
- **Data Persistence:** SharedPreferences
- **UI:** Material Design 3 with ViewBinding

## Building the App

1. Open the project in Android Studio
2. Sync Gradle files
3. Run the app on an emulator or device

## App Structure

```
app/
├── MainActivity.kt          # Home screen with 4 main options
├── StepsActivity.kt         # Steps tracking
├── CaloriesActivity.kt      # Calorie tracking
├── WorkoutActivity.kt       # Workout plans
├── WeightActivity.kt        # Weight tracking
└── Preferences.kt           # Shared data persistence
```

## Color Palette

- Background: `#000000` (Pure Black)
- Text: `#FFFFFF` (Pure White)
- Secondary Text: `#888888` (Medium Gray)
- Buttons: White with black text
- Dividers: `#333333` (Dark Gray)

## Data Persistence

All user data is stored locally using SharedPreferences:
- Step goals and streaks
- Calorie goals and weekly counts
- Weight measurements (starting, current, goal)
- Completion status

## Future Enhancements

- Add charts and visualizations
- Weekly/monthly reports
- Export data
- Custom workout creation
- Reminder notifications

---

Built with ❤️ for a cleaner, simpler fitness tracking experience.
