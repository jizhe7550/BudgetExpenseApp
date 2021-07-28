# BudgetExpenseApp
a simple budgeting/expense app. Users can add transaction record in a list. When a user selects a transaction, will show a second screen that will display a more detailed view of the transaction data and can edit them.

This app basicly follows this Architecture:
![image](https://user-images.githubusercontent.com/46810206/127237102-a0c7eaac-9c31-4174-acd0-c0c46360db2d.png)

Business layer: kotlin stuff (unit testing)
Framework layer: Android stuff (instrument testing)

Techs:
1. Single activtiy architecture.
2. clean architecture + MVI
3. Jetpack (Navigation graph, livedata, room etc.)

TODOs:
More UI testing, unit testing.

Improvement:
1. change custom coroutineScope to viewModelScope.
2. test coverage.



