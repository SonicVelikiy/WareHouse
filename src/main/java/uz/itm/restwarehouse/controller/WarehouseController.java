package uz.itm.restwarehouse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Warehouse;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.loader.WarehouseById;
import uz.itm.restwarehouse.loader.WarehouseLoader;
import uz.itm.restwarehouse.service.WarehouseService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Result>addWarehouse(@RequestBody @Valid WarehouseLoader warehouseLoader){
        Result result = warehouseService.addWarehouse(warehouseLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);

    }
    @GetMapping
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse>getWarehouseById(@PathVariable Integer id){
        WarehouseById warehouseById = warehouseService.getWarehouseById(id);
        if (warehouseById.isSuccess())
            return ResponseEntity.status(HttpStatus.FOUND).body(warehouseById.getWarehouse());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(warehouseById.getWarehouse());
    }
    @PutMapping("/{warehouseId}")
    public ResponseEntity<Result>editWarehouse(@PathVariable Integer warehouseId,@RequestBody @Valid WarehouseLoader warehouseLoader){
        Result result = warehouseService.editUser(warehouseId, warehouseLoader);
        if(result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteWarehouse(@PathVariable Integer id){
        Result result = warehouseService.deleteWarehouse(id);
        if(result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    static Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
