import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:bluetooth_enable/bluetooth_enable.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool _checkEnabled = false;
  String enableBluetooth = '';

  @override
  void initState() {
    super.initState();

    requestBluetoothPermission();
  }

  Future<void> requestBluetoothPermission() async {
    if (await Permission.bluetoothConnect.request().isGranted) {
      print('isGranted');
    } else {
      // Handle the case where permission is not granted.
      print('isNotGranted');
    }
  }

  // Platform messages are asynchronous, so we initialize in an async method.

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Plugin example app')),
        body: Column(
          children: [
            Center(child: Text('Bluetooth Enabled: $_checkEnabled\n')),
            ElevatedButton(
              onPressed: () async {
                print('234234');
                String response =
                    await BluetoothEnable.enableBluetooth ?? 'Failed';

                print(response);

                if (response == 'true') {
                  setState(() {
                    _checkEnabled = true;
                  });
                } else {
                  setState(() {
                    _checkEnabled = false;
                  });
                }
              },
              child: Icon(Icons.bluetooth),
            ),
          ],
        ),
      ),
    );
  }
}
