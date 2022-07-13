![ic_launcher_tiptime](src/main/res/mipmap-xhdpi/ic_launcher_affirmations_round.png?raw=true) ![ic_launcher_tiptime](images/Screenshot_20220712_171057.png?raw=true)

# Affirmations

A simple app that displays ten positive affirmations as text in a scrolling list.

## Topics covered in this module

- Kotlin data class, singleton object
- Organize code into packages
- RecyclerView: view holder, adapter, item layout, layout manager
- Resource annotation
- Unit testing by mocking complex object we don't own

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

Failed to run, yield error [#1](https://github.com/imsyf/AndroidBasicsInKotlin/issues/1).
![Screenshot from 2022-07-13 11-07-24](https://user-images.githubusercontent.com/29587914/178648611-d595ec7d-cdb3-4a34-919d-386d754f8ba3.png)
