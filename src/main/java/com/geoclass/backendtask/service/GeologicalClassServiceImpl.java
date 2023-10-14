package com.geoclass.backendtask.service;

import com.geoclass.backendtask.dto.CreateGeologicalClassDTO;
import com.geoclass.backendtask.dto.UpdateGeologicalClassDTO;
import com.geoclass.backendtask.entities.GeologicalClassEntity;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.GeologicalClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeologicalClassServiceImpl implements GeologicalClassService {

    private final GeologicalClassRepository geologicalClassRepository;

    public GeologicalClassServiceImpl(GeologicalClassRepository geologicalClassRepository) {
        this.geologicalClassRepository = geologicalClassRepository;
    }

    @Override
    public List<GeologicalClassEntity> getGeologicalClass() {

        List<GeologicalClassEntity> geologicalClassEntityList;
        geologicalClassEntityList = geologicalClassRepository.findAll();
        return geologicalClassEntityList;

    }

    @Override
    public  GeologicalClassEntity createGeologicalClass(CreateGeologicalClassDTO createGeologicalClassDTO){
        GeologicalClassEntity geologicalClassEntity = new GeologicalClassEntity();
        if(!createGeologicalClassDTO.getName().isEmpty()) {
            geologicalClassEntity.setClassName(createGeologicalClassDTO.getName());
            geologicalClassEntity.setClassCode(createGeologicalClassDTO.getClassCode());
            geologicalClassEntity = geologicalClassRepository.save(geologicalClassEntity);
            return geologicalClassEntity;
        }
        else {
            geologicalClassEntity.setClassCode("Please Write a value");
            geologicalClassEntity.setClassName("Please Write a value");
            return geologicalClassEntity;
        }
    }

    @Override
    public String deleteGeologicalClass(CreateGeologicalClassDTO createGeologicalClassDTO){
        GeologicalClassEntity geologicalClassDeleted;
        if(!createGeologicalClassDTO.getName().isEmpty() || !createGeologicalClassDTO.getClassCode().isEmpty() ) {
            Optional<GeologicalClassEntity> result = search(createGeologicalClassDTO.getName(), createGeologicalClassDTO.getClassCode());
            if(result.isPresent()){
                geologicalClassDeleted = result.get();
                geologicalClassRepository.delete(geologicalClassDeleted);
                return  geologicalClassDeleted.getClassName() + " is deleted!";
            }
        }
        return "Class not found!";
    }

    @Override
    public String updateGeologicalClass(UpdateGeologicalClassDTO updateGeologicalClassDTO){

        GeologicalClassEntity geologicalClassEntityToUpdated;
        if ((!updateGeologicalClassDTO.getExistingName().isEmpty() && !updateGeologicalClassDTO.getUpdatedName().isEmpty())
        || (!updateGeologicalClassDTO.getExistingClassCode().isEmpty() && !updateGeologicalClassDTO.getUpdatedClassCode().isEmpty())){
            Optional<GeologicalClassEntity> result = search(updateGeologicalClassDTO.getExistingName(), updateGeologicalClassDTO.getExistingClassCode());
            if(result.isPresent()){
                geologicalClassEntityToUpdated = result.get();
                geologicalClassEntityToUpdated.setClassName(updateGeologicalClassDTO.getUpdatedName());
                geologicalClassEntityToUpdated.setClassCode(updateGeologicalClassDTO.getUpdatedClassCode());
                geologicalClassRepository.save(geologicalClassEntityToUpdated);
                return updateGeologicalClassDTO.getExistingName() + " / " + updateGeologicalClassDTO.getExistingName() + " is updated to " +
                        updateGeologicalClassDTO.getUpdatedName() + " / " + updateGeologicalClassDTO.getUpdatedClassCode();
            }
        }
        return "Please write existing and updated Fields!";
    }



    private Optional<GeologicalClassEntity> search(String name, String classCode){
        List<GeologicalClassEntity>  geologicalClassEntityList;
        geologicalClassEntityList = geologicalClassRepository.findAll();
        return geologicalClassEntityList.stream()
                .filter(item -> item.getClassName().equals(name) || item.getClassCode().equals(classCode)  )
                .findFirst();
    }
}
