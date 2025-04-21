      echo "Instalando APK no emulador 5554..."
      adb -s emulator-5554 install -t -g ./app/googleCalculator.apk
      
      echo "Instalando APK no emulador 5556..."
      #adb -s emulator-5556 install -t -g ./app/googleCalculator.apk

      # 3. Verificação da instalação
      echo "Verificando pacotes no emulador 5554:"
      adb -s emulator-5554 shell pm list packages | grep calculator || echo "Pacote não encontrado no emulador 5554"
      
      #echo "Verificando pacotes no emulador 5556:"
      #adb -s emulator-5556 shell pm list packages | grep calculator || echo "Pacote não encontrado no emulador 5556
