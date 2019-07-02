# Android_Learning

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
