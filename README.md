# 🖥️ PC Builder

A Java Swing desktop application that lets you configure and price a custom PC build by selecting components from categorized dropdown menus. Builds can be saved, loaded, and deleted — all persisted locally to a text file.

---

## Features

- **Component selection** across three categories: Core Components, Peripherals, and Accessories
- **Live price total** that updates as you select or change components
- **Required field validation** — components marked with `*` must be selected before saving
- **Save / Load / Delete builds** persisted to `SavedBuilds.txt`
- **Nimbus Look & Feel** with a custom background image and styled UI

---

## Project Structure

```
PCBuilder/
├── Main.java               # Entry point; sets Nimbus L&F and launches the app
├── PCBuilder.java          # Main JFrame — UI layout, component logic, save/load/clear
├── PCComponent.java        # Base class for all components (name + price)
├── CoreComponent.java      # Subclass for core PC parts (CPU, RAM, etc.)
├── Peripheral.java         # Subclass for peripherals (monitor, keyboard, mouse)
├── Accessory.java          # Subclass for accessories (fans, cables)
├── BuildIO.java            # File I/O — save, load, and delete builds from SavedBuilds.txt
├── SavedBuildsFrame.java   # Secondary JFrame to browse, load, or delete saved builds
├── Images/
│   ├── bg.jpg              # Background image
│   ├── pc.png              # App window icon
│   └── save.png            # Saved builds window icon
└── Fonts/
    └── BalooDa2-VariableFont_wght.ttf
```

---

## Component Categories

| Tab | Components |
|-----|-----------|
| **Core Components** | Processor\*, Motherboard\*, CPU Cooler\*, RAM-1\*, RAM-2, Graphics Card, Storage\*, Power Supply\*, Case\* |
| **Peripherals** | Monitor, Keyboard, Mouse |
| **Accessories** | Cooling Fan, Cables |

> Components marked with `*` are required before a build can be saved.

---

## Class Design

```
PCComponent  (base)
├── CoreComponent
├── Peripheral
└── Accessory
```

`PCComponent` holds `name` and `price`, and its `toString()` formats them for the dropdown (e.g. `Intel i9-12900K (55000৳)`). All three subclasses currently extend it without adding fields — the hierarchy is in place for future differentiation.

---

## Getting Started

### Requirements

- Java 8 or later
- No external libraries needed (pure Java Swing)

### Compile & Run

```bash
# From the PCBuilder/ directory
javac *.java
java Main
```

### ⚠️ Image Paths

`PCBuilder.java` and `SavedBuildsFrame.java` currently use **hardcoded absolute paths** for the background and icon images:

```java
// PCBuilder.java
new BackgroundPanel("C:\\Users\\safid\\Downloads\\...\\Images\\bg.jpg");

// SavedBuildsFrame.java
new ImageIcon("C:\\Users\\safid\\Downloads\\...\\Images\\save.png")
```

Before running on a different machine, update these to relative paths or use `getClass().getResource()`:

```java
// Relative path example
new BackgroundPanel("Images/bg.jpg");
new ImageIcon("Images/pc.png");
```

---

## Saved Builds

Builds are written to `SavedBuilds.txt` in the working directory, with entries separated by `-----`. Example:

```
Processor: Intel i9-12900K (55000৳)
Motherboard: ASUS ROG Strix Z690 (25000৳)
...
-----
```

The **Saved Builds** window lets you load a previous build back into the UI or delete it permanently.

---

## Possible Improvements

- Replace hardcoded image paths with relative or classpath-based loading
- Load component data from a config file (JSON/CSV) instead of hardcoding in `loadComponents()`
- Add duplicate build detection before saving
- Support exporting a build summary as PDF or text