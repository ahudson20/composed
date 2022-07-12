# Booster

Created using Jetpack Compose. It is a "single" activity app (MVVM) - the main screen functionality is a single activity. A debug apk is already build and included above.

Jetpack Compose: 1.1.0-beta01\
Target SDK version: 32\
Min SDK Version: 21


### TODO:
1. Design the investor type screen:
  * Create composables to display the data nicely - it all alreday exists in the state.
  * Map the data to the pie chart - same as above^
3. Design the form
  * Map form design to the state - the current form exists in the state.
  * In the viewModel onClick - submit the data to 'me@example.com', return to home screen, update state to submitted.
  * create new form validators
4. Tidy up the codebase.
  * Error handling in certain cases doesn't exist but should - error cases are in the state already, just need to handle them + create composables to show the error.
5. Unit testing + fix the UI tests.
