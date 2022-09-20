package uz.itm.restwarehouse.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Category;
import uz.itm.restwarehouse.loader.CategoryById;
import uz.itm.restwarehouse.loader.CategoryLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.CategoryService;

import java.util.List;
import java.util.Map;

import static uz.itm.restwarehouse.controller.WarehouseController.getStringStringMap;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Result>addCategory(@RequestBody CategoryLoader categoryLoader){
        Result result = categoryService.addCategory(categoryLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @GetMapping
    List<Category> getCategories(){
        return categoryService.getCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category>getCategoryById(@PathVariable Integer id){
        CategoryById categoryById = categoryService.getCategoryById(id);
        if (categoryById.isSuccess())
            return ResponseEntity.status(HttpStatus.FOUND).body(categoryById.getCategory());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categoryById.getCategory());
    }
    @PutMapping("/id")
    public ResponseEntity<Result>editCategory(@PathVariable Integer id,@RequestBody CategoryLoader categoryLoader){
        Result result = categoryService.editCategory(id, categoryLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
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
