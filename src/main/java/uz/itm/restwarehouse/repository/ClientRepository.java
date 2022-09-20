package uz.itm.restwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.restwarehouse.entity.Client;
import uz.itm.restwarehouse.projection.ClientProjection;

@RepositoryRestResource(path = "client",excerptProjection = ClientProjection.class)
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
