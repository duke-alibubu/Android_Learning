# Android_Learning
## Basic repo infos
- AboutMe is a basic Android App that uses simple Linear Layout
- ColorMyViews is a basic Android App that makes use of Constraint Layout and the Layout Editor.
- Dice Roller is a basic Android App that has compatible drawable XML resources, and simple Linear Layout from the code editor.
- TemplateApp is a basic Android App resulting from a template.
- AndroidTrivia is a basic Android App about using navigation path and fragments.
- GuessTheWorld is a basic Android App using ViewModel and LiveData, making use of the observer & factory pattern as well as encapsulation.
- TrackMySleepQuality is a more complex Android App making use of Room database & coroutines, binding adapter and many topics regarding RecyclerView.
- MarsRealEstate is a more complex Android App using Retrofit to interact with REST web service and Moshi for JSON parsing.
# References
- ```https://developer.android.com/index.html```
- ```https://material.io/```: a conceptual design philosophy that outlines how apps should look and function on mobile devices
# Basic topics
## Four different types of app components:
- **Activity**: The entry point for interacting with the user. It represents a single screen with a user interface. For example, an email app might have one activity that shows a list of new emails, another activity to compose an email, and another activity for reading emails. Although the activities work together to form a cohesive user experience in the email app, each one is independent of the others. As such, a different app can start any one of these activities if the email app allows it.
- **Service**: A general-purpose entry point for keeping an app running in the background for all kinds of reasons. It is a component that runs in the background to perform long-running operations or to perform work for remote processes. A service does not provide a user interface. For example, a service might play music in the background while the user is in a different app, or it might fetch data over the network without blocking user interaction with an activity. Another component, such as an activity, can start the service and let it run or bind to it in order to interact with it. There are actually two very distinct semantics services tell the system about how to manage an app: 
  + Started services tell the system to keep them running until their work is completed. This could be to sync some data in the background or play music even after the user leaves the app.
  + Bound services run because some other app (or the system) has said that it wants to make use of the service. This is basically the service providing an API to another process.
- **Broadcast receiver**: A component that enables the system to deliver events to the app outside of a regular user flow, allowing the app to respond to system-wide broadcast announcements. Because broadcast receivers are another well-defined entry into the app, the system can deliver broadcasts even to apps that aren't currently running. So, for example, an app can schedule an alarm to post a notification to tell the user about an upcoming event... and by delivering that alarm to a BroadcastReceiver of the app, there is no need for the app to remain running until the alarm goes off.
- **Content provider**: Manages a shared set of app data that you can store in the file system, in a SQLite database, on the web, or on any other persistent storage location that your app can access. Through the content provider, other apps can query or modify the data if the content provider allows it.
## UI controller
- A *UI Controller* is a UI-based class such as ```Activity``` or ```Fragment```. A UI controller should only contain logic that handles UI and operating-system interactions such as displaying views and capturing user input. Don't put decision-making logic, such as logic that determines the text to display, into the UI controller.
## ViewModel
- A ```ViewModel``` holds data to be displayed in a fragment or activity associated with the ```ViewModel```. A ```ViewModel``` can do simple calculations and transformations on data to prepare the data to be displayed by the UI controller. In this architecture, the ```ViewModel``` performs the decision-making.
- The ```ViewModel``` should never contain references to fragments, activities, or views, because activities, fragments, and views do not survive configuration changes.
### ViewModelFactory
- A ```ViewModelFactory``` instantiates ```ViewModel``` objects, with or without constructor parameters.
### Using the ```GameViewModel``` class
- Open the ```build.gradle(module:app)``` file. Inside the ```dependencies``` block, add the Gradle dependency for the ```ViewModel```: 
```implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'```
- The ```ViewModel``` is destroyed when the associated fragment is detached, or when the activity is finished. Right before the ```ViewModel``` is destroyed, the ```onCleared()``` callback is called to clean up the resources.
### Initializing the ```ViewModel``` with the ```ViewModelProvider```
- A ```ViewModel``` needs to be associated with a UI controller. To associate the two, you create a reference to the ```ViewModel``` inside the UI controller.
- During configuration changes such as screen rotations, UI controllers such as fragments are re-created. However, ```ViewModel``` instances survive. If you create the ```ViewModel``` instance using the ```ViewModel``` class, a new object is created every time the fragment is re-created. ***Instead, always create the ```ViewModel``` instance using a ```ViewModelProvider.```***
### ViewModelFactory
- As the name implied, this class makes use of the factory design pattern.
- The factory method pattern is a creational design pattern that uses factory methods to create objects. A factory method is a method that returns an instance of the same class.
## LiveData
- ```LiveData ``` let you build data object that notify views when the underlying database changes.
- To use the ```LiveData``` class, you set up "observers" (for example, activities or fragments) that observe changes in the app's data. - Some properties of ```LiveData```:
  + ```LiveData``` is observable, which means that an observer is notified when the data held by the ```LiveData``` object changes.
  + ```LiveData``` holds data; ```LiveData``` is a wrapper that can be used with any data.
  + ```LiveData``` is lifecycle-aware, meaning that it **ONLY** updates observers that are in an active lifecycle state such as STARTED or RESUMED.
### MutableLiveData
- ```MutableLiveData``` is a ```LiveData``` whose value can be changed. ```MutableLiveData``` is a generic class, so you need to specify the type of data that it holds.
  + Using ```observer```: ```MutableLiveData``` class have a function ```observe``` that will receive an event when the data held by the observed ```LiveData``` object changes. An observer object is then initialized with a lambda, a function that will be called to handle the event.
### LiveData
- Sometimes we want the data to be only readable, not editable -> ```LiveData``` instead of ```MutableLiveData```.
- From outside the ```ViewModel```, data should be readable, but not editable, so the data should be exposed as ```LiveData```.
- **IMPORTANT NOTES** Usually, ```LiveData``` delivers updates to the observers only when data changes. An exception to this behavior is that observers also receive updates when the observer changes from an inactive to an active state (An example is, when the game fragment is re-created after a screen rotation, it moves from an inactive to an active state). The observer in the fragment is re-connected to the existing ```ViewModel``` and receives the current data.
## Data binding with LiveData and ViewModel
- You can associate a ```ViewModel``` with a layout by using data binding.
- ```ViewModel``` objects hold the UI data. By passing ViewModel objects into the data binding, you can automate some of the communication between the views and the ViewModel objects.
- First declare add a data-binding variable of the type ```ViewModel``` in the layout xml file.
- Second, in the ```GameFragment``` file, pass the ```GameViewModel``` into the data binding.
  + Example: ```binding.gameViewModel = viewModel```
- Finally, to make the ```LiveData``` data binding work, set the current activity (the UI controller) as the lifecycle owner of the binding variable in the UI controller.
  + ```binding.lifecycleOwner = this```
- One of the ***EXTREMELY POWERFUL*** features of data binding when working with ```LiveData``` is that:
  + ```LiveData``` objects can be used as a data-binding source to automatically notify the UI about changes in the data.
  + Define in the xml file: ```android:text="@{gameViewModel.word}"```
## Basic structure & general settings of an Android Project  
- 'java' folder contains the main code for the app (i.e the Activity , v..v)
- 'res' folder holds resources - static contents used in the apps, including images, text strings, screen layouts, styles, and values such as hexadecimal colors or standard dimensions.
- 'manifests' folder contains files that provide essential info about your app to the Android system. (e.g the AndroidManifest.xml)
- 'Gradle Scripts': Gradle is a build automation system that describes the app's PROJECT STRUCTURE, CONFIG. & DEPENDENCIES
  + ' build.gradle(Project: ...)' : <project-level> contains the config. options that are common to all the modules that make up your project. Every Android project contains a single, top-level Gradle build file. This file defines the Gradle repositories and dependencies that are COMMON TO ALL MODULES in the project.
  + 'build.gradle(Module:app)' : <module-level> Each module has a build.gradle file of its own. The module-level build.gradle file allows you to configure build settings for each module. You may need to edit this build.gradle file when you change the SDK level that your app supports, or when you declare new dependencies in the dependencies section...

## About Layout XML files
- View groups are containers that hold other views and help specify the views' positions on the screen. 
- All the view & view groups you add to your layout are organized in a view hierarchy, with the topmost XML element as root of that hierarchy. When your app runs the view hierarchy in your XML layout file becomes a hierarchy of objects when the layout is inflated.
- Mostly the top-level element are either LinearLayout or ConstraintLayout.
- Instead of hardcoding strings in the layout or code file, it's a best practice to put all your app strings into a separate file <strings.xml>.
- The parent Layout element:  orientation attribute: Direction of how we want to group the positions of the views, either vertically or horizontally.
- layout_gravity: to define the place of the element
- It's a good practice to assign an ID to the View, so that we can later access the view in the code part by using findViewbyID() method.
### Constraint Layout
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
## Activities
- An Activity is a core Android class that is responsible for drawing an Android app UI and receiving input events.
- All activities have an associated layout file, which is an XML file in the app's resources. The layout file is named for the activity, for example activity_main.xml.
- The ```setContentView()``` method in MainActivity associates the layout with the activity, and inflates that layout when the activity is created.
- Layout inflation is a process where the views defined in the XML layout files are turned into (or "inflated" into) Kotlin view objects in memory. Once layout inflation happens, the Activity can draw these objects to the screen and dynamically modify them.

## Views & Using views
- All UI elements in the app layout are subclasses of the View class and are called views. TextView and Button are examples of views.
- View elements can be grouped inside a ViewGroup. A view group acts as a container for the views, or other view groups, within it. LinearLayout is an example of a view group that arranges its views linearly.
- A click handler is a method that is invoked when the user clicks or taps on a UI element. To attach a click-handler method to a view such as a button, use the setOnClickListener() method.
- VIEW STYLING: Content -> Padding -> Border -> Margin. Padding is the space inside the boundaries of a view or element. It is the space between the edges of the view and the view's content. Margin is the space added outside of the view's borders. It is the space from the edge of the view to its parent.
### STYLE
- A collection of attributes that specify the appearance and format for a view. A style can include font color, font size, background color, padding, margin, and other common attributes. Using styles also allows you to keep these common attributes in one location. Reusing a style gives your app a consistent look when you have multiple views. A style is extractable by right-clicking on the view component - Refactor > Extract Style.
### ScrollView
- A scroll view can contain only one child view. If you want to scroll more than one view, then add a ```ViewGroup``` such as a ```LinearLayout``` to the ```ScrollView```, and put the views to be scrolled inside that ```ViewGroup```.
## Drawables compatibility
- XML file images, a.k.a vector drawables - can scale without losing quality (that's why they are better than bitmap images like PNG or JPEG). 
- Vector drawables are only natively supported in versions of Android higher than API 21. In older versions, Gradle generates PNG images for those drawables when your app is built.
- You can specify that the Android Support Library should be used for vector drawables in older API versions with the ```vectorDrawables.useSupportLibrary = true``` configuration parameter in the ```build.gradle``` file. Once you've enabled the support library for vector drawables, use the ```app:srcCompat``` attribute in the <ImageView> element (instead of ```android:src```) to specify the vector drawable source for that image. The ```app``` namespace in your XML layout file is for attributes that come from either your custom code or from libraries, not from the core Android framework.  

## Fragments
- A ```fragment``` represents a behavior or a portion of user interface (UI) in an activity. You can combine multiple fragments in a single activity to build a multi-pane UI, and you can reuse a fragment in multiple activities. 
- A fragment ~ a modular section of an activity, something like a "sub activity":
  + A fragment has its own lifecycle and receives its own input events.
  + You can add or remove a fragment while the activity is running.
  + A fragment is defined in a Kotlin class.
  + A fragment's UI is defined in an XML layout file.
  
## Navigation components & paths
- A quite hard topics. Refer to ```https://codelabs.developers.google.com/codelabs/kotlin-android-training-add-navigation/index.html?index=..%2F..android-kotlin-fundamentals#2``` for more informations.
- Setup to use Android navigation library:
  + Add dependencies for ```navigation-fragment-ktx``` and ```navigation-ui-ktx``` in the module-level build.gradle file.
  + Add an ```ext``` variable for the ```navigationVersion``` in the project-level build.gradle file.
- **Navigation destinations** are fragments, activities, or other app components that the user navigates to. A **navigation graph** defines the possible paths from one navigation destination to the next.
  + To create a navigation graph, create a new Android resource file of type Navigation. This file defines the navigation flow through the app. The file is in the ```res/navigation``` folder, and it's typically called ```navigation.xml```.
  + To define the path from one destination to another, use the Navigation Graph to create an action that connects the destinations. In the ```navigation.xml``` file, each of these connections is represented as an action that has an ```ID```.
- A **navigation host** fragment, usually named ```NavHostFragment```, acts as a host for the fragments in the navigation graph:
  + As the user moves between destinations defined in the navigation graph, the ```NavHostFragment``` swaps the fragments in and out and manages the fragment back stack.
  + In the ```activity_main.xml``` layout file, the ```NavHostFragment``` is represented by a fragment element with the name ```android:name="androidx.navigation.fragment.NavHostFragment"```.
- To define which fragment is displayed when the user taps a view (for example a button), set the ```onClick``` listener for the view:
  + In the ```onClick``` listener, call ```findNavController().navigate()``` on the view.
  + Specify the ```ID``` of the ```action``` that leads to the destination.
- **Conditional navigation** navigates to one screen in one case, and to a different screen in another case. To create conditional navigation:
  + Use the Navigation Editor to create a connection from the starting fragment to each of the possible destination fragments.
  + Give each connection a unique ID.
  + In the click-listener method for the ```View```, add code to detect the conditions. Then call ```findNavController().navigate()``` on the view, passing in the ID for the appropriate action.
### The back stack
- The Android system keeps track of where users navigate on an Android-powered device. Each time the user goes to a new destination on the device, Android adds that destination to the ```back stack```.
- When the user presses the Back button, the app goes to the destination that's at the top of the back stack. BY DEFAULT, the top of the back stack is the screen that the user last viewed. 
- A navigation action can modify the back stack. You manage the back stack by setting the "pop" behavior for the actions that connect the fragments:
  + The ```popUpTo``` attribute of an action "pops up" the back stack to a given destination before navigating. (Destinations are removed from the back stack.)
  + If the ```popUpToInclusive``` attribute is false or is not set, popUpTo removes destinations up to the specified destination, but leaves the specified destination in the back stack.
  + If ```popUpToInclusive``` is set to true, the ```popUpTo``` attribute removes all destinations up to and including the given destination from the back stack.
  + If ```popUpToInclusive``` is true and ```popUpTo``` is set to the app's starting location, the action removes ALL app destinations from the back stack. The Back button takes the user all the way out of the app.

## Sharing datas between activities and fragments
### Safe Args
- To help catch errors caused by missing keys or mis-matched types when you pass data from one fragment to another, use a Gradle plugin called Safe Args.
- A key-value store, also known as a dictionary or associative array, is a data structure where you use a unique key (a string) to fetch the value associated with that key.
- To add Safe Args plugin, need to change dependencies settings in the gradle files (look at the docs for more details).
- After successfully adding the Safe Args plugin, it generates a ```NavDirection``` class for each fragment. (E.g: ```GameFragment``` n -> ```GameFragmentDirections```). So now we switches between fragments by, E.g:  ```view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(#Intent_Params#))```
- Upon receiving the shared arguments, use: ```val args = FragmentNameArgs.fromBundle(arguments!!)``` ... rand resolve them.
### Implicit Intents
- An Intent is a simple message object that's used to communicate between Android components. With an implicit intent, you initiate an activity without knowing which app or activity will handle the task. For example, if you want your app to take a photo, you typically don't care which app or activity performs the task. 
- When multiple Android apps can handle the same implicit intent, Android shows the user a chooser, so that the user can select an app to handle the request.
## The app bar
- The app bar, sometimes called the action bar, is a dedicated space for app branding and identity. For example, you can set the app bar's color. The app bar gives the user access to familiar navigation features such as an options menu. To access the options menu from the app bar, the user taps the icon with the three vertical dots.  
- By default, the user uses the system Back button to navigate to previous screens. However, Android apps can also have an on-screen Up button that appears at the top left of the app bar.
- The Up button navigates within the app, based on the hierarchical relationships between screens. The Up button never navigates the user out of the app.
       (This thing does not seem to be real good. Refer to commit ```Up Button``` for more infos)

# Some more advanced techniques
## Data binding: Eliminate ```findViewById()```
- Every time you use ```findViewById()``` to search for a view after the view is created or recreated, the Android system traverses the view hierarchy at runtime to find it. This would be a problem when the number of views turns large.
- SOLUTION: Create an object that contains a reference to each view. This object, called a ```Binding``` object, can be used by your whole app. This technique is called ```data binding```.
- Once a binding object has been created for your app, you can access the views, and other data, through the binding object, without having to traverse the view hierarchy or search for the data everytime you find the view.
- When the binding object is created, the compiler generates the names of the views in the binding object from the IDs of the views in the layout, converting them to camel case. So, for example, ```done_button``` is ```doneButton``` in the binding object, ```nickname_edit``` becomes ```nicknameEdit```, and ```nickname_text``` becomes ```nicknameText```.
- You can take advantage of data binding to make a data class directly available to a view. This technique simplifies the code, and is extremely valuable for handling more complex cases.
- To bind views to data, create a data class then add a ```<data>``` block inside the ```<layout>``` tag --> define a ```<variable>``` with a name, and a type that is the data class.
        
        (I do not like this much really. Will not use it... Oops seems like I still have to. )
- An useful link: ```https://codelabs.developers.google.com/codelabs/kotlin-android-training-data-binding-basics/index.html?index=..%2F..android-kotlin-fundamentals#5```

## Room database
![Room db](https://codelabs.developers.google.com/codelabs/kotlin-android-training-room-database/img/2bb4a0617e161b13.png)
- ```Room``` does all the hard work for you to get from Kotlin data classes to entities that can be stored in SQLite tables, and from function declarations to SQL queries.
- You must define each entity as an annotated data class, and the interactions as an annotated interface, a *data access object (DAO)*. Room uses these annotated classes to create tables in the database, and queries that act on the database.
- Regards to ```https://codelabs.developers.google.com/codelabs/kotlin-android-training-room-database/index.html?index=..%2F..android-kotlin-fundamentals#0``` for more info regarding Room databases.

## Coroutines
- Earlier we have used callbacks for multi-threading and asynchronous programming, but it has some drawbacks. Using ```callback``` makes the code harder to read, and also callback does not allow some programming features, most notably exceptions
- Another way is to use **Coroutine** - which lets you convert callback-based code to sequential code.
  + Coroutines are asynchronous and non-blocking.
  + Coroutines use *suspend* functions to make asynchronous code sequential.
- To use coroutines in Kotlin, you need three things:
  + **Job**: Basically, a job is anything that can be canceled. Every coroutine has a job, and you can use the job to cancel the coroutine. Jobs can be arranged into parent-child hierarchies. Canceling a parent job immediately cancels all the job's children, which is a lot more convenient than canceling each coroutine manually.
  + **Dispatcher**: The dispatcher sends off coroutines to run on various threads. For example, Dispatcher.Main runs tasks on the main thread, and Dispatcher.IO offloads blocking I/O tasks to a shared pool of threads.
  + **Scope**: A coroutine's scope defines the context in which the coroutine runs. A scope combines information about a coroutine's job and dispatcher. Scopes keep track of coroutines. When you launch a coroutine, it's "in a scope," which means that you've indicated which scope will keep track of the coroutine.

- Example pattern of a coroutine code in Kotlin:
```
fun someWorkNeedsToBeDone {
   uiScope.launch {
        suspendFunction()
   }
}

suspend fun suspendFunction() {
   withContext(Dispatchers.IO) {
       longrunningWork()
   }
}
```
## Triggering Navigation
- Other than ```onClick``` handlers that trigger navigation to a destination fragment, there are other events that can trigger navigation.
- We can also use the event of changing a ```LiveData``` (through using an ```observer```) to trigger navigation.

## LiveData Transformation
- Using ```Transformations.map(...)```, we can map the ```LiveData``` to another possible data types that can be displayed on screen.
- We can use this to trigger certain UI state as well.

## RecyclerView
- The greatest benefit of ```RecyclerView``` is that it is very efficient for large lists:
  + By default, ```RecyclerView``` only does work to process or draw items that are currently visible on the screen. For example, if your list has a thousand elements but only 10 elements are visible, ```RecyclerView``` does only enough work to draw 10 items on the screen. When the user scrolls, ```RecyclerView``` figures out what new items should be on the screen and does just enough work to display those items.
  + When an item scrolls off the screen, the item's views are recycled. That means the item is filled with new content that scrolls onto the screen. This `RecyclerView` behavior saves a lot of processing time and helps lists scroll fluidly.
  + When an item changes, instead of redrawing the entire list, `RecyclerView` can update that one item. This is a huge efficiency gain when displaying lists of complex items!
- Make use of **Adapter design pattern**: `RecyclerView` uses an adapter to transform app data into something the `RecyclerView` can display, without changing how the app stores and processes the data.
![RecyclerView basics](https://codelabs.developers.google.com/codelabs/kotlin-android-training-recyclerview-fundamentals/img/64eb759f01f5541f.png)

- To display your data in a `RecyclerView`, you need the following parts:
  + Data to display.
  + A `RecyclerView` instance defined in your layout file, to act as the container for the views.
  + A layout for each item of data.
  + A layout manager. Every `RecyclerView` needs a layout manager that tells it how to position items in the list. Android provides a `LinearLayoutManager`, which by default lays out the items in a vertical list of full width rows.
  + A view holder: The view holder extends the `ViewHolder` class. It contains the view information for displaying one item from the item's layout. View holders also add information that `RecyclerView` uses to efficiently move views around the screen.
  + An adapter: The adapter connects your data to the `RecyclerView`. It adapts the data so that it can be displayed in a `ViewHolder`. A `RecyclerView` uses the adapter to figure out how to display the data on the screen.
  
- In a production app, you might have multiple view holders, more complex adapters, and multiple developers making changes. You should structure your code so that everything related to a view holder is only in the view holder.
  + One way is to use Android's **Refactor > Extract > Function**
  + A good example: The `onCreateViewHolder()` method in the adapter currently inflates the view from the layout resource for the `ViewHolder`. However, inflation has nothing to do with the adapter, and everything to do with the `ViewHolder`. Inflation should happen in the `ViewHolder`.
  
### DiffUtil
- To tell `RecyclerView` that an item in the list has changed and needs to be updated, one way is to call `notifyDataSetChanged()`. As a result, `RecyclerView` rebinds and redraws every item in the list, including items that are not visible on screen. This is very inefficient, especially for large lists.
- `RecyclerView` has a class called `DiffUtil` which is for calculating the differences between two lists. `DiffUtil` takes an old list and a new list and figures out what's different. It finds items that were added, removed, or changed. Then it uses an algorithm called a *Eugene W. Myers's difference algorithm* to figure out the minimum number of changes to make from the old list to produce the new list.
- `DiffUtil` has a class called `ItemCallBack` that you extend in order to figure out the difference between two lists.

### ListAdapter
- To get some list management for free, you can use the `ListAdapter` class instead of `RecyclerView.Adapter`. However, if you use `ListAdapter` you have to write your own adapter for other layouts.

### Binding adapters & Data binding with RecyclerView
- If we want to transform the data to display out without using `Transformations`, we can use binding adapters to help data binding use them.
- To declare a binding adapter, define a method that takes an item and a view, and annotate the method with `@BindingAdapter`. In Kotlin, you can write the binding adapter as an extension function on the View. Pass in the name of the property that the adapter adapts. E.g: ```@BindingAdapter("sleepDurationFormatted")```
- In the XML layout, set an app property with the same name as the binding adapter. Pass in a variable with the data. For example:
```.app:sleepDurationFormatted="@{sleep}"```
- **IMPORTANT**: If you want to use functions in the xml file, have to declare the data binding object that is used first.
- Be careful that binding adapter item might be null - so remember to handle null exception. Unfortunately data binding makes it harder to debug.

### GridLayout
- Layout managers manage how the items in the `RecyclerView` are arranged. For more complicated use cases, implement a custom `LayoutManager`.
- From a design perspective, `GridLayout` is best used for lists of items that can be represented as icons or images. `GridLayout` arranges items in a grid of rows and columns. Assuming vertical scrolling, each item in a row takes up what's called a "span". You can customize how many spans an item takes up, creating more interesting grids without the need for a custom layout manager. Create an item layout for one item in the grid, and the layout manager takes care of arranging the items.
- You can set the `LayoutManager` for the RecyclerView either in the XML layout file that contains the `<RecyclerView>` element, or programmatically.
  + The `GridLayoutManager` constructor takes up to four arguments: a context, which is the activity, the number spans (columns, in the default vertical layout), an orientation (default is vertical), and whether it's a reverse layout (default is false). (E.g: `val manager = GridLayoutManager(activity, 3)`).
  + Then set the layout manager to your `RecyclerView`: E.g: `binding.sleepList.layoutManager = manager`
  
### Implementing click handlers for items within RecyclerView
- To make items in a `RecyclerView` respond to clicks, attach click listeners to list items in the `ViewHolder`, and handle clicks in the `ViewModel` - since the `ViewModel` is the one that holds data.
  + Create a listener class that takes a lambda and assigns it to an `onClick()` function.
  + Set the click listener on the view XML file. (`list_item_...` - Remember we need data binding here).
  + Pass the click listener to the adapter constructor, into the view holder, and add it to the binding object.
  + In the fragment that shows the recycler view, where you create the adapter, define a click listener by passing a lambda to the adapter --- since the fragment would hold the ViewModel object also.
  + Implement the click handler in the view model.
  
## Interacting with the Internet
- The `Retrofit` library is a client library that enables your app to make requests to a REST web service.
- To enable your app to make connections to the internet, add the `android.permission.INTERNET` permission in the Android manifest.
- The `Moshi` library is Android JSON parser that converts a JSON string into Kotlin objects. `Retrofit` has a converter that works with `Moshi`.
  + `Moshi` matches the keys in a JSON response with properties in a data object that have the same name.
  + To use a different property name for a key, annotate that property with the `@Json` annotation and the JSON key name.

### Retrofit and coroutines
- `Call` adapters let `Retrofit` create APIs that return something other than the default `Call` class. Use the `CoroutineCallAdapterFactory` class to replace the `Call` with a coroutine `Deferred`.
- Use the `await()` method on the `Deferred `object to cause your coroutine code to wait without blocking until the value is ready, and then the value is returned.

## Glide
- A library to download, buffer, decode, and cache images into your app.
  + To add options to the Glide request, use the `apply()` method. For example, use `apply()` with `placeholder()` to specify a loading drawable, and use `apply()` with `error()` to specify an error drawable. E.g in commit *Display img with Glide*

