package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.itm.restwarehouse.entity.*;
import uz.itm.restwarehouse.loader.ProductById;
import uz.itm.restwarehouse.loader.ProductLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.repository.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result addProduct(ProductLoader productLoader) {
        boolean existsByName = productRepository.existsByName(productLoader.getName());
        if (existsByName)
            return new Result("the product with this name is already exist", false);
        Product product = new Product();

        Optional<Category> categoryOptional = categoryRepository.findById(productLoader.getCategoryId());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
            Optional<Measurement> measurementOptional = measurementRepository.findById(productLoader.getMeasurementId());
            if (measurementOptional.isPresent()) {
                product.setMeasurement(measurementOptional.get());
                product.setName(productLoader.getName());
                product.setActive(productLoader.isActive());
                product.setCode(productLoader.getCode());
                productRepository.save(product);
                return new Result("added successfully", true);
            }
            return new Result("there is no measurement with this name", false);

        }
        return new Result("there is no category with this name", false);


    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ProductById getProductById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(product -> new ProductById(true, product)).orElseGet(() -> new ProductById(false, null));
    }

    public Result editProduct(Integer id, ProductLoader productLoader) {
        boolean existsByNameAndIdNot = productRepository.existsByNameAndIdNot(productLoader.getName(), id);
        if (existsByNameAndIdNot)
            return new Result("the product with this name is already exist", false);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productLoader.getName());
            product.setActive(productLoader.isActive());
            product.setCode(productLoader.getCode());
            Optional<Category> categoryOptional = categoryRepository.findById(productLoader.getCategoryId());
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
                Optional<Measurement> measurementOptional = measurementRepository.findById(productLoader.getMeasurementId());
                if (measurementOptional.isPresent()) {
                    product.setMeasurement(measurementOptional.get());
                    productRepository.save(product);
                    return new Result("edited successfully", true);
                }
                return new Result("there is no measurement with this name", false);

            }
            return new Result("there is no category with this name", false);

        }
        return new Result("there is no product with this name", false);

    }

    public Result deleteProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return new Result("deleted successfully", true);
        }
        return new Result("there is no product with this name", false);
    }

    public Result addProductPhoto(MultipartHttpServletRequest request, Integer productId) throws ServletException, IOException {
        for (Part part : request.getParts()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()){
                Product product = productOptional.get();
                Attachment attachment=new Attachment();
                attachment.setFileOriginalName(part.getSubmittedFileName());
                attachment.setContentType(part.getContentType());
                attachment.setSize(part.getSize());
                product.addAttachment(attachment);
                MultipartFile file = request.getFile(part.getSubmittedFileName());
                AttachmentContent attachmentContent=new AttachmentContent();
                attachmentContent.setAttachment(attachment);
                assert file != null;
                attachmentContent.setMainContent(file.getBytes());
                attachmentContent.setAttachment(attachment);
                attachmentContentRepository.save(attachmentContent);
                productRepository.save(product);
                return new Result("Photo added successfully",true);
            }
        }
        return new Result("there is no product with this name",false);

    }


    public Result deleteProductPhoto(Integer productId, Integer photoId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(photoId);
            if (attachmentOptional.isPresent()){
                Optional<AttachmentContent> attachmentContentByAttachment_id = attachmentContentRepository.findAttachmentContentByAttachment_Id(photoId);
                if (attachmentContentByAttachment_id.isPresent()){
                    product.removeAttachment(attachmentOptional.get());
                    attachmentContentRepository.delete(attachmentContentByAttachment_id.get());
                    return new Result("photo is deleted successfully",true);
                }
                return new Result("there is no Attachment Content to remove",false);
            }
            return new Result("there is no photo to remove with this name",false);
        }
        return new Result("there is no product with this name",false);
    }

    public void getProductPhotos(Integer productId, HttpServletResponse response) throws IOException {
        List<Attachment> allByProduct_id = attachmentRepository.findAllByProduct_Id(productId);
        for (Attachment attachment : allByProduct_id) {
            Optional<AttachmentContent> attachmentContentByAttachment_id = attachmentContentRepository.findAttachmentContentByAttachment_Id(attachment.getId());
            if (attachmentContentByAttachment_id.isPresent()){
                response.setHeader("Content-Disposition","attachment; filename\""+attachment.getFileOriginalName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContentByAttachment_id.get().getMainContent(),response.getOutputStream());
            }

        }
    }
}
