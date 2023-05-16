package org.batakjava;

import org.extendj.ast.Graph;

public class DependencyChecker {

  public static void main(String[] args) {
    Graph g = new Graph(4);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    g.addEdge(3, 3);
    // g.printGraph();

    System.out.println(g.getComponents());

    Graph g2 = new Graph(8);
    g2.addEdge(0,1);
    g2.addEdge(1,2);
    g2.addEdge(2,0);
    g2.addEdge(3,1);
    g2.addEdge(3,2);
    g2.addEdge(3,4);
    g2.addEdge(4,3);
    g2.addEdge(4,5);
    g2.addEdge(5,2);
    g2.addEdge(5,6);
    g2.addEdge(6,5);
    g2.addEdge(7,4);
    g2.addEdge(7,6);
    g2.addEdge(7,7);

    System.out.println(g2.getComponents());
  }

}
