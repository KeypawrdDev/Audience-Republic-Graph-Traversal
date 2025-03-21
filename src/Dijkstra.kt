import java.util.PriorityQueue

fun dijkstra(graph: Graph, start: Int, end: Int): List<Int> {
    val distances = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
    val previous = mutableMapOf<Int, Int?>()
    val visited = mutableSetOf<Int>()

    val queue = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
    distances[start] = 0
    queue.add(start to 0)

    while (queue.isNotEmpty()) {
        val (currentNode, currentDist) = queue.poll()

        if (currentNode in visited) continue
        visited.add(currentNode)

        if (currentNode == end) break

        val neighbors = graph[currentNode] ?: continue
        for (edge in neighbors) {
            val neighbor = edge.to
            val weight = edge.weight
            val newDist = currentDist + weight

            if (newDist < distances.getValue(neighbor)) {
                distances[neighbor] = newDist
                previous[neighbor] = currentNode
                queue.add(neighbor to newDist)
            }
        }
    }

    val path = mutableListOf<Int>()
    var current: Int? = end
    while (current != null) {
        path.add(0, current)
        current = previous[current]
    }

    if (path.firstOrNull() == start) {
        // ✅ If path exists, print it along with weights and total weight
        println("\n✅ Shortest path from Node $start to Node $end:")
        var totalWeight = 0
        for (i in 0 until path.size - 1) {
            val from = path[i]
            val to = path[i + 1]
            val edge = graph[from]?.find { it.to == to }

            if (edge != null) {
                println("Node $from --(${edge.weight})--> Node $to")
                totalWeight += edge.weight
            } else {
                println("⚠️ Missing edge from Node $from to Node $to")
                return emptyList()
            }
        }
        println("🏁 Total path weight: $totalWeight\n")

        return path
    }

    println("❌ No path found from Node $start to Node $end.")
    return emptyList()
}


fun dijkstraAll(graph: Graph, start: Int): Map<Int, Int> {
    val distances = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
    val visited = mutableSetOf<Int>()

    val queue = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
    distances[start] = 0
    queue.add(start to 0)

    while (queue.isNotEmpty()) {
        val (currentNode, currentDist) = queue.poll()

        if (currentNode in visited) continue
        visited.add(currentNode)

        val neighbors = graph[currentNode] ?: continue
        for (edge in neighbors) {
            val neighbor = edge.to
            val weight = edge.weight
            val newDist = currentDist + weight

            if (newDist < distances.getValue(neighbor)) {
                distances[neighbor] = newDist
                queue.add(neighbor to newDist)
            }
        }
    }

    return distances
}