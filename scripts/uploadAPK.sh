            adb install -t -g ./app/googleCalculator.apk  # APK precisa estar no repositório
            adb shell pm list packages | grep calculator
