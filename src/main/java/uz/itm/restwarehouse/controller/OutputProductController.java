package uz.itm.restwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.OutputProduct;
import uz.itm.restwarehouse.loader.OutputProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.OutputProductService;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/outputProduct")

public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;
    @PostMapping
    public ResponseEntity<Result> addInputProduct(@RequestBody OutputProductLoader outputProductLoader) throws ParseException {
        Result result = outputProductService.addOutputProduct(outputProductLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @GetMapping
    public List<OutputProduct> getInputProducts(){
        return outputProductService.getOutputProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OutputProduct>getInputProductById(@PathVariable Integer id){
        OutputProduct outputProduct = outputProductService.getOutputProductById(id);
        if (outputProduct==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.FOUND).body(outputProduct);
    }
    @GetMapping("/{outputId}")
    public List<OutputProduct>getInputProductsByInputId(@PathVariable Integer outputId){
        return outputProductService.getOutputProductByInputId(outputId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editInputProduct(@PathVariable Integer id,@RequestBody OutputProductLoader outputProductLoader){
        Result result = outputProductService.editOutputProduct(id, outputProductLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteInputProduct(@PathVariable Integer id){
        Result result = outputProductService.deleteOutputProduct(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}
