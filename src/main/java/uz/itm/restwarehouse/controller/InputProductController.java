package uz.itm.restwarehouse.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Input;
import uz.itm.restwarehouse.entity.InputProduct;
import uz.itm.restwarehouse.loader.InputProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.InputProductService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/InputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;
    @PostMapping
    public ResponseEntity<Result>addInputProduct(@RequestBody InputProductLoader inputProductLoader) throws ParseException {
        Result result = inputProductService.addInputProduct(inputProductLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @GetMapping
    public List<InputProduct>getInputProducts(){
       return inputProductService.getInputProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<InputProduct>getInputProductById(@PathVariable Integer id){
        InputProduct inputProductById = inputProductService.getInputProductById(id);
        if (inputProductById==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.FOUND).body(inputProductById);
    }
    @GetMapping("/{inputId}")
    public List<InputProduct>getInputProductsByInputId(@PathVariable Integer inputId){
       return inputProductService.getInputProductByInputId(inputId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editInputProduct(@PathVariable Integer id,@RequestBody InputProductLoader inputProductLoader){
        Result result = inputProductService.editInputProduct(id, inputProductLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteInputProduct(@PathVariable Integer id){
        Result result = inputProductService.deleteInputProduct(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
