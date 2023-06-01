## PAD-04
NAO robot programming project, year one computer engineering HvA

## What we did
For this project we created three main functions. Two of these functions can be controlled by pressing a button on 
the robots head. The first function is designed to detect humans in a certain range. The way this function works 
is as follows:

When a person walks by the NAO, in the range given by us. The NAO will start interacting with that person. He 
interacts by welcoming the person walking by and waves at them. Then he proceeds to give them an introduction on what 
the functions are and how people can start the desired function.

The second function (the second button on the robots head) is an adapted version of simon says using the NAO's red 
ball detection module to check whether a "command" is properly executed. An example of a command is: simon says put 
your hands above your head. The users then put their hands (with a red balloon between their hands) above their heads. 
The NAO checks if the move is made correctly, otherwise it will tell the user to adjust the position of the balloon to 
the proper position.

The third function (the third button on the robots head) is a dance show we created to entertain the users. The dance 
show is accompanied by music and disco eyes. Our inspiration for the dance was "kinderen voor kinderen", which is a 
Dutch initiative to promote health and fitness for children.

 
###### Kinderen voor kinderen link: https://www.youtube.com/watch?v=QUMygc3_JWA

## README for the other modules

* [Audio module](./src/audio)
* [Configuration module](./src/configuration)
* [Core module](./src/core)
* [LED's module](./src/leds)
* [Memory module](./src/memory)
* [Motion module](./src/motion)
* [Speech module](./src/speech)
* [Vision module](./src/vision)


## Visuals
GIF/video of function one and two

## Installation
To use this code in java you will need an SDK and a JDK that are compatible with your operating system and the NAO 
version. 

Apart from that make sure that the name of the nao in the configure file corresponds with the proper name of your 
NAO robot running your java code on the NAO.

A helpful tip to get you started using the NAO is to download Choregraphe. If you have a mac this might prove a bit
difficult, but it is possible. Depending on your processor (Intel of mac) there are solutions that work to a certain
degree. One such solution is by getting a MAC user with the app to put it on a USB stick and download the app from 
there.

## Support
A very helpful link is the aldebaran documentation for the NAO. There are NAOqi and NAO documentation. Both are 
equally useful. In the NAOqi documentation you'll find the API's that can be used to program the NAO. In the NAO 
documentation you'll find a technical overview of 

###### NAOqi link: http://doc.aldebaran.com/2-5/index_dev_guide.html
###### NAO link: http://doc.aldebaran.com/2-5/home_nao.html

## Authors and acknowledgment
This project was made possible by Ahmed El Ela and Valentijn Bruggeman (myself)

Our group started out with two others, but they quit the study around halfway without any major contributions.

## Project status
Done