package com.files.excel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.files.excel.entity.ExcelModel;
import com.files.excel.helper.ExcelHelper;
import com.files.excel.message.ResponseMessage;
import com.files.excel.services.ExcelService;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
	@Autowired
	ExcelService fileService;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		System.out.println(1);
		return null;
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				fileService.save(file);
				message = "Archivo subido con éxito: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "No se pudo cargar el archivo: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Cargue archivo excel";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/rsp")
	public ResponseEntity<List<ExcelModel>> getAllExcelModels() {
		System.out.println("Prueba postman");
		try {
			List<ExcelModel> excelmodels = fileService.getAllExcelModels();
			if (excelmodels.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(excelmodels, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}