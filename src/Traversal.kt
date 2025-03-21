



fun dfs(graph: Graph, start: Int): List<Int> {
    val visited = mutableSetOf<Int>()
    val result = mutableListOf<Int>()
    val stack = ArrayDeque<Int>()

    stack.add(start)

    while (stack.isNotEmpty()) {
        val node = stack.removeLast()

        if (visited.add(node)) {
            result.add(node)
            val neighbors = graph[node]?.map { it.to } ?: emptyList()
            stack.addAll(neighbors)
        }
    }

    return result
}


fun bfs(graph: Graph, start: Int): List<Int> {
    val visited = mutableSetOf<Int>()
    val queue = ArrayDeque<Int>()
    val result = mutableListOf<Int>()

    queue.add(start)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()

        if (visited.add(node)) {
            result.add(node)
            val neighbors = graph[node]?.map { it.to } ?: emptyList()
            queue.addAll(neighbors)
        }
    }

    return result
}
