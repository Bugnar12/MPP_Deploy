package org.backendspring_boot.backendspring_boot.controller;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class AntivirusController {
    private static final Logger logger = LoggerFactory.getLogger(AntivirusController.class);

    @Autowired
    private AntivirusServiceImpl antivirusService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public AntivirusController(AntivirusServiceImpl antivirusService)
    {
        this.antivirusService = antivirusService;
    }

    @GetMapping("/antivirusList")
    public ResponseEntity<List<Antivirus>> getAllAntiviruses(){
        logger.info("Getting all antiviruses\n");
        List<Antivirus> antivirusList = antivirusService.getAllAntivirus();
        return ResponseEntity.ok(antivirusList);
    }

    @GetMapping("antivirusList/{id}")
    public ResponseEntity<Antivirus> getAntivirusById(@PathVariable int id){
        Antivirus antivirus = antivirusService.getAntivirusById(id);
        if (antivirus == null) {
            return ResponseEntity.badRequest().body(null);
        }
        logger.info("Getting antivirus with id: \n" + id + "\n");
        return ResponseEntity.ok(antivirus);
    }

    @PostMapping("/antivirusList")
    public ResponseEntity<String> addAntivirus(@RequestBody Antivirus antivirus)
    {
        if (antivirus.getName() == null || antivirus.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("The name field is required.");
        }

        if (antivirus.getProducer() == null || antivirus.getProducer().isEmpty()) {
            return ResponseEntity.badRequest().body("The producer field is required.");
        }

        if (antivirus.getDescription() == null || antivirus.getDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("The description field is required.");
        }

        if (antivirus.getReleaseDate() == null) {
            return ResponseEntity.badRequest().body("The release date field is required.");
        }

        if (antivirus.getReleaseDate().after(new Date())) {
            return ResponseEntity.badRequest().body("The release date cannot be in the future.");
        }

        antivirusService.addAntivirus(antivirus);


        logger.info("Adding antivirus: " + antivirus + "\n");
        return ResponseEntity.ok("Antivirus added successfully.");
    }

    @PutMapping("antivirusList/{id}")
    public ResponseEntity<String> updateAntivirus(@PathVariable int id, @RequestBody Antivirus updatedAntivirus)
    {
        Antivirus antivirus = antivirusService.getAntivirusById(id);
        if (antivirus == null) {
            return ResponseEntity.badRequest().body("The antivirus with the given id does not exist.");
        }

        if (updatedAntivirus.getName() == null || updatedAntivirus.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("The name field is required.");
        }

        if (updatedAntivirus.getProducer() == null || updatedAntivirus.getProducer().isEmpty()) {
            return ResponseEntity.badRequest().body("The producer field is required.");
        }

        if (updatedAntivirus.getDescription() == null || updatedAntivirus.getDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("The description field is required.");
        }

        if (updatedAntivirus.getReleaseDate() == null) {
            return ResponseEntity.badRequest().body("The release date field is required.");
        }

        antivirusService.updateAntivirus(id, updatedAntivirus);
        logger.info("Updating antivirus with id: " + id + " with data: " + updatedAntivirus + "\n");
        return ResponseEntity.ok("Antivirus updated successfully.");
    }

    @DeleteMapping("antivirusList/{id}")
    public ResponseEntity<Void> deleteAntivirus(@PathVariable int id){
        try {
            antivirusService.deleteAntivirus(id);
            logger.info("Deleting antivirus with id: " + id + "\n");
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")

    public String broadcastMessage(@Payload String message)
    {
        return "Recieved message " + message;
    }

    @Scheduled(fixedRate = 10000)
    public void sendAntivirusPeriodically()
    {
        Antivirus generatedAntivirus = antivirusService.generateAndAddAntivirus();
        simpMessagingTemplate.convertAndSend("/topic/antivirus", generatedAntivirus);
    }
}