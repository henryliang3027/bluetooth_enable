## Introduction

BluetoothEnable is a Bluetooth plugin for [Flutter](https://www.flutter.io), to programtically request turning on Bluetooth within applications. 

This plugin is based on [bluetooth_enable_fork](https://github.com/Alystrasz/bluetooth_enable_fork). The PluginRegistry.Registrar was used in the Android code of this repository, but it was removed in Flutter 3.29.0. Although it is still possible to continue using Java and switch to the new FlutterPlugin interface, Kotlin is the language recommended by Google. Therefore, the plugin has been rewritten in Kotlin.

## Usage

### Enable Bluetooth

```dart
import 'package:bluetooth_enable_fork/bluetooth_enable.dart';

// Request to turn on Bluetooth within an app
BluetoothEnable.enableBluetooth.then((result) {
  if (result == "true") {
    // Bluetooth has been enabled
  }
  else if (result == "false") {
    // Bluetooth has not been enabled
  }
});
```

### iOS support

If Bluetooth has not been activated, a popup window will appear on `enableBluetooth` call asking user to activate Bluetooth in the application settings; 
the method will immediately return false (but will return true on next call if user enabled Bluetooth in the meantime).