package com.sajib.graph.web.controller;

/**
 * Created by sajib on 2/22/19.
 */
public class JsonResponses {

    public static final String JSON_RESPONSE_1 = "{\n" +
            "    \"routes\": [\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Vasastan\",\n" +
            "                    \"end\": \"Liseberg\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 480,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 480,\n" +
            "            \"durationOfRoute\": 1\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static final String JSON_RESPONSE_2 = "{\n" +
            "    \"routes\": [\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Vasastan\",\n" +
            "                    \"end\": \"Liseberg\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 480,\n" +
            "                    \"daysTaken\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Liseberg\",\n" +
            "                    \"end\": \"Oakland Park\",\n" +
            "                    \"modeOfTransport\": \"Ocean\",\n" +
            "                    \"cost\": 1673,\n" +
            "                    \"daysTaken\": 22\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Oakland Park\",\n" +
            "                    \"end\": \"Maitland\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 650,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 2803,\n" +
            "            \"durationOfRoute\": 24\n" +
            "        },\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Vasastan\",\n" +
            "                    \"end\": \"Liseberg\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 480,\n" +
            "                    \"daysTaken\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Liseberg\",\n" +
            "                    \"end\": \"Georgia\",\n" +
            "                    \"modeOfTransport\": \"Ocean\",\n" +
            "                    \"cost\": 1815,\n" +
            "                    \"daysTaken\": 23\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Georgia\",\n" +
            "                    \"end\": \"Maitland\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 650,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 2945,\n" +
            "            \"durationOfRoute\": 25\n" +
            "        },\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Vasastan\",\n" +
            "                    \"end\": \"Gouda\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 1480,\n" +
            "                    \"daysTaken\": 3\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Gouda\",\n" +
            "                    \"end\": \"Oakland Park\",\n" +
            "                    \"modeOfTransport\": \"Ocean\",\n" +
            "                    \"cost\": 1673,\n" +
            "                    \"daysTaken\": 18\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Oakland Park\",\n" +
            "                    \"end\": \"Maitland\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 650,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 3803,\n" +
            "            \"durationOfRoute\": 22\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static final String JSON_RESPONSE_3 = "{\n" +
            "    \"routes\": [\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Himchari\",\n" +
            "                    \"end\": \"Kaptai\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 100,\n" +
            "                    \"daysTaken\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Kaptai\",\n" +
            "                    \"end\": \"Karail\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 115,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 215,\n" +
            "            \"durationOfRoute\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Himchari\",\n" +
            "                    \"end\": \"Kaptai\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 100,\n" +
            "                    \"daysTaken\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Kaptai\",\n" +
            "                    \"end\": \"Agargaon\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 112,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 212,\n" +
            "            \"durationOfRoute\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static final String JSON_RESPONSE_4 = "{\n" +
            "    \"routes\": [\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Liseberg\",\n" +
            "                    \"end\": \"Oakland Park\",\n" +
            "                    \"modeOfTransport\": \"Ocean\",\n" +
            "                    \"cost\": 1673,\n" +
            "                    \"daysTaken\": 22\n" +
            "                },\n" +
            "                {\n" +
            "                    \"start\": \"Oakland Park\",\n" +
            "                    \"end\": \"Maitland\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 650,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 2323,\n" +
            "            \"durationOfRoute\": 23\n" +
            "        }\n" +
            "    ]\n" +
            "}";

}
