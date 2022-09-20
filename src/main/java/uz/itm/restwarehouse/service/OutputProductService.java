package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.restwarehouse.entity.*;
import uz.itm.restwarehouse.loader.InputProductLoader;
import uz.itm.restwarehouse.loader.OutputLoader;
import uz.itm.restwarehouse.loader.OutputProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
OutputRepository outputRepository;
    @Autowired
    OutputProductRepository outputProductRepository;

    public Result addOutputProduct(OutputProductLoader outputProductLoader) throws ParseException {
        OutputProduct outputProduct=new OutputProduct();

        Optional<Product> productOptional = productRepository.findById(outputProductLoader.getProductId());
        if (productOptional.isPresent()){
            outputProduct.setProduct(productOptional.get());
            Optional<Output> outputOptional = outputRepository.findById(outputProductLoader.getInputId());
            if (outputOptional.isPresent()){
                outputProduct.setOutput(outputOptional.get());
                outputProduct.setAmount(outputProductLoader.getAmount());
                outputProduct.setPrice(outputProductLoader.getPrice());
                outputProductRepository.save(outputProduct);
                return new  Result("added successfully",true);
            }
            return new Result("there is no output with this data",false);

        }
        return new Result("there is no product with this name",false);
    }

    public List<OutputProduct> getOutputProducts() {
        return outputProductRepository.findAll();
    }

    public OutputProduct getOutputProductById(Integer id) {
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);
        return outputProductOptional.orElse(null);
    }

    public List<OutputProduct> getOutputProductByInputId(Integer outputId) {
        return outputProductRepository.findAllByOutput_Id(outputId);
    }

    public Result editOutputProduct(Integer id, OutputProductLoader outputProductLoader) {
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);
        if (outputProductOptional.isPresent()){
            OutputProduct outputProduct = outputProductOptional.get();
            outputProduct.setAmount(outputProductLoader.getAmount());
            outputProduct.setPrice(outputProductLoader.getPrice());
            Optional<Product> optionalProduct = productRepository.findById(outputProductLoader.getProductId());
            optionalProduct.ifPresent(outputProduct::setProduct);
            Optional<Output> outputOptional = outputRepository.findById(outputProductLoader.getInputId());
            outputOptional.ifPresent(outputProduct::setOutput);
            outputProductRepository.save(outputProduct);
            return new Result("edited successfully",true);
        }
        return new Result("Not found",false);
    }

    public Result deleteOutputProduct(Integer id) {
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);
        if (outputProductOptional.isPresent()){
            outputProductRepository.delete(outputProductOptional.get());
            return new Result("deleted successfully",true);

        }
        return new Result("Not found",false);
    }
}
