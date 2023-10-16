package com.geoclass.backendtask.controller;


import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.service.GetListByCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getSectionsList")
public class GetListByCourseCodeController {

   private final GetListByCodeService getListByCodeService;

    public GetListByCourseCodeController( GetListByCodeService getListByCodeService) {
        this.getListByCodeService = getListByCodeService;

    }

  @GetMapping("/by-code")
   public List<String> getSectionListByCode(@RequestParam String code){
        return getListByCodeService.getSectionListByClassCode(code);
  }



}
