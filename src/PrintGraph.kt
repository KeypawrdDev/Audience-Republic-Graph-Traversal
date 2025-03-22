

fun printGraph(graph: Graph) {
    println("📌 Random Graph (Adjacency List with Weights):\n")
    graph.forEach { (node, edges) ->
        if (edges.isEmpty()) {
            println("Node $node -> No outgoing edges")
        } else {
            edges.forEach { edge ->
                println("Node $node --(${edge.weight})--> Node ${edge.to}")
            }
        }
    }
    println()
}