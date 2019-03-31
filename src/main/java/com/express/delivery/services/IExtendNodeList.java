package com.express.delivery.services;

import com.express.delivery.domain.Facility;
import com.express.delivery.domain.dijkstra.Node;

import java.util.List;

interface  IExtendNodeList {
    void extendNodeList(List<Node> nodeList, Facility facility);
}
