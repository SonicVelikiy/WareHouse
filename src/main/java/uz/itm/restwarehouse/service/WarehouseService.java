package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.restwarehouse.entity.Warehouse;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.loader.WarehouseById;
import uz.itm.restwarehouse.loader.WarehouseLoader;
import uz.itm.restwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(WarehouseLoader warehouseLoader) {
        boolean existsByName = warehouseRepository.existsByName(warehouseLoader.getName());
        if (existsByName)
            return new Result("the warehouse with this name is already exist",false);
        Warehouse warehouse=new Warehouse();
        warehouse.setName(warehouseLoader.getName());
        warehouse.setActive(warehouseLoader.isActive());
        warehouseRepository.save(warehouse);
        return new Result("added successfully",true );
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    public WarehouseById getWarehouseById(Integer id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        return warehouseOptional.map(warehouse -> new WarehouseById(true, warehouse)).orElseGet(() -> new WarehouseById(false, null));
    }

    public Result editUser(Integer warehouseId, WarehouseLoader warehouseLoader) {
        boolean existsByNameAndIdNot = warehouseRepository.existsByNameAndIdNot(warehouseLoader.getName(), warehouseId);
        if(existsByNameAndIdNot)return new Result("the warehouse with this name is already exist",false);
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
        if (warehouseOptional.isPresent()){
            Warehouse warehouse = warehouseOptional.get();
            warehouse.setName(warehouseLoader.getName());
            warehouse.setActive(warehouse.isActive());
            warehouseRepository.save(warehouse);
            return new Result("edited successfully",true);
        }
        return new Result("there is no warehouse with this name",false);
    }

    public Result deleteWarehouse(Integer id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (warehouseOptional.isPresent()){
            warehouseRepository.delete(warehouseOptional.get());
            return new Result("deleted successfully",true);
        }
        return new Result("the warehouse doesn't exist",false);
    }
}
