package uz.itm.restwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import uz.itm.restwarehouse.entity.Users;
import uz.itm.restwarehouse.entity.Warehouse;
import uz.itm.restwarehouse.loader.Result;
import uz.itm.restwarehouse.loader.UsersById;
import uz.itm.restwarehouse.loader.UsersLoader;
import uz.itm.restwarehouse.repository.UsersRepository;
import uz.itm.restwarehouse.repository.WarehouseRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    public Result addUser( UsersLoader usersLoader){

        boolean existsByPhoneNumber = usersRepository.existsByPhoneNumber(usersLoader.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("The user with this phone number is already is exist",false);
        Users users=new Users();
        users.setFirstName(usersLoader.getFirstName());
        users.setLastName(usersLoader.getLastName());
        users.setPassword(usersLoader.getPassword());
        users.setCode(usersLoader.getCode());
        users.setPhoneNumber(usersLoader.getPhoneNumber());
        users.setActive(usersLoader.isActive());
        usersRepository.save(users);
        return new Result("added successfully",true);

    }

public Result makeResponsibleForWarehouse(Integer warehouseId,Integer userId){
    Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
    if (warehouseOptional.isPresent()){
        Optional<Users> usersOptional = usersRepository.findById(userId);
        if (usersOptional.isPresent()){
            Warehouse warehouse = warehouseOptional.get();
            Users users = usersOptional.get();
            users.add(warehouse);
            usersRepository.save(users);
            return new Result("added successfully",true);

        }
        return new Result("there is no user with this name",false);
    }
    return new Result("there is no warehouse with this data",false);


}
    public Result removeResponsibility(Integer warehouseId,Integer userId){
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
        if (warehouseOptional.isPresent()){
            Optional<Users> usersOptional = usersRepository.findById(userId);
            if (usersOptional.isPresent()){
                Warehouse warehouse = warehouseOptional.get();
                Users users = usersOptional.get();
                users.remove(warehouse);
                usersRepository.save(users);
                return new Result("removed successfully",true);

            }
            return new Result("there is no user with this name",false);
        }
        return new Result("there is no warehouse with this data",false);


    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public UsersById getUserById(Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()){
            Users users = usersOptional.get();
            return new UsersById(true,users);
        }
        return new UsersById(false,null);
    }

    public Result editUser(Integer id, UsersLoader usersLoader) {
        boolean existsByPhoneNumberAndIdNot = usersRepository.existsByPhoneNumberAndIdNot(usersLoader.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot)
            return new Result("the user with this phone number is already exist", false);
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()){
            Users users = usersOptional.get();
            users.setFirstName(usersLoader.getFirstName());
            users.setLastName(usersLoader.getLastName());
            users.setPhoneNumber(usersLoader.getPhoneNumber());
            users.setCode(usersLoader.getCode());
            users.setActive(usersLoader.isActive());
            users.setPassword(usersLoader.getPassword());
            usersRepository.save(users);
            return new Result("edited successfully",true);

        }
        return new Result("there is no user with this name",false);
    }

    public Result deleteUser(Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()){
            usersRepository.delete(usersOptional.get());
            return new Result("deleted successfully",true);
        }
        return new Result("there is no user with this name",false);
    }
}
