package com.example.bluetooth_enable

import androidx.annotation.NonNull

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener

/** BluetoothEnablePlugin */
class BluetoothEnablePlugin: FlutterPlugin, MethodCallHandler, ActivityAware, ActivityResultListener {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var activity: Activity? = null
  private var pendingResult: MethodChannel.Result? = null
  private val REQUEST_ENABLE_BT = 1001

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "bluetooth_enable")
    channel.setMethodCallHandler(this)

  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    // if (call.method == "getPlatformVersion") {
    //   result.success("Android ${android.os.Build.VERSION.RELEASE}")
    // } else {
    //   result.notImplemented()
    // }

    when (call.method) {
      "enableBluetooth" -> {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter?.isEnabled == false) {
          pendingResult = result
          // Ask user to enable Bluetooth.
          val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
          activity?.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else {
          result.success("true")
        }
      }
      else -> result.notImplemented()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
    if (requestCode == REQUEST_ENABLE_BT) {
      // If user pressed "Allow", resultCode == Activity.RESULT_OK
      val wasEnabled = (resultCode == Activity.RESULT_OK)

      // Send the result string ("Enabled" or "Cancelled") back to Dart
      pendingResult?.success(if (wasEnabled) "true" else "false")
      pendingResult = null

      return true // We handled this result
    }
    return false // Not our request
  }


  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  // ActivityAware implementations to keep track of the Activity.
  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
    binding.addActivityResultListener(this)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity = null
  }
}
