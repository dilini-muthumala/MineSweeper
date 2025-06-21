# Minesweeper CLI (TDD-Driven Java Game)

A command-line implementation of the classic Minesweeper game written in Java and built using Test-Driven Development (TDD). Designed to be testable, modular, and runnable as a standalone JAR.

---

## 🎯 Features

* Text-based Minesweeper game on a square grid
* Randomized mine placement with user-defined grid size and mine count
* Recursive revealing of empty cells
* Win/loss detection and feedback
* Fully testable components with input validation
* Input format: coordinates like `A1`, `C3`, etc.
* Plays continuously until manually exited
* Unit tested and coverage-checked using **JUnit 5** and **JaCoCo**

---

## ⚡ Quick Start

```bash
# Clone the repo
$ git clone https://github.com/your-username/minesweeper-cli.git
$ cd minesweeper-cli

# Build and run the game
$ mvn clean package
$ java -jar target/minesweeper-cli-1.0-SNAPSHOT.jar
```

---

## 🕹️ Usage

When you start the game, you'll interact through the command line. Follow the on-screen prompts to play.

### Start the Game

```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid):
> 5
Enter the number of mines to place on the grid (maximum is 8):
> 6

Here is your updated minefield:
  1 2 3 4 5
A _ _ _ _ _
B _ _ _ _ _
C _ _ _ _ _
D _ _ _ _ _
E _ _ _ _ _

Select a square to reveal (e.g. A1): A2
This square contains 1 adjacent mines.

Here is your updated minefield:
  1 2 3 4 5
A _ 1 _ _ _
B _ _ _ _ _
C _ _ _ _ _
D _ _ _ _ _
E _ _ _ _ _

Select a square to reveal (e.g. A1): A1
This square contains 1 adjacent mines.

Here is your updated minefield:
  1 2 3 4 5
A 1 1 _ _ _
B _ _ _ _ _
C _ _ _ _ _
D _ _ _ _ _
E _ _ _ _ _

Select a square to reveal (e.g. A1): B1
This square contains 1 adjacent mines.

Here is your updated minefield:
  1 2 3 4 5
A 1 1 _ _ _
B 1 _ _ _ _
C _ _ _ _ _
D _ _ _ _ _
E _ _ _ _ _

Select a square to reveal (e.g. A1): B2
Oh no, you detonated a mine! Game over.

Here is your updated minefield:
  1 2 3 4 5
A 1 1 _ _ _
B 1 * _ _ _
C _ _ _ _ *
D _ _ _ * _
E * * _ _ *

Oh no, you detonated a mine! Game over.
Press Enter to play again...
```

Use coordinate input like `A1`, `C3`, etc., to reveal squares. Avoid the mines!

---

## 🏗️ Building from Source

Make sure you have Java 17 and Maven installed.

```bash
mvn clean package
```

Output JAR: `target/minesweeper-cli-1.0-SNAPSHOT.jar`

---

## ✅ Running Tests

This project uses **JUnit 5**. To execute all unit tests:

```bash
mvn test
```

Test classes are located under `src/test/java`.

---

## 📊 Code Coverage

This project uses **JaCoCo** to generate code coverage reports.

<span style="color:red; font-weight:bold">⚠️ Code coverage threshold of 80% is enforced. The build will fail if coverage is below this level.</span>

Generate the report:

```bash
mvn clean verify
```

View the report:

```bash
open target/site/jacoco/index.html   # or use your browser to open this file manually
```

---

## 🐳 Run with Docker

Build the Docker image:

```bash
docker build -t minesweeper-cli .
```

Run the game inside a container:

```bash
docker run -it --rm minesweeper-cli
```

### 🐳 Dockerfile

Save this as `Dockerfile` in the root directory:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/minesweeper-cli-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

> Ensure you've run `mvn clean package` before building the Docker image.

---

## 📁 Project Structure

```
com/minesweeper
├── app          → Game launcher (MinesweeperApp)
├── board        → Game board and cell logic
├── config       → Game configuration logic
├── game         → Core game state and move handling
└── ui           → Console UI and I/O management
```

---

## 🧩 Basic Gameplay

### Select your difficulty level:

* **Easy**: 9x9 grid with 10 mines
* **Medium**: 16x16 grid with 40 mines
* **Hard**: 30x30 grid with 160 mines

You can also choose a custom grid and mine count when prompted.

### Gameplay

* Type coordinates like `A1`, `B2`, etc. to reveal a cell.
* The numbers show how many adjacent cells contain mines.
* Empty cells will automatically reveal neighbors until numbers are encountered.
* Use **flags** (coming soon) to mark suspected mines.

### Winning / Losing

* If you hit a mine... **game over, homie.** 💥
* Win by revealing all cells that do **not** contain mines.

*(Get it? MINE... SWEEPER...? 😄)*

---

## 🛠️ Technologies Used

The good old Three Amigos — but CLI style:

* **Java 17**
* **JUnit 5** (Testing)
* **JaCoCo** (Code Coverage)
* **Maven** (Build Tool)
* **Docker** (Containerization)

---

## 📃 License

This project is licensed under the MIT License.

---

## 👤 Author

**Dilini Asanga**
🧑‍💻 Java Developer & Software Engineer
🌐 [LinkedIn](https://www.linkedin.com/in/dilinimuthumala/)
📦 [GitHub Repository](https://github.com/dilini-muthumala/MineSweeper)
✉️ Feel free to contribute or reach out with feedback!