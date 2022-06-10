package com.files.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.excel.entity.ExcelModel;

public interface ExcelRepository extends JpaRepository<ExcelModel, Long>{

}
