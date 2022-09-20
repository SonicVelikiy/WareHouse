package uz.itm.restwarehouse.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Input;
import uz.itm.restwarehouse.loader.InputLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.InputService;


import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public ResponseEntity<Result> addInput(@RequestBody InputLoader inputLoader) {
        Result result = inputService.addInput(inputLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @GetMapping
    public List<Input> getInputs() {
        return inputService.getInputs();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Input> getInputById(@PathVariable Integer id) {
        Input inputById = inputService.getInputById(id);
        if (inputById != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(inputById);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editInput(@PathVariable Integer id,@RequestBody InputLoader inputLoader){
        Result result = inputService.editInput(id, inputLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteInput(@PathVariable Integer id){
        Result result = inputService.deleteInput(id);
        if (result.isSuccess())
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}
