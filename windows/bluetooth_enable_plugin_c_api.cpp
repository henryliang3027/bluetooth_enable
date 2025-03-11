#include "include/bluetooth_enable/bluetooth_enable_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "bluetooth_enable_plugin.h"

void BluetoothEnablePluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  bluetooth_enable::BluetoothEnablePlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
