#!/bin/bash
# Auto-generated script to build and run the Foodomer Android Application

# Setup environment variables
export JAVA_HOME=/home/kynguyen/.jdks/jdk-17.0.11+9
export ANDROID_HOME=/home/kynguyen/android-sdk
export PATH=$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools:$PATH

echo "----------------------------------------"
echo "1. Building the debug APK..."
echo "----------------------------------------"
./gradlew assembleDebug --no-daemon

if [ $? -ne 0 ]; then
    echo "Build failed! Exiting."
    exit 1
fi

echo "----------------------------------------"
echo "2. Checking for running emulators..."
echo "----------------------------------------"
# Find if an emulator is online or offline
EMULATOR_FOUND=$(adb devices | grep "emulator")

if [ -z "$EMULATOR_FOUND" ]; then
    echo "Starting Android emulator (test_avd)..."
    emulator -avd test_avd -no-audio -no-snapshot -no-boot-anim > /dev/null 2>&1 &
    
    echo "Waiting for emulator connection..."
    adb wait-for-device
    
    echo "Waiting for Android system to boot..."
    while [ "$(adb shell getprop sys.boot_completed | tr -d '\r')" != "1" ]; do
        sleep 2
    done
    echo "Emulator is ready!"
else
    echo "Emulator is already running."
fi

echo "----------------------------------------"
echo "3. Installing the APK..."
echo "----------------------------------------"
adb install app/build/outputs/apk/debug/app-debug.apk

if [ $? -ne 0 ]; then
    echo "Installation failed!"
    exit 1
fi

echo "----------------------------------------"
echo "4. Launching the Foodomer application..."
echo "----------------------------------------"
adb shell am start -n com.example.foodomer/com.example.foodomer.MainActivity

echo "----------------------------------------"
echo "App is running successfully!"
echo "----------------------------------------"
