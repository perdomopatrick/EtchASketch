# Etch A Sketch

## What the application does?

The application is a digital version of the drawing toy [Etch A Sketch](https://en.wikipedia.org/wiki/Etch_A_Sketch). An Etch A Sketch consists of three components: two knobs, a canvas, and a stylus. The left knob moves the stylus left and right, while the right knob moves it up and down, depending on which way you **twist** them. The canvas displays what you draw, you can draw only with 2 colours—black and white.

The functionality of the stylus makes the Etch A Sketch unique from other drawing toys. First, the stylus cannot be lifted, your entire drawing is a single continuous line. However, the stylus can be moved in all 2D directions with the knobs. Second, once you have advanced the stylus, you can not undo the change. When you're done with your drawing and want to restart, you can shake the canvas to get a blank canvas.

The **digital** version is like the physical toy but better, because it has **extra** features. For example, allowing you to make as many drawings as you want with no need to erase your drawing every time. It also allows you to see all your saved drawings.

## Who will use it and Why is this project of interest to you?

The project is for anyone, especially for those who want to be creative and draw in an unconventional and fun way. My motivation for creating this project is to make the toy more accessible and to add much-needed features.

## User Stories

- As a user, I want to be able to keep an arbitrary number of canvases in my collection
- As a user, I want to be able to view all my collection of canvases
- As a user, I want to be able to draw on my canvas
- As a user, I want to be able to see my canvas as I draw
- As a user, I want to be able to reset/shake my canvas
- As a user, I want to be able to have the option to save my gallery
- As a user, I want to be able to have the option to load my gallery

- As a user, I want to be able to have the option to remap controls
- As a user, I want to be able to have the option to save my canvas as a photo

## Instructions for End User

- How to add an canvas to gallery
    1. In main menu, click 'New Canvas'
    2. Enter desired width and height 
    3. Click 'Create Canvas'
- How to draw on canvas
    1. Following the same steps above of 'how to add a canvas to gallery' 
    2. You are now in the drawing menu
    3. To move the stylus and start drawing use the arrow keys (Default controls)
    4. If you do not like the controls, in the top left corner there is a remap controls menu bar
    5. Click the direction you would like to remap, then click the new key
- How to view canvases
    1. In main menu, click 'View Canvas'
    2. In the bottom, you can either go to the next or previous Canvas by clicking the buttons
- How to export canvas to PNG
    1. In main menu, click 'View Canvas'
    2. Navigate to canvas you would like to export next Canvas and the previous Canvas buttons
    3. At the top, click 'Export to .png' to export
    4. Image will be in data folder
- How to draw on old canvas
    1. In main menu, click 'View Canvas'
    2. Navigate to canvas you would like to export next Canvas and the previous Canvas buttons
    3. At the bottom, click 'Draw'
- How to find the visual component
    1. In main menu, click 'Visual Component'
    2. You will see an image in the middle of the frame
    - Credit to: https://github.com/djaiss/mapsicon for the 512x512 images
- How to save your gallery
    1. In main menu, click 'Save Gallery'
- How to load your gallery
    1. In main menu, click 'Load Gallery'


### Phase 4: Task 2
Wed Nov 27 11:39:26 PST 2024 <br>
New 2x3 canvas <br>
Wed Nov 27 11:39:26 PST 2024 <br>
Set stylus X coordinate to 0 <br>
Wed Nov 27 11:39:26 PST 2024 <br>
Set stylus Y coordinate to 0 <br>
Wed Nov 27 11:39:26 PST 2024 <br>
Added new 2x3 canvas to gallery <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Set stylus X coordinate to 1 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Drew right by 1 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Set stylus Y coordinate to 1 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Drew down by 1 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Set stylus X coordinate to 0 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Drew left by 1 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Set stylus Y coordinate to 0 <br>
Wed Nov 27 11:39:27 PST 2024 <br>
Drew up by 1 <br>
Wed Nov 27 11:39:29 PST 2024 <br> 
Drew up by 1 (Hit the border)<br> 
Wed Nov 27 11:39:29 PST 2024 <br>
Drew left by 1 (Hit the border) <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Created loaded canvas <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Set stylus X coordinate to 2 <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Set stylus Y coordinate to 1 <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Added given canvas to gallery <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Created loaded canvas <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Set stylus X coordinate to 1 <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Set stylus Y coordinate to 2 <br>
Wed Nov 27 11:39:35 PST 2024 <br>
Added given canvas to gallery <br>
Wed Nov 27 11:39:37 PST 2024 <br>
Reset current canvas index to zero <br>
Wed Nov 27 11:39:39 PST 2024 <br>
Added one from current canvas index <br>
Wed Nov 27 11:39:40 PST 2024 <br>
Subtracted one from current canvas index <br>
Wed Nov 27 11:39:43 PST 2024 <br>
Drew right by 1 (Hit the border) <br>
Wed Nov 27 11:39:43 PST 2024 <br>
Set stylus Y coordinate to 2 <br>
Wed Nov 27 11:39:43 PST 2024 <br>
Drew down by 1 <br>
Wed Nov 27 11:39:43 PST 2024 <br>
Drew down by 1 (Hit the border) <br>
Wed Nov 27 11:39:45 PST 2024 <br>
Shaked canvas <br>

### Phase 4: Task 3

![alt text](UML_Design_Diagram.png)

One thing that I could refactor is the Gallery and how it is used by other classes. I made Gallery a singleton before really understanding the pattern completely and I think that not only did I not implement the singleton that well, I also don't think it was the right place to use the singleton pattern. If I were to refactor it, instead of make Gallery a singleton, I would create an instance of Gallery in the MainMenu and pass it as a argument to other menus.

I would also refactor the way the menus interact with each other because it seems like there's a lot of coupling in the UML diagram between the main menu and the other menus that isn't exclusively necessary. Maybe, I could create a menu manager class to handle transitions between menus, so individual menus don’t need to know about each other.