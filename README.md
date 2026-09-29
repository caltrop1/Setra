# Setra

Setra is a production-quality, offline-first Kotlin Android workout and fitness tracking application built with Jetpack Compose and Material 3.

---

## Current Phase

**Phase 1 — Foundation & App Shell**

Phase 1 establishes the architecture, navigation infrastructure, data persistence, and centralized design system for Setra. It provides a polished shell with zero unnecessary bloat, ready for future feature phases.

---

## Technical Specifications & Stack

- **Language:** Kotlin 2.2.10
- **UI Framework:** Jetpack Compose with Material 3
- **Navigation:** Navigation Compose 2.10.2 (type-safe routes with Kotlinx Serialization)
- **Local Database:** Room 2.8.5 with KSP 2.3.12
- **Preferences:** DataStore Preferences 1.2.1
- **State Management:** Coroutines, StateFlow, ViewModel (`collectAsStateWithLifecycle`)
- **Build System:** Gradle Kotlin DSL with Version Catalog (`libs.versions.toml`)
- **Target SDK:** 37 (Android 16) | **Min SDK:** 24 (Android 7.0)

---

## Architecture

Setra follows a clean, pragmatic layered architecture with explicit dependency flow:

```
Presentation / UI (Composables, ViewModels, Themes)
         ↓
Domain Layer (Models, Preferences)
         ↓
Data Layer (Room Database, DataStore Preferences, Repositories)
```

### Package Hierarchy

```
com.sami.setra
│
├── data
│   ├── local
│   │   ├── database
│   │   │   ├── SetraDatabase.kt
│   │   │   ├── dao/
│   │   │   └── entity/
│   │   └── preferences
│   │       ├── UserPreferences.kt
│   │       └── UserPreferencesRepository.kt
│   └── repository/
│
├── domain
│   ├── model/
│   └── repository/
│
├── navigation
│   ├── Screen.kt
│   └── SetraNavHost.kt
│
├── ui
│   ├── components
│   │   ├── ScreenPlaceholder.kt
│   │   ├── SetraBottomNavigation.kt
│   │   ├── SetraButtons.kt
│   │   ├── SetraCard.kt
│   │   ├── SetraGlassSurface.kt
│   │   ├── SetraScaffold.kt
│   │   ├── SetraSectionHeader.kt
│   │   └── SetraTopBar.kt
│   ├── screens
│   │   ├── create/CreateScreen.kt
│   │   ├── profile/ProfileScreen.kt
│   │   ├── progress/ProgressScreen.kt
│   │   ├── routines/RoutinesScreen.kt
│   │   └── splits/SplitsScreen.kt
│   └── theme
│       ├── Color.kt
│       ├── Dimens.kt
│       ├── Motion.kt
│       ├── Shape.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── MainActivity.kt
└── MainViewModel.kt
```

---

## Features & Highlights (Phase 1)

1. **Centralized Design System:**
   - Dark Theme (very dark green background `#080C0A` with vibrant gym green `#22C55E` accents).
   - Light Theme (clean off-white background `#F8FAFC` with modern blue `#2563EB` accents).
   - Strategic "Liquid Glass" translucent surfaces with subtle borders (`SetraGlassSurface`).
   - Centralized typography, spacing, motion durations, and shapes.

2. **App Shell & Navigation:**
   - Bottom navigation bar with 5 destinations:
     - **Splits** (default launch destination)
     - **My Routines**
     - **Create**
     - **Progress**
     - **Profile**
   - Preserves backstack state during navigation without screen recreation.

3. **DataStore Preferences:**
   - Instant theme mode switching (System Default, Light, Dark).
   - Unit selection preferences (Weight: KG/LBS, Distance: KM/MILES).

4. **Room Database Foundation:**
   - `SetraDatabase` configured with Room KSP for future entity persistence.
   - Initialized at launch via `MainViewModel`.

5. **Accessibility Foundations:**
   - Touch targets >= 48dp across interactive elements.
   - High-contrast color palette for nighttime and outdoor gym use.
   - Semantic headings and clear content descriptions.

---

## Development Principles

- **Offline-First:** No network requirement for core functionality.
- **No Friction:** No mandatory onboarding, no paywalls, no forced logins.
- **Incremental & Modular:** Scalable codebase built for maintainability.

---

## How to Build & Run

### Build Application
```bash
./gradlew :app:assembleDebug
```

### Run Unit Tests
```bash
./gradlew :app:testDebugUnitTest
```

### Physical Device Debugging
Deploy through Android Studio wireless/USB debugging:
```bash
./gradlew :app:installDebug
```
