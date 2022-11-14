package com.ford.sparepartbooking.service;

import com.ford.sparepartbooking.exceptions.PermissionDeniedException;
import com.ford.sparepartbooking.exceptions.SparePartAbsentException;
import com.ford.sparepartbooking.models.sparepart.SparePart;
import com.ford.sparepartbooking.models.sparepart.SparePartRequest;
import com.ford.sparepartbooking.repository.SparePartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;
    private final UserService userService;

    public SparePartService(SparePartRepository sparePartRepository, UserService userService) {
        this.sparePartRepository = sparePartRepository;
        this.userService = userService;
    }

    public Iterable<SparePart> getSparePart(long sparePartId) {
        Optional<SparePart> oSparePart = sparePartRepository.findById(sparePartId);

        if(oSparePart.isEmpty())
            throw new SparePartAbsentException(sparePartId);

        return List.of(oSparePart.get());
    }

    public Iterable<SparePart> getSparePart() {
        return sparePartRepository.findAll();
    }

    public SparePart addSparePart(SparePartRequest sparePartRequest) {
        if(!userService.isAdmin(sparePartRequest.getUserId()))
            throw new PermissionDeniedException("Permission Denied to Add Spare Part");

        SparePart sparePart = new SparePart();
        sparePart.setName(sparePartRequest.getSparePart());
        sparePart.setQuantity(sparePartRequest.getQuantity());

        return sparePartRepository.save(sparePart);
    }

    public SparePart updateSparePart(long sparePartId, SparePartRequest sparePartRequest) {
        if(!userService.isAdmin(sparePartRequest.getUserId()))
            throw new PermissionDeniedException("Permission Denied to Update Spare Part");

        try {
            Iterable<SparePart> spareParts = getSparePart(sparePartId);
            SparePart sparePart = spareParts.iterator().next();
            sparePart.setName(sparePartRequest.getSparePart());
            sparePart.setQuantity(sparePartRequest.getQuantity());

            return sparePartRepository.save(sparePart);

        }catch (SparePartAbsentException ex) {
            return addSparePart(sparePartRequest);
        }
    }

    public boolean deleteSparePart(long sparePartId) {
        getSparePart(sparePartId);
        sparePartRepository.deleteById(sparePartId);
        return true;
    }

    public boolean inStock(String sparePartName, int quantity) {
        SparePart sparePart = getSparePart(sparePartName);
        return sparePart.getQuantity() >= quantity;
    }

    public SparePart getSparePart(String sparePartName) {
        return sparePartRepository.findByName(sparePartName);
    }

    public void updateSparePart(String sparePartName, int quantity) {
        SparePart sparePart = getSparePart(sparePartName);
        sparePart.setQuantity(sparePart.getQuantity() - quantity);
        sparePartRepository.save(sparePart);
    }
}
