# Wordle-CLI-Game

Welcome to my take on the popular Wordle game! This application is a Java-based CLI implementation of the popular word-guessing game Wordle. The objective is to guess a secret five-letter word within six attempts. To play, pull down the repo, run the main program and play directly from the terminal.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Gameplay Instructions](#gameplay-instructions)
- [Contributing](#contributing)
- [Additional Notes](#additional-notes)

## Prerequisites

Before you begin, ensure you have met the following requirements:
- You have installed Java 11 or later.
- You have installed Maven 3.8.1 or later
- You have a basic understanding of the command line or terminal.

## Installation

To install this project, follow these steps:

1. **Clone the repository:**

    ```sh
    git clone https://github.com/conorginty/Wordle-CLI-Game.git
    cd Word-Guesser-Game
    ```

2. **Build the project:**

   Ensure you have `mvn` installed and set up correctly. Then, compile the Java files:

    ```sh
    mvn clean install
    ```

3. **Run the application:**

   From the base directory, you can run the `Main` class in /Wordle-Game using the following command:

    ```sh
    mvn -pl Wordle-Game exec:java -Dexec.mainClass="com.wordle.Main"
    ```

## Usage

Once the application is running, follow the on-screen prompts to play the game.

## Gameplay Instructions

1. **Start the game:**

   When you run the application, it will prompt you with a welcome message indicating that the game has started.

2. **Make a guess:**

    - Enter a five-letter word as your guess.
    - The program will provide feedback for each letter in your guess:
        - A letter in the correct position will be marked accordingly as Green.
        - A letter that is in the word but in the wrong position will also be indicated, and marked accordingly as Yellow.
        - Letters that are not in the word will be marked as Grey.
    - This colour scheme will be reflected in an accompanying alphabet to reflect which letters the player has already selected.

3. **Game end conditions:**

    - If you guess the correct word within six attempts, the game congratulates you and displays your number of attempts.
    - If you exhaust all six attempts without guessing the word, the game reveals the correct word and prompts you to try again next time. The game ends, and you must run the aforementioned mvn command to run the Main class again to play another game.

## Contributing

Contributions are always welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.
---

## Additional Notes:

- The game will prompt you to try another word if you have used an 'invalid' word (one that doesn't meet the word length criteria, has already been guessed etc).
- The game can be edited to change the length of the target word with the `WORD_LENGTH` constant in Main.
- The `usedLettersMap` is displayed during each guess to help users keep track of their guessed letters.

Feel free to customize the content, structure, or sections as needed to better fit your project.
And above all, I hope you enjoy the game!