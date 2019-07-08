# Android_Learning
# References
- https://developer.android.com/index.html
- https://material.io/ : a conceptual design philosophy that outlines how apps should look and function on mobile devices
# Basic topics:
### Basic structure & general settings of an Android Project  
- 'java' folder contains the main code for the app (i.e, the Activity , v..v)
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
- 

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
