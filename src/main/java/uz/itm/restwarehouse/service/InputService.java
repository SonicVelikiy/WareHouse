package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.restwarehouse.entity.Currency;
import uz.itm.restwarehouse.entity.Input;
import uz.itm.restwarehouse.entity.Supplier;
import uz.itm.restwarehouse.entity.Warehouse;
import uz.itm.restwarehouse.loader.InputLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.CurrencyRepository;
import uz.itm.restwarehouse.repository.InputRepository;
import uz.itm.restwarehouse.repository.SupplierRepository;
import uz.itm.restwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public Result addInput(InputLoader inputLoader) {
        Input input = new Input();
        input.setCode(inputLoader.getCode());
        input.setFactureNumber(input.getFactureNumber());
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputLoader.getWarehouseId());
        if (warehouseOptional.isPresent()) {
            input.setWarehouse(warehouseOptional.get());
            Optional<Currency> currencyOptional = currencyRepository.findById(inputLoader.getCurrencyId());
            if (currencyOptional.isPresent()) {
                input.setCurrency(currencyOptional.get());
                Optional<Supplier> supplierOptional = supplierRepository.findById(inputLoader.getSupplierId());
                if (supplierOptional.isPresent()) {
                    input.setSupplier(supplierOptional.get());
                    inputRepository.save(input);
                    return new Result("added successfully", true);
                }
                return new Result("No supplier", false);
            }
            return new Result("No currency", false);
        }
        return new Result("No warehouse", false);


    }

    public List<Input> getInputs() {
        return inputRepository.findAll();
    }

    public Input getInputById(Integer id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        return inputOptional.orElse(null);
    }

    public Result editInput(Integer id, InputLoader inputLoader) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isPresent()) {
            Input input = inputOptional.get();
            input.setCode(inputLoader.getCode());
            input.setFactureNumber(input.getFactureNumber());
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputLoader.getWarehouseId());
            warehouseOptional.ifPresent(input::setWarehouse);
            Optional<Currency> currencyOptional = currencyRepository.findById(inputLoader.getCurrencyId());
            currencyOptional.ifPresent(input::setCurrency);
            Optional<Supplier> supplierOptional = supplierRepository.findById(inputLoader.getSupplierId());
            supplierOptional.ifPresent(input::setSupplier);
            return new Result("edited successfully", true);


        }
        return new Result("there is no Input with this data", false);
    }

    public Result deleteInput(Integer id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isPresent()){
            inputRepository.delete(inputOptional.get());
            return new Result("deleted successfully",true);
        }
        return new Result("Not found",false);
    }
}
