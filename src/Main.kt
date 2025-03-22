
fun main(args: Array<String>) {
    // Simple CLI argument parsing
    if (args.size < 4) {
        println("Usage: graph -N <number_of_nodes> -S <sparseness_edges>")
        return
    }

    var nodeCount = 0
    var edgeCount = 0

    for (i in args.indices) {
        when (args[i]) {
            "-N" -> nodeCount = args.getOrNull(i + 1)?.toIntOrNull() ?: 0
            "-S" -> edgeCount = args.getOrNull(i + 1)?.toIntOrNull() ?: 0
        }
    }

    if (nodeCount <= 0 || edgeCount < nodeCount - 1) {
        println("Invalid input. N must be > 0 and S must be >= N - 1.")
        return
    }

    // Generate random graph
    val randomGraph = makeGraph(nodeCount, edgeCount)

    // Print the graph structure
    println("\n📌 Randomly Generated Graph ($nodeCount nodes, $edgeCount edges):\n")
    printGraph(randomGraph)

    // Calculate radius and diameter
    val graphRadius = radius(randomGraph)
    val graphDiameter = diameter(randomGraph)

    println("\n✅ Radius of the graph: ${if (graphRadius == Int.MAX_VALUE) "∞" else graphRadius}")
    println("✅ Diameter of the graph: ${if (graphDiameter == Int.MAX_VALUE) "∞" else graphDiameter}")

    // Randomly select 2 nodes for shortest path
    val nodes = randomGraph.keys.toList()
    if (nodes.size < 2) {
        println("Not enough nodes to compute shortest path.")
        return
    }

    val startNode = nodes.random()
    var endNode = nodes.random()
    while (endNode == startNode) {
        endNode = nodes.random()
    }

    val shortestPath = dijkstra(randomGraph, startNode, endNode)

    println("\n🔎 Shortest path between random nodes $startNode and $endNode:")
    if (shortestPath.isNotEmpty()) {
        println("✅ Shortest path: $shortestPath")
    } else {
        println("❌ No path found between $startNode and $endNode.")
    }

    // Randomly select 1 node for eccentricity
    val eccentricityNode = nodes.random()
    val ecc = eccentricity(randomGraph, eccentricityNode)

    println("\n🔎 Eccentricity of random node $eccentricityNode: ${if (ecc == Int.MAX_VALUE) "∞" else ecc}")
}
