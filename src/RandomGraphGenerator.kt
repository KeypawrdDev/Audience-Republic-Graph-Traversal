import kotlin.random.Random

/**
 * Generates a random, simple, connected, directed graph.
 * @param nodeCount The number of nodes (vertices) in the graph
 * @param edgeCount The number of edges to include (must be >= nodeCount - 1)
 * @return The generated graph as an adjacency list (Map)
 */
fun makeGraph(nodeCount: Int, edgeCount: Int): Graph {
    // Basic input checks
    require(nodeCount > 0) { "Graph must have at least one node." }
    require(edgeCount >= nodeCount - 1) { "Need at least N-1 edges for connectivity." }
    require(edgeCount <= nodeCount * (nodeCount - 1)) { "Too many edges! Max allowed is N*(N-1)." }

    val nodes = (1..nodeCount).toList() // Node IDs from 1 to nodeCount

    // Initialize the adjacency list: each node maps to an empty mutable list of edges
    val edges = mutableMapOf<Int, MutableList<Edge>>()
    nodes.forEach { node ->
        edges[node] = mutableListOf()
    }

    // Step 1: Create a connected spanning tree first (to guarantee connectivity)
    val unconnected = nodes.toMutableList() // Nodes not yet connected
    val connected = mutableListOf<Int>()    // Nodes already connected to the graph

    connected.add(unconnected.removeAt(0))  // Start with the first node (node 1)

    while (unconnected.isNotEmpty()) {
        val from = connected.random()        // Pick a random node already connected
        val to = unconnected.removeAt(0)     // Pick an unconnected node to connect

        val weight = Random.nextInt(1, 20)   // Random weight between 1 and 20
        edges[from]?.add(Edge(to, weight))   // Add a directed edge from -> to

        connected.add(to)                    // Mark this node as connected
    }

    // Step 2: Add remaining edges randomly (making sure we avoid self-loops and duplicates)
    val remainingEdges = edgeCount - (nodeCount - 1)

    repeat(remainingEdges) {
        var from: Int
        var to: Int

        // Keep picking random node pairs until we get a valid pair
        do {
            from = nodes.random()     // Random source node
            to = nodes.random()       // Random destination node
        } while (
            from == to ||             // No self-loops
            edges[from]?.any { it.to == to } == true // No duplicate edges
        )

        val weight = Random.nextInt(1, 20) // Random weight
        edges[from]?.add(Edge(to, weight)) // Add the edge to the graph
    }

    // Return the graph as a Map (immutable from this point)
    return edges
}