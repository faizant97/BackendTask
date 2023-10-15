package com.geoclass.backendtask.service;

import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.GeologicalClassRepository;

import java.util.List;

public interface GetListByCodeService {

    List<String> getSectionListByClassCode(String code);

}
