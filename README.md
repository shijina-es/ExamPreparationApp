📱 About
A fully offline Android quiz application for exam preparation. Create unlimited questions, practice with randomized quizzes, and review your performance - all without any backend server or internet connection.

✨ Features
Core Functionality
✅ Create Questions - Add custom questions with 4 multiple-choice options

✅ Mark Correct Answers - Select the right answer while creating questions

✅ Practice Mode - Take quizzes with no immediate feedback

✅ Shuffle Mode - Questions appear in random order every practice session

✅ Score & Review - View final score and detailed answer review after completion

✅ 100% Offline - No backend, no API, no internet required

✅ Auto-clear Fields - Input fields clear automatically after saving questions

Technical Features
Local data storage using Room Database

Kotlin-based Android application

Material Design UI components

Coroutine-based async operations

No external dependencies or paid services

🛠️ Technology Stack
Language: Kotlin

Database: Room (SQLite)

UI: XML Layouts with Material Components

Architecture: MVVM-like with coroutines

Min SDK: API 24 (Android 7.0)

Target SDK: API 36 (Android 14)

📂 Project Structure
text
app/
├── src/main/
│   ├── java/com/example/exampreparationapp/
│   │   ├── data/
│   │   │   ├── Question.kt          # Question entity
│   │   │   ├── QuestionDao.kt       # Database operations
│   │   │   └── AppDatabase.kt       # Room database instance
│   │   ├── MainActivity.kt           # Home screen
│   │   ├── CreateQuestionActivity.kt # Question creation
│   │   ├── PracticeActivity.kt       # Quiz practice
│   │   └── ResultActivity.kt         # Score & review
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   ├── activity_create_question.xml
│       │   ├── activity_practice.xml
│       │   └── activity_result.xml
│       └── values/
│           └── strings.xml
🚀 Getting Started
Prerequisites
Android Studio (Ladybug or later)

Android SDK 24+

Kotlin plugin

Installation
Clone/Download the project

Open in Android Studio

Sync Gradle dependencies

Build APK:

Go to Build → Build Bundle(s) / APK(s) → Build APK(s)

APK location: app/build/outputs/apk/debug/app-debug.apk

Install on device:

Transfer APK to Android device

Enable "Install from unknown sources"

Install and run

📖 Usage Guide
Creating Questions
Open app and tap "Create Questions"

Enter question text

Fill in options A, B, C, D

Select correct answer using radio buttons

Tap "SAVE QUESTION"

Fields auto-clear for next question

Taking Practice Quiz
From home screen, tap "Start Practice"

Questions load in random order (shuffled)

Read question and select one option

Tap "Next" to proceed

No feedback shown during quiz

After last question, tap "Finish"

Viewing Results
Final score displayed (e.g., "7/10")

Review section shows:

Each question

Your selected answer

Correct answer

Color-coded correct/incorrect indicators

🔧 Configuration
Change App Name
Edit app/res/values/strings.xml:

xml
<string name="app_name">Your App Name</string>
Change App Icon
Right-click res folder → New → Image Asset

Choose Launcher Icons

Select Clip Art and pick an icon

Customize colors

Click Finish

Adjust Minimum Questions
No minimum enforced - works with 1 to 10,000+ questions

📊 Database Schema
Question Table
Column	Type	Description
id	Integer	Primary key (auto-increment)
text	String	Question text
optionA	String	Option A text
optionB	String	Option B text
optionC	String	Option C text
optionD	String	Option D text
correctIndex	Integer	Correct answer (0=A, 1=B, 2=C, 3=D)
⚡ Performance Notes
Handling Large Question Banks
1-100 questions: Instant loading

100-1000 questions: 1-2 second load time

1000-4000 questions: 2-3 second load time with shuffle

4000+ questions: May experience slight lag on practice start

Optimization Tips
Loading screen already implemented

Shuffle happens once at practice start

Navigation between questions is instant

Consider adding question limit selector for very large banks

