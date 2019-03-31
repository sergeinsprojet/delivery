package com.express.delivery.services;

import com.express.delivery.domain.*;
import com.express.delivery.domain.dijkstra.Graph;
import com.express.delivery.domain.dijkstra.Node;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.ShippingOrderRepository;
import com.express.delivery.repository.StoreRepository;
import com.express.delivery.repository.DepotRepository;
import com.express.delivery.vo.ShortestPathVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShippingOrderService implements IShippingOrderService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShippingOrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    private DijkstraAlgorithmService dijkstraAlgorithmService;

    @Override
    public List<ShippingOrder> findAll() {
        return (List<ShippingOrder>) orderRepository.findAll();
    }

    public Store findClosestStoreByDeliveryAddress(GeoLocation geolocation){

        Store store = storeRepository.findClosestStoreByLngAndLat(geolocation.getGeoLng(), geolocation.getGeoLat());
        return store;
    }

    public Depot findClosestDepotByStoreAddress(GeoLocation geolocation){
        Depot depot = depotRepository.findClosestDepotByLngAndLat(geolocation.getGeoLng(), geolocation.getGeoLat());
        return depot;
    }

    public ShortestPathVO findShortestPath(Customer customer){
        ShortestPathVO shortestPathVO = findStoreAndDepotWithShortestDistanceByCustomer(customer);
        shortestPathVO.setCustomer(customer);
        return shortestPathVO;
    }

    public ShortestPathVO findStoreAndDepotWithShortestDistanceByCustomer(Customer customer){
        Graph graph = buildGraphByCustomer(customer);
        ShortestPathVO shortestPath = new ShortestPathVO();
        List<Depot> depotList = depotRepository.findAll();
        List<Node> nodeList = new ArrayList<Node>();

        for(int i = 0; i < graph.getNodes().size(); i++){
            for(int j = 0; j < depotList.size(); j++){
                if(depotList.get(j)!=null && depotList.get(j).getGeoLocation()!=null){
                    Node nodeInstance = (Node)graph.getNodes().toArray()[i];
                    if(depotList.get(j).getGeoLocation() == nodeInstance.getGeoLocation()){
                        nodeList.add(nodeInstance);

                    }
                }
            }
        }
        Collections.sort(nodeList);
        Node resultNode = nodeList.get(0);
        Depot startingDepot = depotRepository.findByGeoLocation(resultNode.getGeoLocation());
        Store store = storeRepository.findByGeoLocation(resultNode.getShortestPath().get(1).getGeoLocation());
        shortestPath.setStore(store);
        shortestPath.setDepot(startingDepot);
        return shortestPath;
    }

    public Graph buildGraphByCustomer(Customer customer){
        Node destinationNode = new Node(customer.getFullname(),customer.getGeoLocation());
        Graph graph = new Graph();
        List<Node> storeNodeList = getStoreNodeList();
        List<Node> depotNodeList = getDepotNodeList();
        storeNodeList.forEach(storeNode->
                destinationNode.addDestination(storeNode, calculateDistanceFromA2B(destinationNode.getGeoLocation(),storeNode.getGeoLocation()))
        );

        storeNodeList.forEach(storeNode->
                depotNodeList.forEach(depotNode->
                        storeNode.addDestination(depotNode, calculateDistanceFromA2B(storeNode.getGeoLocation(),depotNode.getGeoLocation()))
                )
        );

        graph.addNode(destinationNode);
        for(int i = 0; i < storeNodeList.size(); i++)
        {
            graph.addNode(storeNodeList.get(i));
        }

        for(int i = 0; i < depotNodeList.size(); i++)
        {
            graph.addNode(depotNodeList.get(i));
        }

        graph = dijkstraAlgorithmService.calculateShortestPathFromSource(graph, destinationNode);

        return graph;
    }

    public static Double calculateDistanceFromA2B(GeoLocation locationA, GeoLocation locationB){
        Double distance = ( 6371 * Math.acos( Math.cos( Math.toRadians(locationA.getGeoLat().doubleValue()) ) * Math.cos( Math.toRadians( locationB.getGeoLat().doubleValue() ) )
                * Math.cos( Math.toRadians( locationB.getGeoLng().doubleValue() ) - Math.toRadians(locationA.getGeoLng().doubleValue()) )
                + Math.sin( Math.toRadians(locationA.getGeoLat().doubleValue()) )
                * Math.sin( Math.toRadians( locationB.getGeoLat().doubleValue() ) ) ) );

        return distance;
    }

    public List<Node> extendNodeList(List<Node> nodeList){
        return nodeList;
    };

    public List<Node> getStoreNodeList(){
        List<Store> storeList = storeRepository.findAll();
        List<Node> storeNodeList = new ArrayList<Node>();
        storeList.forEach(store->
                storeNodeList.add(new Node(store.getName(), store.getGeoLocation()))
        );

        return storeNodeList;
    };

    public List<Node> getDepotNodeList(){
        List<Depot> depotList = depotRepository.findAll();
        List<Node> depotNodeList = new ArrayList<Node>();
        depotList.forEach(depot->
                depotNodeList.add(new Node(depot.getName(), depot.getGeoLocation()))
        );

        return depotNodeList;
    };
}
