# Dietsnap - Nutrition and Fitness App

Dietsnap is a modern Android application built with Jetpack Compose, focusing on nutrition and fitness tracking. This project was developed as part of an internship assignment.

## Features

- Two main screens: Home Screen and Food Info Screen
- Intuitive UI with circular progress indicators, goal tracking, and explore sections
- Bottom navigation for easy access to different app sections
- Integration with remote API for dynamic data fetching

## Technical Details

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Koin
- **Networking**: Ktor client
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Compose

## API Integration

The app fetches data from two main endpoints:
- Homepage: `http://52.25.229.242:8000/homepage_v3/`
- Food Info: `http://52.25.229.242:8000/food_info/`

## Screens

1. **Home Screen**: Displays user's daily progress, goals, and explore options.
2. **Food Info Screen**: Shows detailed information about specific food items.

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Run the app on an emulator or physical device

## Note

This project was developed as part of an internship application process, showcasing skills in modern Android development practices and UI design.
