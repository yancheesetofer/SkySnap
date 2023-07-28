# SkySnap

The SkySnap is a simple Android application that allows users to check the current location's weather information and other chosen cities. The app uses the OpenWeatherMap API to fetch weather data and displays it in a user-friendly interface (one touch away).

## Features

- Display the current temperature, humidity, pressure, clouds, and wind speed.
- Show a weather icon representing the current weather condition.
- Update the weather information using GPS location (requires GPS permission).
- Cache the weather data locally for offline access.
- Last updated time displayed on the screen.

## Previews

### Normal Flow

![Skysnap demo 2](demo0.gif)

### No internet (caching)

![Skysnap demo 1](demo1.gif)

## Getting Started

### Prerequisites

- Android Studio

### Installation

1. Clone the repository to your local machine using:

   `git clone https://github.com/yancheesetofer/SkySnap`

2. Open Android Studio and select "Open an existing Android Studio project."
3. Navigate to the cloned directory and select the "SkySnap" folder.

### API Key

The app requires an API key from OpenWeatherMap to fetch weather data. Follow these steps to add your API key:

1. Sign up for a free account at [OpenWeatherMap](https://openweathermap.org/) and obtain an API key.
2. Replace `AppId` with your actual API key.
   AppId=YOUR_OPENWEATHERMAP_API_KEY

### Running the App

1. Connect an Android device to your computer or use a virtual device in Android Studio.
2. Click on the "Run" button in Android Studio, or use the shortcut `Shift + F10`, to install and run the app on your device.

## Testing

Testing is being developed yet.

## Contributing

We welcome contributions to the Sky Snap app. If you find any issues or have new feature suggestions, please feel free to create an issue or submit a pull request.

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/my-new-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/my-new-feature`).
5. Create a new Pull Request.

## License

The Weather App is open-source software licensed under the [MIT License](LICENSE).

## Acknowledgments

- Special thanks to Pawneshwer Gupta for the API and Cache tutorial https://learnpainless.com/fetch-data-from-api-and-store-in-cache/
- Big thanks to Khammaci Yakout for the basecode.

---
