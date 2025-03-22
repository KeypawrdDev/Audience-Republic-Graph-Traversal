
# 📊 Graph Traversal and Analysis Project

This project implements **graph traversal algorithms**, **distance metrics**, and **random graph generation** in both **Kotlin** and **Clojure**.  
It demonstrates **DFS**, **BFS**, **Dijkstra's algorithm**, and graph distance properties such as **eccentricity**, **radius**, and **diameter**.

---

## ✅ Features

- **DFS (Depth-First Search)**
- **BFS (Breadth-First Search)**
- **Dijkstra's Algorithm (Shortest Path)**
- **Eccentricity Calculation**
- **Radius Calculation**
- **Diameter Calculation**
- **Random Graph Generation** (connected, directed, weighted)

---

## 🛠️ Technologies Used

| Language   | Purpose                              |
|------------|--------------------------------------|
| **Kotlin** | Primary implementation and CLI app   |
| **Clojure**| Functional translation, AI-assisted  |

---

## 🚀 Getting Started

### Kotlin Version

#### Prerequisites
- Kotlin compiler (or IntelliJ IDEA)
- JDK 11+

#### Run via CLI
```bash
kotlinc Main.kt -include-runtime -d graph.jar
java -jar graph.jar -N 6 -S 8
```

Or use IntelliJ’s **Run** button with **Program arguments**:
```
-N 6 -S 8
```

#### Features
- Generates a random graph with `N` nodes and `S` edges.
- Prints the adjacency list.
- Displays:
  - Radius and Diameter.
  - Shortest path between two randomly selected nodes.
  - Eccentricity of one random node.

---

### Clojure Version

#### Prerequisites
- Clojure CLI installed ([Getting Started Guide](https://clojure.org/guides/getting_started))

#### Run the Program
```bash
clojure graph_project.clj
```

#### Features
- Random graph generation with default nodes and edges.
- DFS and BFS graph traversal.
- Dijkstra shortest path.
- Radius, Diameter, and Eccentricity calculations.

---

## 🗂️ Project Structure

### Kotlin
```
src/
 ├── Main.kt                 # CLI interface
 ├── Graph.kt                # Graph data structures
 ├── Edge.kt                 # Edge definition
 ├── Traversal.kt            # DFS and BFS algorithms
 ├── Dijkstra.kt             # Dijkstra’s algorithm
 ├── GraphProperties.kt      # Eccentricity, radius, diameter
 ├── RandomGraphGenerator.kt # Random graph generation
 └── PrintGraph.kt           # Prints graph structure
```

### Clojure
```
graph_project.clj            # All core logic and CLI in one file
```

---

## 🤖 AI Tools and Adaptations

As part of **Audience Republic's** challenge requirements, AI tools were used to assist in translating the Kotlin implementation to Clojure.

### Adaptations
- Translated imperative Kotlin logic to idiomatic Clojure functional patterns.
- Replaced mutable structures with immutable maps, vectors, and recursion.
- Used `sorted-set` for Dijkstra’s priority queue functionality.
- Followed functional programming practices to align with Clojure's philosophy.

---

## 💻 Example CLI Usage (Kotlin)

```bash
graph -N 6 -S 8
```

```
📌 Random Graph:
{
  :1 [(:2 1) (:3 2)]
  :2 [(:4 4)]
  :3 [(:4 2)]
  :4 []
}

✅ Radius: 19
✅ Diameter: 34

🔎 Shortest path from node 1 to node 6: [1, 3, 6] with total weight 21
🔎 Eccentricity of node 3: 19
```
