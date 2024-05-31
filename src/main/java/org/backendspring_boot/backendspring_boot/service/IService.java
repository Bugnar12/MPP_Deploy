package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;

import java.util.List;

public interface IService {
    List<Antivirus> getAllAntivirus();
    Antivirus getAntivirusById(int id);
    void addAntivirus(Antivirus antivirus);
    void updateAntivirus(int id, Antivirus updatedAntvirus);
    void deleteAntivirus(int id);
}
