#!/bin/sh
rm -rf .idea
./gradlew clean
rm -rf .gradle
rm -rf build
rm -rf */build
rm -rf app/ios/ios.xcworkspace
rm -rf app/ios/Pods
rm -rf app/ios/ios.xcodeproj/project.xcworkspace
rm -rf app/ios/ios.xcodeproj/xcuserdata