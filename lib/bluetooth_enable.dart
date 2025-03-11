import 'bluetooth_enable_platform_interface.dart';

class BluetoothEnable {
  static Future<String> get enableBluetooth {
    return BluetoothEnablePlatform.instance.enableBluetooth();
  }
}
