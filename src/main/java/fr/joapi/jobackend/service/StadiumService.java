package fr.joapi.jobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.joapi.jobackend.dto.StadiumDto;
import fr.joapi.jobackend.model.Stadium;
import fr.joapi.jobackend.repository.StadiumRepository;
import jakarta.transaction.Transactional;

@Service
public class StadiumService {
    private final StadiumRepository repository;

    @Autowired
    public StadiumService(StadiumRepository repository) {
        this.repository = repository;
    }

    public List<Stadium> findAllStadiums() {
        return repository.findAll();
    }

    public Stadium findStadiumById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Stadium create(StadiumDto stadium) {
        Stadium stadiumToCreate = new Stadium(stadium.getName(), stadium.getAddress(), stadium.getCapacity());
        return repository.save(stadiumToCreate);
    }

    @Transactional
    public boolean delete(String uuid) {
        Stadium stadiumToDelete = findStadiumById(uuid);
        if (stadiumToDelete != null) {
            repository.delete(stadiumToDelete);
            return true;
        }
        return false;
    }

    public boolean update(String uuid, StadiumDto event) {
        Stadium eventToUpdate = findStadiumById(uuid);

        if (eventToUpdate != null) {
            eventToUpdate.setName(event.getName());
            eventToUpdate.setAddress(event.getAddress());
            eventToUpdate.setCapacity(event.getCapacity());
            repository.save(eventToUpdate);
            return true;
        }
        return false;
    }
}
