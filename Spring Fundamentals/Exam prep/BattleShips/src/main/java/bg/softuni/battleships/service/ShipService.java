package bg.softuni.battleships.service;


import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.service.ShipServiceModel;

import java.util.List;

public interface ShipService {

    void addShip(ShipServiceModel shipServiceModel);

    List<ShipDTO> getShipsOwnedBy(long ownerId);

    List<ShipDTO> getShipsNotOwnedBy(long ownerId);

    List<ShipDTO> getAllSortedShips();
}
