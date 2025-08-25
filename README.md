# Bluetooth Enable Flutter Plugin


這個套件適用於 Android 與 iOS，用於在 App 內以程式的方式請求開啟藍牙功能。

套件基於 [bluetooth_enable_fork](https://github.com/Alystrasz/bluetooth_enable_fork) 開發。原本的 Android 程式碼使用了 PluginRegistry.Registrar，但此功能已在 Flutter 3.29.0 版本中移除。雖然仍可繼續使用 Java 並串接 FlutterPlugin 介面，但是基於 Kotlin 是 Google 推薦的語言。因此本套件使用 Kotlin 重新串接 Android 程式碼，iOS 部分無任何修改。

## 快速開始

### 1. 安裝套件

在您的 Flutter 專案中的 `pubspec.yaml` 檔案中添加：

方法一：使用 Git Repository
```yaml
  camera_checker:
    git:
      url: https://github.com/your-repo/bluetooth_enable.git # git repository 路徑
      ref: main
```

方法二：使用本地端路徑
```yaml
  camera_checker:
    path: /path/to/bluetooth_enable # 本地端路徑
```

然後執行：
```bash
flutter pub get
```

### 2. 基本使用示例

```dart
import 'package:bluetooth_enable_fork/bluetooth_enable.dart';

void requestBluetoothEnable() async {
  String result = await BluetoothEnable.enableBluetooth;
  
  if (result == "true") {
    print("藍牙已啟用");
    // 執行需要藍牙的功能
  } else {
    print("藍牙未啟用");
    // 處理藍牙未啟用的情況
  }
}
```

完整使用範例請參考：`example/lib/main.dart`

## API 函數說明

### BluetoothEnable 類別

#### 靜態方法

**`static Future<String> get enableBluetooth`**
- **功能**：請求在應用程式內以程式方式啟用藍牙功能
- **回傳**：`Future<String>`
  - `"true"` - 藍牙已成功啟用或已經處於啟用狀態
  - `"false"` - 藍牙啟用失敗或使用者拒絕啟用藍牙
- **平台行為**：
  - **Android**：會彈出系統對話框請求使用者啟用藍牙，等待使用者操作後回傳結果
  - **iOS**：會彈出提示窗要求使用者前往設定頁面手動啟用藍牙，方法會立即回傳 `"false"`（若使用者已啟用藍牙，下次呼叫時會回傳 `"true"`）

## 平台支援
支援 Android 與 iOS

**Android 平台**：
- 支援直接啟用藍牙功能
- 會顯示系統對話框請求使用者同意啟用藍牙

**iOS 平台**：
- 由於系統限制，無法直接啟用藍牙，會引導使用者前往系統設定頁面手動啟用藍牙，方法會立即回傳結果，不會等待使用者操作