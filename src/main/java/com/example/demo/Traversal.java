package com.example.demo;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;
import static org.apache.tinkerpop.gremlin.structure.io.IoCore.graphml;

public class Traversal {
    public static void main(String[] args) {
        TinkerGraph tg = TinkerGraph.open();

        try {
            tg.io(graphml()).readGraph("./air-routes.graphml");
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        GraphTraversalSource g = tg.traversal();

        /*
        // 查询顶点AUS的属性并赋值给aus
        Map<Object, ?> aus = g.V().has("code", "AUS").valueMap().next();
        System.out.println(aus);

        // 循环输出aus中的数据，更直观
        aus.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));

        // 取出city属性值
        List city = (List) (aus.get("city"));
        System.out.println("The AUS airport is in " + city.get(0));

        // 计数：DFW直飞的航线数量
        Long n = g.V().has("code", "DFW").out().count().next();
        System.out.println("There are " + n + " routes from Dallas");

        // 查询与DFW存在直飞航线的机场名称，以列表的形式输出
        List fromDfw = g.V().has("code", "DFW").out().
                order().by("code").values("code").toList();
        System.out.println(fromDfw);

        // 输出路径：从LHR到US所有机场的航线及飞行距离
        List<Path> lhrToUsa = g.V().has("code", "LHR").outE().inV().
                has("country", "US").
                path().by("code").by("dist").toList();
        lhrToUsa.forEach((k) -> System.out.println(k));

        // Places in England I can get to with one stop from AUS
        // repeat(__.out()).times(2)-->out前面的下划线下划线一定要有
        List<Object> eng =
                g.V().has("code", "AUS").repeat(out()).times(2).
                        has("region", "GB-ENG").values("city").dedup().toList();
        System.out.println("\nPlaces in England I can get to with one stop from AUS.\n");
        eng.forEach((p) -> System.out.print(p + " "));

        Search search = new Search();
        search.findByRegion("DEN",g);

        */

        // ------------------------something more complex----------------------
        // 哪些国家没有机场
//        System.out.println(g.V().hasLabel("country").not(out()).values("desc").toList());
//        System.out.println(g.V().hasLabel("country").not(in()).values("desc").toList());
//        System.out.println(g.V().hasLabel("country").outE().valueMap().toList());

        // 哪些机场没有航线
//        System.out.println(g.V().hasLabel("airport").not(bothE("route")).values("code").toList());

        // 跑道的分布
        g.V().hasLabel("airport").groupCount().by("runways").toList();
    }


}
