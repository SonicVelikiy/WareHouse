package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.itm.restwarehouse.entity.Category;
import uz.itm.restwarehouse.loader.CategoryById;
import uz.itm.restwarehouse.loader.CategoryLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryLoader categoryLoader) {
        boolean existsByName = categoryRepository.existsByName(categoryLoader.getName());
        if (existsByName)
            return new Result("the Category with this name is already exist", false);
        Category category = new Category();
        category.setName(categoryLoader.getName());
        category.setActive(category.isActive());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryLoader.getParentCategoryId());
        if (optionalCategory.isPresent()) {
            category.setParentCategory(optionalCategory.get());
            categoryRepository.save(category);
            return new Result("added successfully", true);
        }
        return new Result("there is no category with this name", false);


    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public CategoryById getCategoryById(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.map(category -> new CategoryById(true, category)).orElseGet(() -> new CategoryById(false, null));
    }

    public Result editCategory(Integer id, CategoryLoader categoryLoader) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setName(categoryLoader.getName());
            category.setActive(category.isActive());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryLoader.getParentCategoryId());
            if (optionalCategory.isPresent()){
                category.setParentCategory(optionalCategory.get());
                categoryRepository.save(category);
                return new Result("edited successfully",true);
            }
            return new Result("there is no Parent category with this name",false);
        }
        return new Result("there is no category with this name",false);
    }

    public Result deleteCategory(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            categoryRepository.delete(categoryOptional.get());
            return new Result("deleted successfully",true);
        }
        return new Result("Not found",false);
    }
}
