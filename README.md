# ChuckNorrisJokes
Android Programming Challenge, based around the Internet Chuck Norris Database

# Android Programming Challenge
## Functional requirements
You are tasked with creating a simple application that will interact with The Internet Chuck Norris Database API (http://www.icndb.com/api/).

Start with reading the API documentation first:
http://www.icndb.com/api/

The Challenge consists of 3 subtasks, each represented with a Button on the starting screen:
1) Random joke
2) Text input
3) Never ending list

### Random Joke
When the ‘Random Joke’ button is pressed the app should request a single random joke from the server and then display it in a Dialog with a dismiss button.

### Text input
When the ‘Text input’ button is pressed the app should open a new screen (can be a new Activity or a Fragment – your decision) with a single Edittext and a ‘Submit’ Button.

Upon pressing the ‘Submit’ Button, the app should request a random joke with a custom main character as described in the Changing the name of the main character section of the API docs and show it in a Dialog. App should handle the input validation and also first name / last name splitting.

### Never-ending list
When the ‘Never-ending list button is pressed the app should open a new screen (can be a new Activity or a Fragment – your decision) that contains a list of random jokes. Jokes should be requested asynchronously, in batches of 20, from the server. When the user scrolls
to the bottom of the list, the list should show a loading footer to indicate that more items are being requested.

Note – since requested items are random, we don’t care if we have duplicates in the list.

### Technical requirements
It is enough that the application can be run on a random Android phone with Android 4.0+. We may want to run it on a random Android tablet to see how it does – but the primary devices are phones.

### Evaluation criteria
The functionality of the application is simple because we want to make sure that:
* You care about the user experience
* You use latest Android design and UX patterns
* You create elegant, clean and maintainable code
* You are paying attention to details

In case of any doubts, use your own judgment to take appropriate decision to create the best (in your opinion) solution. This is just a test to show your approach to problem solving and software development.

### Deliverables
We expect you to create a complete Android Studio project in version controlled repository, preferably with several commits so that we can see the progress of creating the app.
Feel free to use any library you want.

### Bonus tasks
The tasks described below should considered only in case when you finished the 3 requirements above really quickly – they won’t impact our decision, it’s just a place to show off your extra skills and Android knowledge.
* Make app handle screen rotation correctly (do not restart network requests, lists shouldn’t change scroll position etc.)
* Since some jokes are inappropriate, add a ‘No explicit’ checkbox to the main screen which, when checked, should alter all requests
made to the server to exclude explicit category (as described in Limiting categories section of the API docs)
