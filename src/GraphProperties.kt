fun eccentricity(graph: Graph, node: Int): Int {
    val distances = dijkstraAll(graph, node)

    // Filter out unreachable nodes AND the node itself (distance = 0)
    val reachableDistances = distances.filter { (target, distance) ->
        distance != Int.MAX_VALUE && target != node
    }.values

    // If there are no reachable nodes (node can't reach anyone else)
    if (reachableDistances.isEmpty()) {
        return Int.MAX_VALUE
    }

    // Otherwise, return the farthest reachable distance
    return reachableDistances.maxOrNull() ?: Int.MAX_VALUE
}


// --- Radius of the graph (minimum eccentricity) ---
fun radius(graph: Graph): Int {
    val eccentricities = graph.keys.map { node -> eccentricity(graph, node) }

    // Filter out disconnected nodes
    val finiteEccentricities = eccentricities.filter { it != Int.MAX_VALUE }

    // Return smallest eccentricity, or infinity if all nodes are disconnected
    return finiteEccentricities.minOrNull() ?: Int.MAX_VALUE
}

// --- Diameter of the graph (maximum eccentricity) ---
fun diameter(graph: Graph): Int {
    val eccentricities = graph.keys.map { node -> eccentricity(graph, node) }

    // Filter out disconnected nodes
    val finiteEccentricities = eccentricities.filter { it != Int.MAX_VALUE }

    // Return largest eccentricity, or infinity if no finite eccentricity exists
    return finiteEccentricities.maxOrNull() ?: Int.MAX_VALUE
}