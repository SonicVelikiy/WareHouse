package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.restwarehouse.entity.*;
import uz.itm.restwarehouse.loader.InputLoader;
import uz.itm.restwarehouse.loader.OutputLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public Result addOutput(OutputLoader outputLoader) {
        Output output = new Output();
        output.setCode(outputLoader.getCode());
        output.setFactureNumber(output.getFactureNumber());
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(outputLoader.getWarehouseId());
        if (warehouseOptional.isPresent()) {
            output.setWarehouse(warehouseOptional.get());
            Optional<Currency> currencyOptional = currencyRepository.findById(outputLoader.getCurrencyId());
            if (currencyOptional.isPresent()) {
                output.setCurrency(currencyOptional.get());
                Optional<Client> clientOptional = clientRepository.findById(outputLoader.getClientId());
                if (clientOptional.isPresent()) {
                    output.setClient(clientOptional.get());
                    outputRepository.save(output);
                    return new Result("added successfully", true);
                }
                return new Result("No Client", false);
            }
            return new Result("No currency", false);
        }
        return new Result("No warehouse", false);


    }

    public List<Output> getOutputs() {
        return outputRepository.findAll();
    }

    public Output getOutputById(Integer id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        return outputOptional.orElse(null);
    }

    public Result editOutput(Integer id, OutputLoader outputLoader) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isPresent()) {
            Output output = outputOptional.get();
            output.setCode(outputLoader.getCode());
            output.setFactureNumber(outputLoader.getFactureNumber());
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(outputLoader.getWarehouseId());
            warehouseOptional.ifPresent(output::setWarehouse);
            Optional<Currency> currencyOptional = currencyRepository.findById(outputLoader.getCurrencyId());
            currencyOptional.ifPresent(output::setCurrency);
            Optional<Client> supplierOptional = clientRepository.findById(outputLoader.getClientId());
            supplierOptional.ifPresent(output::setClient);
            return new Result("edited successfully", true);


        }
        return new Result("there is no Input with this data", false);
    }

    public Result deleteOutput(Integer id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isPresent()){
            outputRepository.delete(outputOptional.get());
            return new Result("deleted successfully",true);
        }
        return new Result("Not found",false);
    }
}
