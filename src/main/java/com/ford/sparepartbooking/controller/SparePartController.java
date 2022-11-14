package com.ford.sparepartbooking.controller;

import com.ford.sparepartbooking.exceptions.SparePartAbsentException;
import com.ford.sparepartbooking.models.sparepart.SparePart;
import com.ford.sparepartbooking.models.sparepart.SparePartRequest;
import com.ford.sparepartbooking.service.SparePartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sparepart")
public class SparePartController {

    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping("/{sparePartId}")
    public ResponseEntity<Object> getSparePart(@PathVariable("sparePartId") long sparePartId) {
        return new ResponseEntity<>(sparePartService.getSparePart(sparePartId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getSparePart() {
        return new ResponseEntity<>(sparePartService.getSparePart(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addSparePart(@RequestBody  SparePartRequest request) {
        SparePart sparePart = sparePartService.addSparePart(request);

        Map<Object, Object> response = new HashMap<>();
        response.put("message", "Spare Part Created with id " + sparePart.getId());
        response.put("sparePart", sparePart);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{sparePartId}")
    public ResponseEntity<Object> updateSparePart(@PathVariable("sparePartId") long sparePartId,
                                                  @RequestBody SparePartRequest request) {

        SparePart sparePart = sparePartService.updateSparePart(sparePartId, request);

        Map<Object, Object> response = new HashMap<>();
        response.put("sparePart", sparePart);
        if(sparePart.getId() == sparePartId)
            response.put("message", "Spare Part updated with id " + sparePartId);
        else
            response.put("message", "Spare Part created with id " + sparePart.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sparePartId}")
    public ResponseEntity<Object> deleteSparePart(@PathVariable("sparePartId") long sparePartId) {
        boolean deleted = sparePartService.deleteSparePart(sparePartId);

        if(!deleted)
            throw new SparePartAbsentException(sparePartId);

        Map<Object, Object> response = new HashMap<>();
        response.put("message", "Deleted spare part with id " + sparePartId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
