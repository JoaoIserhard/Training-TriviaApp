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

| Dark Mode | Light Mode |
| :---: | :---: |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/a2bc4649-928c-4fd5-96d2-dc265b7339d9" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/11e780a2-d206-4381-861f-accd7fbd490c" /> |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/773b35fd-f59d-4ed1-8284-6339a2bc6fc5" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/8e91c1b6-83c2-45b2-91ad-d2bd18feafc0" /> |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/755b8586-f772-43e5-8731-1d1371010fd2" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/a1c4f339-1e56-437c-9890-ca3b1cd8dfa2" /> |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/138e2fa3-7d9e-4044-a909-b94a3678b875" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/c750f428-8ae5-4c22-9ab2-ae9a4173a4d4" /> |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/199cf69f-0aca-4d81-95ce-90feeb566a72" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/4e79f1a4-43a4-4156-8fff-8e4d121b1379" /> |

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
