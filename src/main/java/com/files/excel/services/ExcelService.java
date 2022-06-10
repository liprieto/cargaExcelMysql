package com.files.excel.services;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.files.excel.entity.ExcelModel;
import com.files.excel.helper.ExcelHelper;
import com.files.excel.repository.ExcelRepository;

@Service
public class ExcelService {
  @Autowired
  ExcelRepository repository;
  public void save(MultipartFile file) {
    try {
      List<ExcelModel> excelmodels = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(excelmodels);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
  public List<ExcelModel> getAllExcelModels() {
    return repository.findAll();
  }
}