Notifikator
===========

This is an Android application that catches every notification and forwards them to an HTTP endpoint.

It natively supports talking to the [Kodi](https://kodi.tv/) JSON-RPC interface, [Notifications for Android TV](https://play.google.com/store/apps/details?id=de.cyberdream.androidtv.notifications.google), as well as sending plain JSON.

How to Use
----------

1. Install the application.
2. Enable notification access (a shortcut is provided within).
3. Configure the HTTP endpoint, protocol and authentication if necessary (see next section).
4. *(Optional)* Send a test notification.

Protocols
---------

### Kodi

This protocol sends a call to show a notification through the Kodi JSON-RPC interface.

In order for this to work, the "Allow remote control via HTTP" setting must be turned on in Settings -> Services -> Control in Kodi.

The endpoint URL should look like `http://hostname:8080/jsonrpc`, and authentication should be enabled and matching the username and password configured in Kodi.

### Kodi with Addon

This is a variant on the Kodi protocol. Sadly, the `GUI.ShowNotification` call takes URLs for images, and will not accept `data:` URIs, therefore the notification icon can not be sent that way.

This variant requires you to install the `script.notifikator.zip` addon, which will save the received image as a temporary file, and then show the notification.

Otherwise, the endpoint configuration should be the same as with the Kodi protocol.

**Privacy issue:** Every received notification icon will stay stored in `~/.kodi/temp/notifikator` forever. This may include profile pictures or other private images.

### Notifications for Android TV

This protocol forwards the notification to the server part of the [Notifications for Android TV](https://play.google.com/store/apps/details?id=de.cyberdream.androidtv.notifications.google) application. No customization options are available, unlike in the real application.

The endpoint URL should look like `http://hostname:7676/`, and authentication should not be enabled.

### JSON

This protocol will simply `POST` a JSON containing the notification title, text, small and large icons to an arbitrary endpoint. This is designed for people who want to write their own server code to handle it.

The JSON format is intentionally similar to the parameters of the [`ServiceWorkerRegistration.showNotification`](https://developer.mozilla.org/en-US/docs/Web/API/ServiceWorkerRegistration/showNotification) API, so in theory the contents could be used as-is. In practice, the combined size of the text and icon image data will likely exceed the maximum WebPush size and not go through.

Here is an example JSON, note that any of the fields not present in the original notification will not be present in the JSON. Additionally, an empty `options` object will be omitted.

```json
{
    "title": "Notification Title",
    "options": {
        "body": "Notification text.",
        "badge": "data:image/png;base64,...",
        "icon": "data:image/png;base64,..."
    }
}
```