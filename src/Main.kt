

fun main() {
    // Simple hardcoded graph for testing
    val graph1: Graph = mapOf(
        1 to listOf(Edge(2, 1), Edge(3, 2)),
        2 to listOf(Edge(4, 4)),
        3 to listOf(Edge(4, 2)),
        4 to emptyList()
    )

    println("DFS Traversal starting at node 1: ${dfs(graph1, 1)}")
    println("BFS Traversal starting at node 1: ${bfs(graph1, 1)}")


    val nodeCount = 6
    val edgeCount = 8


    val randomGraph = makeGraph(nodeCount, edgeCount)

    println("Random Graph with $nodeCount nodes and $edgeCount edges:\n")
    printGraph(randomGraph)

    val startNode = 1
    val endNode = 6

    val shortestPath = dijkstra(randomGraph, startNode, endNode)

    if (shortestPath.isNotEmpty()) {
        println("Shortest path from $startNode to $endNode: $shortestPath")
    } else {
        println("No path found from $startNode to $endNode.")
    }

    println("🔎 Eccentricity of each node:")
    randomGraph.keys.forEach { node ->
        val ecc = eccentricity(randomGraph, node)
        println("Eccentricity of node $node: ${if (ecc == Int.MAX_VALUE) "∞" else ecc}")
    }

    val graphRadius = radius(randomGraph)
    val graphDiameter = diameter(randomGraph)

    println("\n✅ Radius of the graph: ${if (graphRadius == Int.MAX_VALUE) "∞" else graphRadius}")
    println("✅ Diameter of the graph: ${if (graphDiameter == Int.MAX_VALUE) "∞" else graphDiameter}")
}
