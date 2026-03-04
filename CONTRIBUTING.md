# Contributing

This document provides information useful for contributing to the Meergo Kotlin SDK.

## Prerequisites

- **JDK 11** (recommended) or any JDK version from 8 to 16.

> **Why not JDK 17+?**
> The test suite uses MockK 1.10.6 which has compatibility issues with newer JVMs, causing test failures. JDK 11 is the recommended choice as it is a widely supported LTS release.

To verify your JDK version:

```bash
java -version
```

If you have multiple JDK versions installed, you can set `JAVA_HOME` before running Gradle:

```bash
export JAVA_HOME=/path/to/jdk-11
```

If you use **Android Studio**, make sure the Gradle JDK is set to JDK 11: **File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK**.

## Testing the SDK

Tests can be run locally on Linux and Windows:

```bash
./gradlew core:test android:test
```

## Running the sample application

To run the sample application locally:

1. Install [Android Studio](https://developer.android.com/studio).

2. Create and configure an [Android Virtual Device (AVD)](https://developer.android.com/studio/run/managing-avds), if you did not already create one during the Android Studio installation process.

3. Open `MainApplication.kt` and replace `WRITE_KEY` and `ENDPOINT` with the values from your Meergo Android source.

   **Note**:
   If you want to send events to a local Meergo instance running on your development machine, you must replace `localhost` or `127.0.0.1` in the endpoint URL with `10.0.2.2`.

   This is required because the Android Emulator treats `localhost` as the emulator itself. Using `10.0.2.2` allows the emulator to reach services running on your host machine.

   For more information, see the Android documentation on [emulator networking](https://developer.android.com/studio/run/emulator-networking).

   **Using HTTPS locally?**
   Additional emulator configuration is required when sending events to a local Meergo instance running on HTTPS via self-signed certificates.
   See [Using self-signed SSL certificates with mkcert](#using-self-signed-ssl-certificates-with-mkcert).

4. Build and run the application from Android Studio.

5. Interact with the buttons inside the application running in the emulator. You should see new logged events in the event debugger of the Meergo Android source.

### Using self-signed SSL certificates with mkcert

If your local Meergo instance runs over **HTTPS** using mkcert self-signed SSL certificates, additional configuration is required for the Android Emulator, since the emulator will not trust the certificates by default:

* Ensure the self-signed certificate generated for running Meergo is valid for **both** `localhost` and `10.0.2.2`.
* Install the **mkcert CA certificate** inside the emulator so Android trusts it:

   1. Locate the mkcert CA file (`rootCA.pem`) on your machine:

      ```
      mkcert -CAROOT
      ```

   2. Convert it to Android-compatible format:

      ```
      openssl x509 -outform der -in rootCA.pem -out rootCA.crt
      ```

   3. Drag & drop `rootCA.crt` into the Android Emulator.

   4. In the emulator: **Settings → Security & privacy → More security & privacy → Encryption & credentials → Install a certificate → CA certificate → Install anyway**.

   5. Select the `rootCA.crt` file from the file explorer of the emulator.

After installation, the CA should appear under **Settings → Security & privacy → More security & privacy → Encryption & credentials → Trusted credentials → User**, and your local HTTPS Meergo instance should now be trusted by the emulator.