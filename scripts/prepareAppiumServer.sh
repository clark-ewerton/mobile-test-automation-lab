          npm install -g appium
          appium -v
          appium driver list --installed
          appium driver install uiautomator2
          nohup appium --log-level warn --log-timestamp --local-timezone > appium.log 2>&1 &
          sleep 10
          curl http://127.0.0.1:4723/wd/hub/sessions || echo "Appium check failed"  
