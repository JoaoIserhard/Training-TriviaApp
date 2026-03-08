# Training - TriviaApp

A dynamic Android trivia application built with Jetpack Compose that challenges users with a variety of questions fetched from a remote API. This project demonstrates modern Android development practices, including asynchronous data fetching, dependency injection, and polished UI animations.

## Features

* **Dynamic Question Loading**: Fetches a large dataset of trivia questions from a remote JSON endpoint using Retrofit.
* **Interactive Quiz Interface**:
    * **Real-time Feedback**: Instant visual feedback (Green/Red) upon selecting an answer.
    * **Smart Navigation**: The "Next" button is conditionally enabled only when the correct answer is selected, ensuring a learning-focused experience.
* **Progress Tracking**: 
    * **Visual Progress Bar**: A custom-designed Material 3 progress bar showing completion percentage with smooth gradients.
    * **Question Tracker**: Keeps users informed of their current position within the quiz.
* **Robust State Management**: Handles loading, success, and error states gracefully using a `DataOrException` wrapper and ViewModels.
* **Dependency Injection**: Fully powered by Hilt for clean architecture and testability.

## Tech Stack

* **Language**: Kotlin
* **UI Framework**: Jetpack Compose
* **Dependency Injection**: Hilt
* **Networking**: Retrofit & GSON
* **Asynchronous Programming**: Kotlin Coroutines & Flow
* **Theme**: Material 3 (M3)

## Project Structure

The project follows a clean, layered architecture:

```text
com.example.triviaapp
├── component         # Reusable UI components (Questions, Progress Bars)
├── data              # Data wrappers (DataOrException)
├── di                # Hilt Dependency Injection modules
├── model             # Data classes for API mapping (Question, QuestionItem)
├── network           # Retrofit API interface definitions
├── repository        # Data source abstraction layer
├── screens           # Screen-level composables and ViewModels
├── util              # Utility classes and constants
└── MainActivity.kt   # Entry point and Theme initialization
```

## Getting Started

### Prerequisites
* Android Studio Ladybug (2024.2.1) or newer.
* JDK 17+.
* Android SDK Level 34+.

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/triviaapp.git
   ```
2. **Open in Android Studio**:
   Select "Open" and navigate to the project folder.
3. **Sync Gradle**:
   Wait for the project to download dependencies (Hilt, Retrofit, Compose).
4. **Run**:
   Click the **Run** button or press `Shift + F10` to launch on an emulator or physical device.

## Key Components

### Networking with Retrofit
The app uses a singleton `QuestionApi` to fetch quiz data. The repository layer abstracts this call, returning a `DataOrException` object that helps the UI react to loading states or network failures.

### Custom UI Components
* **ShowProgress**: A custom-drawn progress bar using Compose `Canvas` and `Brush` for a modern, gradient-filled aesthetic.
* **QuestionDisplay**: Manages the local state of selected answers and provides immediate validation logic.

## Training & Credits
This project was developed as part of the learning journey in the **[Jetpack Compose Masterclass](https://www.udemy.com/course/kotling-android-jetpack-compose-/)** on Udemy.
