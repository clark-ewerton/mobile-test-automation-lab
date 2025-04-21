#!/bin/bash
set -e

echo "Criando AVD para Emulator 2..."
avdmanager create avd --force -n test2 --abi 'google_apis/x86_64' --package 'system-images;android-28;google_apis;x86_64' --device 'Nexus 5'

echo "Iniciando Emulator 2 na porta 5556..."
nohup /usr/local/lib/android/sdk/emulator/emulator -port 5556 -avd test2 -no-window -gpu swiftshader_indirect -no-snapshot -noaudio -no-boot-anim &

# 3. Espera pelo boot completo
echo "Aguardando inicialização do emulador secundário..."
i=1
while [ "$i" -le 60 ]; do
  if adb -s emulator-5556 shell getprop sys.boot_completed 2>/dev/null | grep -q "1"; then
    echo "Emulador secundário pronto!"
    break
  fi
  echo "Aguardando emulador secundário... ($i/60)"
  sleep 5
  i=$((i+1))
done

# Verificação final
if ! adb -s emulator-5556 shell getprop sys.boot_completed | grep -q "1"; then
  echo "::error:: Falha ao iniciar emulador secundário"
  exit 1
fi

echo "Esperando Emulator 2 inicializar..."
adb -s emulator-5556 shell settings put global window_animation_scale 0
adb -s emulator-5556 shell settings put global transition_animation_scale 0
adb -s emulator-5556 shell settings put global animator_duration_scale 0

adb devices
adb -s emulator-5554 emu avd name
adb -s emulator-5556 emu avd name
