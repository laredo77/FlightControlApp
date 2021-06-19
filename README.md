# FlightControlApp
Android application for controlling and flying an airplane in the 'FlightGear' internet flight simulator.

![app](https://user-images.githubusercontent.com/60240620/122652676-9d30be80-d148-11eb-9bfa-eedb00ff76fc.jpg)

### Description
This app is an easy to use tool for users who want to make the plane fly in FlightGear simulator.
The app is designed for Android, and allows the user to control the plane during the flight using a joystick that simulates the pilot's joystick, the rudder and the throttle slider.
The application has a nice graphic design which makes it easier and more convenient to use, thus helping the pilot to improve his skills.

### Installation
To use the application, first download the code to your computer or mobile device.
Then, you can run the code using [Android Studio](https://developer.android.com/studio) or any IDE that can run Kotlin.
In addition, you must install the FlightGear flight simulator on your computer. [Download](https://www.flightgear.org/download/),  [Setup tutorial](https://wiki.flightgear.org/New_to_FlightGear)

### Usage
First, Open FlightGear, go to Setting and insert the following command into the text box.

`--telnet=socket,in,10,127.0.0.1,8080,tcp`

![setting](https://user-images.githubusercontent.com/60240620/122652697-ba658d00-d148-11eb-96dd-a5c0428f7c87.jpg)

Using this command, the simulator will open the server for the clients at the localhost address and port 8080.
>* Press Fly and let the FlightGear load the simulator.

Run the app and and start connecting.
>* Use the dedicated button to enter the IP address of your PC. (for example 192.168.1.14)
>* Use the dedicated button and enter the port that the server is listening to: 8080
>* Click on the Connect button.

Back to the FlightGear simulator, on the top menu, press `Cessna C172P` and then `Autostart`. The engine will start working.

![autostart](https://user-images.githubusercontent.com/60240620/122652716-cc473000-d148-11eb-9f1d-f6d79f37b72b.jpg)

You can press 'v' in your keyboard to change the point of view you are looking at the plane.
Once the engine is working, raise the throttle slider (Left on the screen) to speed up. move the rudder slidder (In the bottom) to direct the plane, and when the plane has gained enough speed, move the dedicated joystick and enjoy your flight safely.

![app2](https://user-images.githubusercontent.com/60240620/122652721-dbc67900-d148-11eb-8f73-f516a3dc04c8.jpg)

In order to disconnect, press the dedicate bottun and close the applications.

### Dictionary
Folders of the project:
> Model
> ViewModel
> View
> 

![uml](https://user-images.githubusercontent.com/60240620/122652958-4cba6080-d14a-11eb-8583-958c6796a956.jpg)

### Technical explanation
The app was written in the programming language Kotlin. The project was written according to the MVVM code architecture, which separates the functionality of the data presentation from the processing and calculation of the data. The ViewModel link between them is to provide safety and separation of responsibilities.
In the Model part, I used the 'Active Object' design pattern crwates a connection with the server in a separate thread, and uses a queue that sends tasks to the server one by one.
When the user moves the joystick, a task enters the queue. When its time comes, the model sends a command through the connection to the server in order to execute it on the plane.
The Model is connected to the ViewModel which is connected to the View. The View displays the app, listens for changes of the joystick and of other controls.

### Credits
The project was written by Itamar Laredo, as part of a project in Advanced programming 2 course, Bar-Ilan University.
