package com.example.demo;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;

import java.util.List;

public class Search {
    public void findByRegion(String iata, GraphTraversalSource g) {

        System.out.println("\n\nRegion code lookup for " + iata );
        List<List<Object>> list = g.V().has("code",iata).values("region").as("r").
                V().hasLabel("airport").as("a").values("region").
                where(P.eq("r")).by().
                local(__.select("a").values("city","code","region").fold()).toList();

        for(List t : list) {
            System.out.println(t);
        }
    }
}
