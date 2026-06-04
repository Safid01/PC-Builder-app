# How to Run PC Builder

## Prerequisites

- **Java JDK 8 or later** installed on your machine
- Verify your installation by running:
  ```bash
  java -version
  javac -version
  ```
  If either command is not found, download the JDK from [https://adoptium.net](https://adoptium.net).

---

## Step 1 — Fix the Image Paths

Before compiling, open `PCBuilder.java` and find this line inside the constructor:

```java
BackgroundPanel mainPanel = new BackgroundPanel("C:\\Users\\safid\\Downloads\\...\\Images\\bg.jpg");
```

Replace it with a relative path:

```java
BackgroundPanel mainPanel = new BackgroundPanel("Images/bg.jpg");
```

Also find the icon line near the bottom of the constructor:

```java
setIconImage(new ImageIcon("C:\\Users\\safid\\Downloads\\...\\Images\\pc.png").getImage());
```

Replace with:

```java
setIconImage(new ImageIcon("Images/pc.png").getImage());
```

Then open `SavedBuildsFrame.java` and do the same for its icon line:

```java
// Before
new ImageIcon("C:\\Users\\safid\\Downloads\\...\\Images\\save.png")

// After
new ImageIcon("Images/save.png")
```

> **Why?** The original paths are hardcoded to a specific machine. Using relative paths makes the project portable.

---

## Step 2 — Compile

Open a terminal and `cd` into the `PCBuilder/` folder (where all the `.java` files are).

```bash
cd path/to/PCBuilder
```

Compile all Java files at once:

```bash
javac *.java
```

You should see no errors. Several `.class` files will be generated in the same directory.

---

## Step 3 — Run

```bash
java Main
```

The PC Builder window should open.

---

## Expected Output

On launch you will see a 610×800 window with:

- A background image and a **"Build your own PC"** title
- Three tabs: **Core Components**, **Peripherals**, **Accessories**
- A live **Total Amount** counter in BDT (৳)
- Three buttons at the bottom: **Save Build**, **Load Build**, **Clear All**

Saved builds are written to `SavedBuilds.txt` in the same directory you ran the program from.

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| `javac: command not found` | JDK is not installed or not on PATH — install from [adoptium.net](https://adoptium.net) |
| `error: cannot find symbol` | Make sure you are inside the `PCBuilder/` directory when compiling |
| Background image not showing | Double-check the relative paths in Step 1 |
| Font looks wrong | The `Baloo Da 2` font is loaded by name; if it is not registered on your system, Java will fall back to a default font — this is harmless |
| `SavedBuilds.txt` not found | It is created automatically on first save — no action needed |