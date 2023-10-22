package com.geoclass.backendtask.service;

import com.geoclass.backendtask.dto.CreateSectionDTO;
import com.geoclass.backendtask.dto.DeleteSectionDTO;
import com.geoclass.backendtask.dto.SectionResponseDTO;
import com.geoclass.backendtask.dto.UpdateSectionDTO;
import com.geoclass.backendtask.entities.SectionEntity;

import java.util.List;


public interface SectionService {

 List<SectionEntity> getSection();
 SectionResponseDTO createSection(CreateSectionDTO sectionDTO);
 DeleteSectionDTO deleteSection(CreateSectionDTO createSectionDTO);
 String updateSection(UpdateSectionDTO updateSectionDTO);
}
