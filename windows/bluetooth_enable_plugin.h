#ifndef FLUTTER_PLUGIN_BLUETOOTH_ENABLE_PLUGIN_H_
#define FLUTTER_PLUGIN_BLUETOOTH_ENABLE_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace bluetooth_enable {

class BluetoothEnablePlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  BluetoothEnablePlugin();

  virtual ~BluetoothEnablePlugin();

  // Disallow copy and assign.
  BluetoothEnablePlugin(const BluetoothEnablePlugin&) = delete;
  BluetoothEnablePlugin& operator=(const BluetoothEnablePlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace bluetooth_enable

#endif  // FLUTTER_PLUGIN_BLUETOOTH_ENABLE_PLUGIN_H_
