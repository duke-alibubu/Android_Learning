# Android_Learning
### Basic repo infos
- AboutMe is a basic Android App that uses simple Linear Layout
- ColorMyViews is a basic Android App that makes use of Constraint Layout and the Layout Editor.
- Dice Roller is a basic Android App that has compatible drawable XML resources, and simple Linear Layout from the code editor.
- TemplateApp is a basic Android App resulting from a template.
# References
- ```https://developer.android.com/index.html```
- ```https://material.io/```: a conceptual design philosophy that outlines how apps should look and function on mobile devices
# Basic topics
### Basic structure & general settings of an Android Project  
- 'java' folder contains the main code for the app (i.e the Activity , v..v)
- 'res' folder holds resources - static contents used in the apps, including images, text strings, screen layouts, styles, and values such as hexadecimal colors or standard dimensions.
- 'manifests' folder contains files that provide essential info about your app to the Android system. (e.g the AndroidManifest.xml)
- 'Gradle Scripts': Gradle is a build automation system that describes the app's PROJECT STRUCTURE, CONFIG. & DEPENDENCIES
  + ' build.gradle(Project: ...)' : <project-level> contains the config. options that are common to all the modules that make up your project. Every Android project contains a single, top-level Gradle build file. This file defines the Gradle repositories and dependencies that are COMMON TO ALL MODULES in the project.
  + 'build.gradle(Module:app)' : <module-level> Each module has a build.gradle file of its own. The module-level build.gradle file allows you to configure build settings for each module. You may need to edit this build.gradle file when you change the SDK level that your app supports, or when you declare new dependencies in the dependencies section...

### About Layout XML files
- View groups are containers that hold other views and help specify the views' positions on the screen. 
- All the view & view groups you add to your layout are organized in a view hierarchy, with the topmost XML element as root of that hierarchy. When your app runs the view hierarchy in your XML layout file becomes a hierarchy of objects when the layout is inflated.
- Mostly the top-level element are either LinearLayout or ConstraintLayout.
- Instead of hardcoding strings in the layout or code file, it's a best practice to put all your app strings into a separate file <strings.xml>.
- The parent Layout element:  orientation attribute: Direction of how we want to group the positions of the views, either vertically or horizontally.
- layout_gravity: to define the place of the element
- It's a good practice to assign an ID to the View, so that we can later access the view in the code part by using findViewbyID() method.
###### Constraint Layout
- A ```ViewGroup``` that allows you to position and size the layout's child views in a flexible way. 
- A constraint is a connection/alignment between two UI elements. Each constraint connects or aligns one view to another view, to the parent layout, or to an invisible guideline. 
- In a constraint layout, each view's position is defined using at least one horizontal constraint, and at least one vertical constraint.
- Chain:
  + A chain is a group of views that are linked to each other with bidirectional constraints.
  + The views within a chain can be distributed either vertically or horizontally.
  + A chain will have a head (first view of the chain)
  + Chain styles: Defining the way the chained views are spread out and aligned. You style a chain by assigning a chain style attribute, adding weight, or setting bias on the views. 4 chain styles: Spread, Spread inside, Packed, Weighted.
  + To add a chain style to a chain, set the ```layout_constraintHorizontal_chainStyle``` or the ```layout_constraintVertical_chainStyle``` attribute for the head of the chain. 
- Constraint bias: positions the view element along the horizontal and vertical axes. By default, the view is centered between the two constraints with a bias of 50%.
- Three constraint types (Check the docs for this) ...
- All views in a ```ConstraintLayout``` need to be constrained horizontally and vertically, or else the views jump to an edge of the parent when you run the app.This is why the Layout Editor adds ```tools:layout_editor_absoluteX``` if the view is not horizontally constrained. Layout Editor gives the design-time attribute the value of the view's current position in the layout, to keep the views in place during design. You can safely ignore these ```tools``` attributes, because Android Studio removes them after you create the constraints.
- Baseline constraints:
  + Aligns a view's text baseline to the text baseline of another view that has text.
  + Are helpful when views have different font sizes.
### Activities
- An Activity is a core Android class that is responsible for drawing an Android app UI and receiving input events.
- All activities have an associated layout file, which is an XML file in the app's resources. The layout file is named for the activity, for example activity_main.xml.
- The ```setContentView()``` method in MainActivity associates the layout with the activity, and inflates that layout when the activity is created.
- Layout inflation is a process where the views defined in the XML layout files are turned into (or "inflated" into) Kotlin view objects in memory. Once layout inflation happens, the Activity can draw these objects to the screen and dynamically modify them.

### Views & Using views
- All UI elements in the app layout are subclasses of the View class and are called views. TextView and Button are examples of views.
- View elements can be grouped inside a ViewGroup. A view group acts as a container for the views, or other view groups, within it. LinearLayout is an example of a view group that arranges its views linearly.
- A click handler is a method that is invoked when the user clicks or taps on a UI element. To attach a click-handler method to a view such as a button, use the setOnClickListener() method.
- VIEW STYLING: Content -> Padding -> Border -> Margin. Padding is the space inside the boundaries of a view or element. It is the space between the edges of the view and the view's content. Margin is the space added outside of the view's borders. It is the space from the edge of the view to its parent.
###### STYLE
- A collection of attributes that specify the appearance and format for a view. A style can include font color, font size, background color, padding, margin, and other common attributes. Using styles also allows you to keep these common attributes in one location. Reusing a style gives your app a consistent look when you have multiple views. A style is extractable by right-clicking on the view component - Refactor > Extract Style.
###### ScrollView
- A scroll view can contain only one child view. If you want to scroll more than one view, then add a ```ViewGroup``` such as a ```LinearLayout``` to the ```ScrollView```, and put the views to be scrolled inside that ```ViewGroup```.
### Drawables compatibility
- XML file images, a.k.a vector drawables - can scale without losing quality (that's why they are better than bitmap images like PNG or JPEG). 
- Vector drawables are only natively supported in versions of Android higher than API 21. In older versions, Gradle generates PNG images for those drawables when your app is built.
- You can specify that the Android Support Library should be used for vector drawables in older API versions with the ```vectorDrawables.useSupportLibrary = true``` configuration parameter in the ```build.gradle``` file. Once you've enabled the support library for vector drawables, use the ```app:srcCompat``` attribute in the <ImageView> element (instead of ```android:src```) to specify the vector drawable source for that image. The ```app``` namespace in your XML layout file is for attributes that come from either your custom code or from libraries, not from the core Android framework.  

### Fragments
- A ```fragment``` represents a behavior or a portion of user interface (UI) in an activity. You can combine multiple fragments in a single activity to build a multi-pane UI, and you can reuse a fragment in multiple activities. 
- A fragment ~ a modular section of an activity, something like a "sub activity":
  + A fragment has its own lifecycle and receives its own input events.
  + You can add or remove a fragment while the activity is running.
  + A fragment is defined in a Kotlin class.
  + A fragment's UI is defined in an XML layout file.
  
### Navigation paths
- A quite hard topics. Refer to ```https://codelabs.developers.google.com/codelabs/kotlin-android-training-add-navigation/index.html?index=..%2F..android-kotlin-fundamentals#2``` for more informations.
- 
###### The back stack
- The Android system keeps track of where users navigate on an Android-powered device. Each time the user goes to a new destination on the device, Android adds that destination to the ```back stack```.
- When the user presses the Back button, the app goes to the destination that's at the top of the back stack. BY DEFAULT, the top of the back stack is the screen that the user last viewed. 
- A navigation action can modify the back stack. You manage the back stack by setting the "pop" behavior for the actions that connect the fragments:
  + The ```popUpTo``` attribute of an action "pops up" the back stack to a given destination before navigating. (Destinations are removed from the back stack.)
  + If the ```popUpToInclusive``` attribute is false or is not set, popUpTo removes destinations up to the specified destination, but leaves the specified destination in the back stack.
  + If ```popUpToInclusive``` is set to true, the ```popUpTo``` attribute removes all destinations up to and including the given destination from the back stack.
  + If ```popUpToInclusive``` is true and ```popUpTo``` is set to the app's starting location, the action removes ALL app destinations from the back stack. The Back button takes the user all the way out of the app.
  
# Some execution optimization techniques
### Data binding: Eliminate ```findViewById()```
- Every time you use ```findViewById()``` to search for a view after the view is created or recreated, the Android system traverses the view hierarchy at runtime to find it. This would be a problem when the number of views turns large.
- SOLUTION: Create an object that contains a reference to each view. This object, called a ```Binding``` object, can be used by your whole app. This technique is called ```data binding```.
- Once a binding object has been created for your app, you can access the views, and other data, through the binding object, without having to traverse the view hierarchy or search for the data everytime you find the view.
- When the binding object is created, the compiler generates the names of the views in the binding object from the IDs of the views in the layout, converting them to camel case. So, for example, ```done_button``` is ```doneButton``` in the binding object, ```nickname_edit``` becomes ```nicknameEdit```, and ```nickname_text``` becomes ```nicknameText```.
- You can take advantage of data binding to make a data class directly available to a view. This technique simplifies the code, and is extremely valuable for handling more complex cases.
- To bind views to data, create a data class then add a ```<data>``` block inside the ```<layout>``` tag --> define a ```<variable>``` with a name, and a type that is the data class.
        
        (I do not like this much really. Will not use it ...)
- An useful link: ```https://codelabs.developers.google.com/codelabs/kotlin-android-training-data-binding-basics/index.html?index=..%2F..android-kotlin-fundamentals#5```


