![ic_launcher_lunchtray](src/main/res/mipmap-xhdpi/ic_launcher_lunchtray_round.png?raw=true) ![ic_launcher_lunchtray](images/Screenshot_20220719_095616.png?raw=true)

# Lunch Tray

Independent project for the final pathway of Android Basics in Kotlin Unit 3. This app contains an
order flow for lunch with options for entree, side dish, and accompaniment. The order details get
displayed on an order summary screen.

## Topics covered in this module

- ViewModel
- Data binding
- Navigation
- Instrumentation tests

## Result

### Navigation tests

![Navigation tests](images/Screenshot_20220719_132938.png?raw=true)

[NavigationTests.webm](https://user-images.githubusercontent.com/29587914/179683449-06c619c2-f612-420d-a429-8a2e2ae9e571.webm)

### Entree Screen tests

Check if all entree menus are displayed

![Entree menu tests](images/Screenshot_20220719_132559.png?raw=true)

[EntreeMenuItemContent.webm](https://user-images.githubusercontent.com/29587914/179703637-90e37de3-c2bd-4186-b85b-15aeedfdc317.webm)

Given a choice of entree, check if the right amount of subtotal is displayed

![Entree subtotal tests](images/Screenshot_20220719_133715.png?raw=true)

[RadioButtonsUpdateEntreeMenuSubtotal.webm](https://user-images.githubusercontent.com/29587914/179727202-208e3f5d-1a19-473f-93a7-b83b3603dcdd.webm)

### Side Screen tests

Check if all side dish menus are displayed

![Side menu tests](images/Screenshot_20220719_172922.png?raw=true)

[SideMenuItemContent.webm](https://user-images.githubusercontent.com/29587914/179730628-c3af49a9-da75-4ada-9c39-e4170df852de.webm)

Given a choice of side dish, check if the right amount of subtotal is displayed

![Side subtotal tests](images/Screenshot_20220719_172527.png?raw=true)

[RadioButtonsUpdateSideMenuSubtotal.webm](https://user-images.githubusercontent.com/29587914/179730619-83c40b57-6bac-4544-b4b1-9c11437258e8.webm)

### Accompaniment Screen tests

Check if all accompaniment menus are displayed

![Accompaniment menu tests](images/Screenshot_20220719_175539.png?raw=true)

[AccompanimentMenuItemContent.webm](https://user-images.githubusercontent.com/29587914/179740707-c7115d7a-e9e4-4425-936c-3d43c416aa64.webm)

Given a choice of accompaniment, check if the right amount of subtotal is displayed

![Accompaniment subtotal tests](images/Screenshot_20220719_175737.png?raw=true)

[RadioButtonsUpdateAccompanimentMenuSubtotal.webm](https://user-images.githubusercontent.com/29587914/179740722-4c4d2f65-414d-4ca1-9ae0-b78636714124.webm)

### Checkout Screen tests

Given a choice of entree, side dish and accompaniment, check if the right amount of subtotal is
displayed

![Checkout subtotal tests](images/Screenshot_20220719_195350.png?raw=true)

[SubtotalTaxTotalInCheckout.webm](https://user-images.githubusercontent.com/29587914/179756323-ea268bea-921b-44e8-bb04-f98807ba4913.webm)

Given a choice of entree, side dish and accompaniment, check if the right amount of subtotal, tax
and total are all displayed

![Checkout total tests](images/Screenshot_20220719_195632.png?raw=true)

[SubtotalUpdatesInFullOrderFlow.webm](https://user-images.githubusercontent.com/29587914/179756344-a6164498-1eeb-4de9-94ab-203ba591d989.webm)

### Cancel Order tests

From entree menu

![Cancel order from entree menu tests](images/Screenshot_20220720_055243.png?raw=true)

[CancelFromEntreeMenu.webm](https://user-images.githubusercontent.com/29587914/179861905-826daa4d-a0e5-4a4f-b907-033b1f0639a3.webm)

From side menu

![Cancel order from side menu tests](images/Screenshot_20220720_060202.png?raw=true)

[CancelFromSideMenu.webm](https://user-images.githubusercontent.com/29587914/179862874-a362a880-9013-414a-a6b5-f2b2309a5a34.webm)

From accompaniment menu

![Cancel order from accompaniment menu tests](images/Screenshot_20220720_064341.png?raw=true)

[CancelFromAccompanimentMenu.webm](https://user-images.githubusercontent.com/29587914/179866516-ee2ba4aa-f33d-4269-89db-c97add30adba.webm)

From checkout

![Cancel order from checkout tests](images/Screenshot_20220720_064902.png?raw=true)

[CancelFromCheckout.webm](https://user-images.githubusercontent.com/29587914/179866987-b427eb2e-6cb5-44ac-81cf-c77936ba1df9.webm)

### Submit Order tests

![Submit order tests](images/Screenshot_20220720_065540.png?raw=true)

[SubmitOrder.webm](https://user-images.githubusercontent.com/29587914/179868110-90fc66ee-c05f-4170-babb-36c8285c494f.webm)
