# Android

## Android Architecture

The architecture of Android is **layered**.

Top: **Applications** e.g.:

-   home
-   contacts
-   phone
-   browser

Middle: **Application Framework** i.e.

-   activity manager
-   content providers
-   location manager
-   notification manager
-   package manger
-   resource manager
-   telephony manager
-   view system
-   window manager

Middle (further down):

-   **Libraries** e.g.
    -   SGL
    -   libc
    -   WebKit
    -   SSL
    -   SQLite
    -   Media Framework
    -   FreeType
-   **Android Runtime**
    -   core libraries
    -   virtual machine

Bottom: **Linux Kernel** i.e.

-   display driver
-   keypad driver
-   WiFi driver
-   audio driver
-   power management
-   flash memory driver
-   binder (IPC) driver

## Android Application Framework

-   Views
    -   UI elements
-   Content Providers
    -   enable data sharing between applications
-   Resource Manager
    -   provide access to non-code resources
-   Notification Manager
    -   enables applications to display alerts, status messages etc.
-   Activity Manager
    -   manages life cycles of activities

## Applications

-   An application is a collection of components.
-   There is no single entry point i.e. no `main()`.
-   May use other components from other applications vis their published
    interfaces.
-   Application components are started when needed.

### Application Components

-   **Activity**
    -   UI (composed of Views) for a single task
    -   an application may have have several activities
-   **Service** (think UNIX daemon)
    -   background process, no UI
    -   may be used by several applications
-   **Broadcast Receiver**
    -   receives and acts on notifications
    -   allows an application to respond to messages (an Android Intent) that
        are broadcast by the Android operating system or by an application

``` {.xml}
<!-- On incoming calls, add user to history -->
<receiver android:name=".broadcast.IncomingCallHandler" 
          android:enabled="false">
  <intent-filter>
    <action android:name="android.intent.action.PHONE_STATE" /> </intent-filter>
</receiver>
```

-   **Intent**
    -   used to activate Activities (`startActivity`), Services
        (`startService`, also communicate with a background Service
        `bindService`) and Broadcast Receivers (`broadcastIntent`)
    -   abstract description of an operation to be performed.

> An intent is an abstract description of an operation to be performed.
>
> -   It can be used with `startActivity` to launch an `Activity`
> -   It can be used with `BroadcastIntent` to send it to any interested
>     `BroadcastReceiver` components
> -   `startService(Intent)` or `bindService(Intent, ServiceConnection, int)`
>     to communicate with a background Service.
>
> -- From <https://developer.android.com/reference/android/content/Intent>

-   **Content Provider**
    -   Share data between applications, typically from an on-device SQLite
        database.
    -   If you don't need to do it you can just use a database
        directly via `SQLiteDatabase`.

### The Manifest File

All application packages (apk's) include an `AndroidManifest.xml` file:

-   declares components

``` {.xml}
<activity android:name=".MainActivity" 
  android:clearTaskOnLaunch="true" 
  android:excludeFromRecents="true"
  android:label="@string/app_name" 
  android:launchMode="singleTask" 
  android:screenOrientation="user"
  android:stateNotNeeded="true" 
  android:windowSoftInputMode="stateAlwaysHidden|adjustResize">

  <intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
    <category android:name="android.intent.category.HOME" />
    <category android:name="android.intent.category.DEFAULT" /> </intent-filter>
```

-   names required libraries
-   lists permissions required

``` {.xml}
<permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" 
            android:permissionGroup="android.permission-group.SYSTEM_TOOLS"
            android:protectionLevel="normal" />

<!-- Self explanatory -->
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

-   associates components with intents
-   says how each intent should be invoked (e.g. by the Launcher)
-   specifies icons and labels for display to the user

### The Activity Class

**Example** `HelloActivity.java`:

``` {.java}
package com.example.android.helloactivity;
import android.app.Activity;
import android.os.Bundle;
public class HelloActivity extends Activity {

        public HelloActivity() { ... }
                              an  
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.hello_activity);
        }
}
```

### Activity Lifecycle

-   3 states:
    -   Active -- in foreground, focus of user's actions
    -   Paused -- lost focus but still visible
    -   Stopped -- not visible, retains state
-   When paused or stopped, system *may* remove it to reclaim memory.
-   Activity is notified of state changes by callbacks to your
    `android.application.ActivitySubClass`
    -   `onCreate()`
    -   `onStop()`
    -   `onStart()`
    -   `onResume()`
    -   `onDestroy()`
    -   `onPause()`
    -   **NOTE**: You can overwrite those methods and perform some utility
        actions such as displaying info about the state from a `ContentProvider`
    -   **NOTE**: `Bundle savedInstanceState` is a key-value storage structure
        to hold application data between invocations (like a session variable)
- **NOTE**: activities are stopped when another activity comes in front of them
  and they are no longer visible. When stopped, they retain their state.
  After being stopped, they can be restarted if the user reopens them which triggers the `onRestart()`,
  `onStart()` and `onResume()` callbacks.
- `onCreate()` is only run when the activity is not stopped and is not paused.
  I.e. after the process was killed.
- If an activity wants to persist it's state it must save it in some persistent
  storage (e.g. `ContentProvider`).

## Services

-   Continuously running background process.
    -   provide shared service to one or more applications (activities)
        -   e.g. location tracking, music playing
    -   perform long-running tasks for a single application
        -   e.g. network file upload or download
-   Several alternative life-cycles:
    -   started and stopped by a client application
    -   started by a client, runs until task completed, then stops
    -   once started other clients may bind to the service
    -   service will not be stopped if running or has a bound client (except in
        cases of extremely low memory)

## Content Providers (CP)

-   No shared storage are the major way to share data between applications
-   Built-in Content Providers for:
    -   contacts list
    -   audio
    -   image store
    -   video store
-   To make data public, create a Content Provider subclass or add data to an
    existing writeable Content Provider.
-   Queries use:
    -   `content://` URI to identify Content Provider
    -   `ContentResolver.query(...)` method and `moveToFirst()` or
        `moveToNext()` for accessing rows of results

## Resources

Resources are the additional files and static content that your code uses such
as:

-   bitmaps
-   layout definitions
-   user interface strings
-   animation instructions

You should always externalize app resources such as images and strings from
your code, so that you can maintain them independently. You should also provide
alternative resources for specific device configurations, by grouping them in
specially-named resource directories.

At runtime, Android uses the appropriate resource based on the current
configuration. For example, you might want to provide a different UI layout
depending on the screen size or different strings depending on the language
setting.

Once you externalize your app resources, you can access them using resource IDs
that are generated in your project's R class.

Grouping resource types You should place each type of resource in a specific
subdirectory of your project's `res/` directory. For example:

    MyProject/
        src/
            MyActivity.java
        res/
            drawable/
                graphic.png
            layout/
                main.xml
                info.xml
            mipmap/
                icon.png
            values/
                strings.xml

As you can see in this example, the `res/` directory contains all the resources
(in subdirectories):

-   an image resource
-   two layout resources
-   `mipmap/` directories for launcher icons
-   a string resource file

  Directory   Role
  ----------- --------------------------------------------------------------------------------------
  `color/`    XML files that define a state list of colours
  `layout/`   XML files that define a user interface layout
  `menu/`     XML files that define app menus, such as an Options Menu, Context Menu, or Sub Menu.
  `values/`   XML files that contain simple values, such as strings, integers, and colours.

Additionally you can append `-fr`, `-pl`, `ar` etc. to `values/` so that is
becomes e.g. `values-fr/`. This will allow you specify the content in other
languages.

**Snippet from** `strings.xml`:

``` {.xml}
<string name="app_name">KISS launcher</string>
<string name="activity_setting">KISS settings</string>
<string name="ui_search_hint">Search apps, contacts, …</string>
<string name="ui_item_search">Search %1$s for “%2$s”</string>
<string name="ui_item_visit">Visit “%1$s”</string>
<string name="ui_item_phone">Call “%s”</string>
```

### Layouts

All extend `ViewGroup` (which extends `View`). A `Viewgroup` has the capacity
to contain within itself a number of `View`s.

Layouts (`ViewGroup`s) Can be nested e.g.:

``` {.xml}
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android" 
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="fill_parent" 
                android:layout_height="fill_parent" 
                android:orientation="vertical">

  <RelativeLayout android:id="@+id/widgetLayout" 
                  android:layout_width="match_parent"
                  android:layout_height="match_parent" 
                  android:layout_above="@+id/resultLayout" 
                  android:layout_alignParentTop="true"
                  android:layout_marginLeft="10dp" 
                  android:layout_marginRight="10dp" 
                  android:layout_marginTop="10dp"
                  android:gravity="center_horizontal"> 
  </RelativeLayout>

    ...
```

To set a layout: call `setContentView(View v)`.

Layouts:

- `LinearLayout` (there is a vertical and horizontal version)
- `RelativeLayout` (allows to position widgets relative to each other -- above,
  below, centred)
- `TableLayout` (borderless stack of table rows)
- (new) `ConstraintLayout`
- (new) `FrameLayout`

To fire a callback when a button (i.e. a widget) has been pressed you need to
assign the name of the callback to the "on click" property.

### Widgets

Examples:

- Button
- RadioButton
- CheckBox
- CalendarView
- TextView

### Using Resources

You can access resources via `R` e.g.:

``` {.java}
public class ConvertActivity extends Activity {

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
        }

        // Called at button click because we assigned the name
        // to the "On Click property" of the button
        public void myClickHandler(View view) {
            switch (view.getId()) {
                case R.id.button1: // ...
            }
        }
}
```
