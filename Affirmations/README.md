![ic_launcher_tiptime](src/main/res/mipmap-xhdpi/ic_launcher_affirmations_round.png?raw=true) ![ic_launcher_tiptime](images/Screenshot_20220712_171057.png?raw=true)

# Affirmations

A simple app that displays ten positive affirmations as text in a scrolling list.

## Topics covered in this module

- Kotlin data class, singleton object
- Organize code into packages
- RecyclerView: view holder, adapter, item layout, layout manager
- Resource annotation
- Unit testing by mocking complex object we don't own
- UI testing
- Multidex

## Result

<table>
  <tr>
    <th>Light</th>
    <th>Dark</th>
  </tr>
  <tr>
    <td>
      <img src="images/Screenshot_20220712_162810.png?raw=true" />
    </td>
    <td>
      <img src="images/Screenshot_20220712_162517.png?raw=true" />
    </td>
  </tr>
</table>

## Tests result

### Unit test

Check if `ItemAdapter` item count is equal to the number of data passed to it.

![Unit test](images/Screenshot_20220713_102647.png?raw=true)

### UI test

Check if the last item is displayed.

![UI test](images/Screenshot_20220713_134041.png?raw=true)

[Recording from 2022-07-13 13-44-31.webm](https://user-images.githubusercontent.com/29587914/178668942-173dd91c-92de-41fa-aec5-5a31fd8e5a6b.webm)
