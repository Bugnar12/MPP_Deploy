package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

@Service
public class AntivirusServiceImpl implements IService{
    private final List<Antivirus> antivirusList = new ArrayList<>();
    private int nextId = 1;

    public AntivirusServiceImpl(){
        try {
            Faker faker = new Faker();
            for(int i = 0; i < 5; i++)
            {
                Antivirus generatedAntivirus = generateRandomAntivirus(faker);
                addAntivirus(generatedAntivirus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private Antivirus generateRandomAntivirus(Faker faker) {
        String name = faker.app().name();
        String producer = faker.company().name();
        String description = faker.lorem().sentence();
        boolean supportMultiPlatform = faker.bool().bool();
        Date releaseDate = faker.date().past(10, TimeUnit.DAYS);

        return new Antivirus(0, name, producer, description, supportMultiPlatform, releaseDate);
    }

    @Override
    public List<Antivirus> getAllAntivirus() {
        return antivirusList;
    }

    @Override
    public Antivirus getAntivirusById(int id) {
        return antivirusList.stream().filter(antivirus -> antivirus.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addAntivirus(Antivirus antivirus) {
        antivirus.setId(nextId);
        nextId++;
        antivirusList.add(antivirus);
    }

        @Override
        public void updateAntivirus(int id, Antivirus updatedAntvirus) {
            Antivirus antivirus = getAntivirusById(id);
            if(antivirus != null){
                antivirus.setName(updatedAntvirus.getName());
                antivirus.setProducer(updatedAntvirus.getProducer());
                antivirus.setDescription(updatedAntvirus.getDescription());
                antivirus.setSupportMultiPlatform(updatedAntvirus.isSupportMultiPlatform());
                antivirus.setReleaseDate(updatedAntvirus.getReleaseDate());
            }
        }
        @Override
        public void deleteAntivirus(int id) {
            Antivirus antivirus = getAntivirusById(id);
            if(antivirus != null){
                antivirusList.remove(antivirus);
            }
            else{
                throw new RuntimeException("Antivirus not found");
            }
        }

    public Antivirus generateAndAddAntivirus() {
        Faker faker = new Faker();
        Antivirus generatedAntivirus = generateRandomAntivirus(faker);
        addAntivirus(generatedAntivirus);
        return generatedAntivirus;
    }
}
