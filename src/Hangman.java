import java.util.Scanner;

public class Hangman {
    private static final String[] WORDS = {"apple", "banana", "cherry", "date", "elderberry"};
    private static String wordToGuess;
    private static char[] guessedWord;
    private static int attemptsLeft;
    private static boolean[] guessedLetters;

    public static void main(String[] args) {
        initializeGame();
        playGame();
        endGame();
    }

    private static void initializeGame() {
        wordToGuess = WORDS[(int) (Math.random() * WORDS.length)];
        guessedWord = new char[wordToGuess.length()];
        guessedLetters = new boolean[26];
        attemptsLeft = 6;

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (attemptsLeft > 0 && !isWordGuessed()) {
            System.out.println("Guess the word: " + String.valueOf(guessedWord));
            System.out.println("Left attempts: " + attemptsLeft);
            System.out.print("Type the letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (guess < 'a' || guess > 'z') {
                System.out.println("Please enter valid letter.");
                continue;
            }

            if (guessedLetters[guess - 'a']) {
                System.out.println("This letter is already guessed.Try another.");
                continue;
            }

            guessedLetters[guess - 'a'] = true;

            if (wordToGuess.indexOf(guess) >= 0) {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guess) {
                        guessedWord[i] = guess;
                    }
                }
            } else {
                attemptsLeft--;
                System.out.println("Wrong guess.Try again.");
            }
        }

        scanner.close();
    }

    private static boolean isWordGuessed() {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private static void endGame() {
        if (isWordGuessed()) {
            System.out.println("Congratulations!You guessed the word: " + wordToGuess);
        } else {
            System.out.println("You're out of tries.The word was: " + wordToGuess);
        }
    }
}

