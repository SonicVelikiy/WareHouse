package uz.itm.restwarehouse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Users;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.loader.UsersById;
import uz.itm.restwarehouse.loader.UsersLoader;
import uz.itm.restwarehouse.service.UsersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.itm.restwarehouse.controller.WarehouseController.getStringStringMap;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping
    public ResponseEntity<Result> addUsers(@RequestBody @Valid UsersLoader usersLoader) {
        Result result = usersService.addUser(usersLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PostMapping("/makeResponsible/{userId}/{warehouseId}")
    public ResponseEntity<Result> makeResponsibleForWarehouse(@PathVariable Integer warehouseId, @PathVariable Integer userId) {
        Result result = usersService.makeResponsibleForWarehouse(warehouseId, userId);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @PostMapping("removeResponsibility/{userId}/{warehouseId}")
    public ResponseEntity<Result> removeResponsibility(@PathVariable Integer userId, @PathVariable Integer warehouseId) {
        Result result = usersService.removeResponsibility(warehouseId, userId);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);

    }

    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        UsersById userById = usersService.getUserById(id);
        if (userById.isSuccess())
            return ResponseEntity.status(HttpStatus.FOUND).body(userById.getUser());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userById.getUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editUser(@PathVariable Integer id, @RequestBody UsersLoader usersLoader) {
        Result result = usersService.editUser(id, usersLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Integer id) {
        Result result = usersService.deleteUser(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
