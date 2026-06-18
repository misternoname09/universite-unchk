package com.universite.unchk.controller;

import com.universite.unchk.entity.Etudiant;
import com.universite.unchk.service.EtudiantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // 🔹 Récupérer tous les étudiants (ADMIN/ENSEIGNANT)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public List<Etudiant> getAll() {
        return etudiantService.getAll();
    }

    // 🔹 Récupérer le profil de l'étudiant connecté
    @GetMapping("/me")
    @PreAuthorize("hasRole('ETUDIANT')")
    public ResponseEntity<Etudiant> getCurrentEtudiant() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Etudiant etudiant = etudiantService.findByUsername(username);
        return ResponseEntity.ok(etudiant);
    }

    // 🔹 Récupérer un étudiant par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT')")
    public ResponseEntity<Etudiant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getById(id));
    }

    // 🔹 Créer un étudiant
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Etudiant> create(@Valid @RequestBody Etudiant etudiant) {
        return new ResponseEntity<>(etudiantService.create(etudiant), HttpStatus.CREATED);
    }

    // 🔹 Mettre à jour un étudiant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Etudiant> update(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant) {
        return ResponseEntity.ok(etudiantService.update(id, etudiant));
    }

    // 🔹 Supprimer un étudiant
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etudiantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Exporter en PDF
    @GetMapping("/export/pdf")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportPdf() throws DocumentException {
        List<Etudiant> etudiants = etudiantService.getAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("Liste des étudiants", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.addCell("INE");
        table.addCell("Nom");
        table.addCell("Prénom");
        table.addCell("Formation");
        table.addCell("Promo");

        for (Etudiant e : etudiants) {
            table.addCell(e.getIne());
            table.addCell(e.getNom());
            table.addCell(e.getPrenom());
            table.addCell(e.getFormation());
            table.addCell(e.getPromo());
        }

        document.add(table);
        document.close();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "attachment; filename=etudiants.pdf")
                .body(out.toByteArray());
    }

    // 🔹 Exporter en Excel
    @GetMapping("/export/excel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        List<Etudiant> etudiants = etudiantService.getAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Etudiants");

        Row header = sheet.createRow(0);
        String[] columns = {"INE", "Nom", "Prénom", "Formation", "Promo"};
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        int rowIdx = 1;
        for (Etudiant e : etudiants) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(e.getIne());
            row.createCell(1).setCellValue(e.getNom());
            row.createCell(2).setCellValue(e.getPrenom());
            row.createCell(3).setCellValue(e.getFormation());
            row.createCell(4).setCellValue(e.getPromo());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=etudiants.xlsx")
                .body(out.toByteArray());
    }
}
