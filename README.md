# MedicalEmergency
This is a simple implementation of a single-button medical monitor. If a person gets sick, all they need to do is press a button in order to notify other people who are monitoring the button.

This project consists of a RaspberryPi hooked up to a button, and the Android app which can run on one or more phones. 

The Android apps use Google's Firebase to receive messages sent to a specific Topic they subscribe to. The RaspberryPi is hooked up to a single button which sends a message to that Topic.

# Publishing Test Messages
```
curl -X POST --insecure --header "Authorization:key=<server_key>" --header "Content-Type:application/json" -d "{\"to\": \"/topics/mother_alert\",\"data\": {\"message\": \"ALARM\"}}" https://fcm.googleapis.com/fcm/send
```
