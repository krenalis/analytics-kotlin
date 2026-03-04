# Meergo Kotlin SDK (🚧 work in progress) 

---

## 🚨 Important note. 🚧 Please note that this SDK is still under development, has not been released, and is therefore not yet usable. Therefore, the commands and documentation provided here may not work or may be changed without notice. Stay tuned for updates. 👀

---

The Meergo Kotlin SDK lets you send customer event data from your Android applications to your specified destinations.

## SDK setup requirements

- Set up a Meergo account.
- Set up an Android source in the dashboard.
- Copy the write key and the endpoint.

## Installation

To integrate the Kotlin SDK inside your application, add the dependency to your `build.gradle`. Make sure to replace `<latest_version>` with the latest version of the SDK.

```kotlin
repositories {
  mavenCentral()
}
dependencies {
  implementation 'com.meergo.analytics.kotlin:android:<latest_version>'
}
```

Add the required permissions to `AndroidManifest.xml` (if they are not yet present).
```xml
<!-- Required for internet. -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
``` 

## Using the SDK

```kotlin
import com.meergo.analytics.kotlin.android.Analytics
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

val client = Analytics("YOUR_WRITE_KEY", applicationContext) {
  endpoint = "YOUR_ENDPOINT"
  trackApplicationLifecycleEvents = true
  flushAt = 3
  flushInterval = 10
  // ...other config options
}

client.track(
  "Workout completed", 
  buildJsonObject { 
    put("workout_type", "Cardio");
    put("duration_minutes", 45);
    put("calories_burned", 380);
    put("device", "Smartwatch")
  },
)
```

## Sending events

Refer to the Meergo events documentation for more information on the supported event types.

## Compatibility

* The SDK requires **JVM 8** or higher.
* If you use pure Java codebase, please refer to [Java Compatibility](JAVA_COMPAT.md) for sample usages.

## License
```
MIT License

Copyright (c) 2026 Open2b
Copyright (c) 2021 Segment

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
