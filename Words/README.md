![ic_launcher_words](src/main/res/mipmap-xhdpi/ic_launcher_words_round.png?raw=true) ![ic_launcher_words](images/Screenshot_20220714_151809.png?raw=true)

# Words

Words app allows you to select a letter and use Intents to navigate to an Activity that presents a
number of words starting with that letter. Each word can be looked up via a web search.

Words app contains a scrollable list of 26 letters A to Z in a RecyclerView. The orientation of the
RecyclerView can be changed between a vertical list or a grid of items.

The app demonstrates the use of Intents in two ways:

- to navigate inside an app by specifying an explicit destination
- allowing Android to service the Intent using the apps and resources present on the device

## Topics covered in this module

- Intent, extras
- Menu item
- Layout manager(s)
- Fragment
- Navigation component: NavHost, graph, controller, safe args, testing

## Result

![Words](https://user-images.githubusercontent.com/29587914/178991718-8e6a445f-eb09-429b-b580-1ce73d08d36e.gif)

### Navigation test

Check if the screen navigate to word list when letter list item is clicked.

![NavigationTests](images/Screenshot_20220716_094757.png?raw=true)

[NavigationTests.webm](https://user-images.githubusercontent.com/29587914/179336792-4711f137-cb46-4572-9f51-f5d68616e4d3.webm)
