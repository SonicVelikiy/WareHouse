package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.restwarehouse.entity.Input;
import uz.itm.restwarehouse.entity.InputProduct;
import uz.itm.restwarehouse.entity.Product;
import uz.itm.restwarehouse.loader.InputProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.InputProductRepository;
import uz.itm.restwarehouse.repository.InputRepository;
import uz.itm.restwarehouse.repository.ProductRepository;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result addInputProduct(InputProductLoader inputProductLoader) throws ParseException {
        InputProduct inputProduct=new InputProduct();

        Optional<Product> productOptional = productRepository.findById(inputProductLoader.getProductId());
        if (productOptional.isPresent()){
            inputProduct.setProduct(productOptional.get());
            Optional<Input> inputOptional = inputRepository.findById(inputProductLoader.getInputId());
            if (inputOptional.isPresent()){
                inputProduct.setInput(inputOptional.get());
                inputProduct.setAmount(inputProductLoader.getAmount());
                inputProduct.setPrice(inputProductLoader.getPrice());
                inputProduct.setExpireDate(inputProductLoader.getExpireDate());
                inputProductRepository.save(inputProduct);
                return new  Result("added successfully",true);
            }
            return new Result("there is no input with this data",false);

        }
        return new Result("there is no product with this name",false);
    }

    public List<InputProduct> getInputProducts() {
        return inputProductRepository.findAll();
    }

    public InputProduct getInputProductById(Integer id) {
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        return inputProductOptional.orElse(null);
    }

    public List<InputProduct> getInputProductByInputId(Integer inputId) {
        return inputProductRepository.findAllByInput_Id(inputId);
    }

    public Result editInputProduct(Integer id, InputProductLoader inputProductLoader) {
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        if (inputProductOptional.isPresent()){
            InputProduct inputProduct = inputProductOptional.get();
            inputProduct.setAmount(inputProductLoader.getAmount());
            inputProduct.setPrice(inputProductLoader.getPrice());
            inputProduct.setExpireDate(inputProductLoader.getExpireDate());
            Optional<Product> optionalProduct = productRepository.findById(inputProductLoader.getProductId());
            optionalProduct.ifPresent(inputProduct::setProduct);
            Optional<Input> inputOptional = inputRepository.findById(inputProductLoader.getInputId());
            inputOptional.ifPresent(inputProduct::setInput);
            inputProductRepository.save(inputProduct);
            return new Result("edited successfully",true);
        }
        return new Result("Not found",false);
    }

    public Result deleteInputProduct(Integer id) {
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        if (inputProductOptional.isPresent()){
            inputProductRepository.delete(inputProductOptional.get());
            return new Result("deleted successfully",true);

        }
        return new Result("Not found",false);
    }
}
