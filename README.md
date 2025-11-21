FootballPro – Java Player Management System

FootballPro is a simple, console-based application built in Java.
The purpose of the project is to demonstrate Object-Oriented Programming (OOP) along with several modern Java features.
The application allows users to manage football players, record match details, calculate statistics, and perform basic operations such as adding, listing, searching, and deleting players.

1. Overview

This project is organised into clear packages using a modular structure:
	•	model – Player classes (Striker, Goalkeeper, Defender), PlayerType enum, and the Statistics class
	•	service – Service interface and PlayerManager implementation
	•	util – Utility helper methods
	•	record – Java Record for match details
	•	exception – Custom PlayerNotFoundException
	•	root package – Main class with the console menu

The structure is kept simple and readable so that each class has a single clear responsibility.

⸻

2. Features

✔ Add Player

Users can add a new Striker, Goalkeeper, or Defender by entering:
	•	Name
	•	Age
	•	Type
	•	Score / Saves / Tackles

✔ List Players

Displays each player’s:
	•	Name
	•	Age
	•	Type
	•	Score
	•	Matches played
	•	Average performance (calculated through Statistics)
	•	Polymorphic stats via overridden displayStats()

✔ Search Player

Searches for a player by name using Java streams and Optional.
If the player does not exist, a custom PlayerNotFoundException is thrown and handled gracefully.

✔ Delete Player

Allows deleting a player by name.
If the name is not found, the same custom exception is thrown.

✔ Record Demo

Shows how Java Records are used to store match summary details.

✔ Average Demo

Demonstrates:
	•	Method overloading
	•	Varargs
Using the FootballUtils.average() method.

✔ Player Statistics

Users can set the number of matches played for a player.
The existing score is combined with matches to calculate an average.

✔ Exit Application

Ends the program with a menu-driven switch expression.

⸻

3. Java Concepts Demonstrated

This project intentionally uses a wide range of Java concepts, including:

Core OOP Concepts
	•	Classes and objects
	•	Encapsulation
	•	Inheritance
	•	Polymorphism
	•	Method overriding

Advanced Java Features
	•	Sealed classes → Player
	•	Records → MatchRecord
	•	Enums → PlayerType
	•	Lambdas & Method References → In PlayerManager
	•	Predicate Functional Interface
	•	Switch expressions (Java 21 style)
	•	Local Variable Type Inference (var)
	•	Optional API
	•	Java Date API (LocalDate)
	•	Varargs & method overloading
	•	StringBuilder
	•	Custom exception handling → PlayerNotFoundException

Design Patterns / Good Practices
	•	Defensive copying in service class
	•	Multi-package application layout
	•	Separation of concerns
	•	Use of immutable Statistics class
	•	Git version control with meaningful commits


  
