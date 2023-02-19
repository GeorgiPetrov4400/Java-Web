package softuni.pathfinder.service;

import softuni.pathfinder.model.view.RouteViewModel;

import java.util.List;

public interface RouteService {

    List<RouteViewModel> findAllRoutesView();
}
