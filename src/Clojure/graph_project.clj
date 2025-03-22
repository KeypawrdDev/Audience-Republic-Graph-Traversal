;; =============================================
;; Graph Representation and Edge Structure
;; =============================================

;; Graph: map from node to a vector of edges (node-weight pairs)
(def graph-example
  {:1 [[:2 1] [:3 2]]
   :2 [[:4 4]]
   :3 [[:4 2]]
   :4 []})

;; =============================================
;; DFS Traversal
;; =============================================
(defn dfs [graph start]
  (loop [stack [start]
         visited #{}
         result []]
    (if (empty? stack)
      result
      (let [node (peek stack)
            stack (pop stack)]
        (if (visited node)
          (recur stack visited result)
          (let [neighbors (map first (graph node))]
            (recur (into stack neighbors)
                   (conj visited node)
                   (conj result node))))))))

;; =============================================
;; BFS Traversal
;; =============================================
(defn bfs [graph start]
  (loop [queue (conj clojure.lang.PersistentQueue/EMPTY start)
         visited #{}
         result []]
    (if (empty? queue)
      result
      (let [node (peek queue)
            queue (pop queue)]
        (if (visited node)
          (recur queue visited result)
          (let [neighbors (map first (graph node))]
            (recur (into queue neighbors)
                   (conj visited node)
                   (conj result node))))))))

;; =============================================
;; Random Graph Generator
;; =============================================
(defn make-graph [node-count edge-count]
  (let [nodes (map keyword (map str (range 1 (inc node-count))))
        edges (zipmap nodes (repeat []))]
    (loop [unconnected (rest nodes)
           connected [(first nodes)]
           edges edges]
      (if (empty? unconnected)
        edges
        (let [from (rand-nth connected)
              to (first unconnected)
              weight (+ 1 (rand-int 20))
              updated-edges (update edges from conj [to weight])]
          (recur (rest unconnected)
                 (conj connected to)
                 updated-edges))))
    (loop [remaining (- edge-count (dec node-count))
           edges edges]
      (if (zero? remaining)
        edges
        (let [from (rand-nth nodes)
              to (rand-nth nodes)
              valid? (and (not= from to)
                           (not-any? #(= (first %) to) (edges from)))]
          (if valid?
            (recur (dec remaining)
                   (update edges from conj [to (+ 1 (rand-int 20))]))
            (recur remaining edges)))))))

;; =============================================
;; Dijkstra Algorithm (Returns Distance Map)
;; =============================================
(defn dijkstra [graph start]
  (loop [distances {start 0}
         visited #{}
         queue (sorted-set-by #(compare (distances %1) (distances %2)) start)]
    (if (empty? queue)
      distances
      (let [current (first queue)
            current-dist (distances current)
            neighbors (graph current)
            updated-distances (reduce
                                (fn [dists [neighbor weight]]
                                  (let [new-dist (+ current-dist weight)]
                                    (if (< new-dist (get dists neighbor ##Inf))
                                      (assoc dists neighbor new-dist)
                                      dists)))
                                distances
                                neighbors)
            new-queue (into (disj queue current)
                            (remove visited (map first neighbors)))]
        (recur updated-distances (conj visited current) new-queue)))))

;; =============================================
;; Eccentricity, Radius, Diameter
;; =============================================
(defn eccentricity [graph node]
  (let [distances (dijkstra graph node)
        reachable (remove #(= node (key %)) distances)]
    (if (empty? reachable)
      ##Inf
      (apply max (vals reachable)))))

(defn radius [graph]
  (let [eccs (map #(eccentricity graph %) (keys graph))
        finite (remove #(= ##Inf %) eccs)]
    (if (empty? finite)
      ##Inf
      (apply min finite))))

(defn diameter [graph]
  (let [eccs (map #(eccentricity graph %) (keys graph))
        finite (remove #(= ##Inf %) eccs)]
    (if (empty? finite)
      ##Inf
      (apply max finite))))

;; =============================================
;; Print Graph
;; =============================================
(defn print-graph [graph]
  (doseq [[node edges] graph]
    (if (empty? edges)
      (println (str "Node " node " -> No outgoing edges"))
      (doseq [[to weight] edges]
        (println (str "Node " node " --(" weight ")--> Node " to))))))

;; =============================================
;; Main Functionality (CLI Simulation)
;; =============================================
(defn run-example []
  (let [node-count 6
        edge-count 8
        g (make-graph node-count edge-count)
        nodes (vec (keys g))
        start (rand-nth nodes)
        end (rand-nth nodes)
        ecc-node (rand-nth nodes)]
    (println "\n📌 Generated Graph:\n")
    (print-graph g)
    (println "\n✅ Radius:" (radius g))
    (println "✅ Diameter:" (diameter g))
    (println (str "\n🔎 Random Shortest Path: " start " -> " end ": " (get (dijkstra g start) end "No path")))
    (println (str "\n🔎 Eccentricity of " ecc-node ": " (eccentricity g ecc-node)))))

;; Run the example
(run-example)
