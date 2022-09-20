package uz.itm.restwarehouse.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.itm.restwarehouse.entity.Product;
import uz.itm.restwarehouse.loader.ProductById;
import uz.itm.restwarehouse.loader.ProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static uz.itm.restwarehouse.controller.WarehouseController.getStringStringMap;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Result> addProduct(@RequestBody @Valid ProductLoader productLoader) {
        Result result = productService.addProduct(productLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        ProductById productById = productService.getProductById(id);
        if (productById.isSuccess())
            return ResponseEntity.status(HttpStatus.FOUND).body(productById.getProduct());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productById.getProduct());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editProduct(@PathVariable Integer id,@RequestBody @Valid ProductLoader productLoader){
        Result result = productService.editProduct(id, productLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @PostMapping("/{productId}/addPhoto")
    public ResponseEntity <Result> addProductPhoto(MultipartHttpServletRequest request,@PathVariable Integer productId) throws ServletException, IOException {
        Result result = productService.addProductPhoto(request, productId);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);

    }
    @DeleteMapping("/{productId}/removePhoto/{photoId}")
    public ResponseEntity<Result>removeProductPhoto(@PathVariable Integer productId,@PathVariable Integer photoId){
        Result result = productService.deleteProductPhoto(productId, photoId);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @GetMapping("/photos/{productId}")
    public void getFiles(@PathVariable Integer productId, HttpServletResponse response) throws IOException {
        productService.getProductPhotos(productId,response);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
