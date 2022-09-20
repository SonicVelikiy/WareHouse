package uz.itm.restwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.itm.restwarehouse.entity.Output;
import uz.itm.restwarehouse.loader.OutputLoader;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.service.OutputService;
import java.util.List;


    @RestController
    @RequestMapping("/output")
    public class OutputController {
        @Autowired
        OutputService outputService;

        @PostMapping
        public ResponseEntity<Result> addOutput(@RequestBody OutputLoader outputLoader) {
            Result result = outputService.addOutput(outputLoader);
            if (result.isSuccess())
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        @GetMapping
        public List<Output> getOutputs() {
            return outputService.getOutputs();

        }

        @GetMapping("/{id}")
        public ResponseEntity<Output> getOutputById(@PathVariable Integer id) {
            Output outputById = outputService.getOutputById(id);
            if (outputById != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(outputById);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        @PutMapping("/{id}")
        public ResponseEntity<Result>editOutput(@PathVariable Integer id,@RequestBody OutputLoader outputLoader){
            Result result = outputService.editOutput(id, outputLoader);
            if (result.isSuccess())
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Result>deleteOUTPUT(@PathVariable Integer id){
            Result result = outputService.deleteOutput(id);
            if (result.isSuccess())
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
}
