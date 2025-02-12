package de.starwit.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.starwit.data.AlertDb;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertDb alertDb;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(alertDb.getAlerts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        try {
            alertDb.getAlerts().add(name);
            return new ResponseEntity<>("GetOne Result: " + name, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Scheduled(fixedDelayString = "${alert.delay:60s}")
    public void executeActions() {
        List<String> alerts = new ArrayList<>();
        List<String> oldAlerts = alertDb.getAlerts();
        int max = Math.min(oldAlerts.size(), 5);
        for (int i = 0; i < max; i++) {
            alerts.add(oldAlerts.get(i));
        }
        alertDb.setAlerts(alerts);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody AlertDto dto) {
        try {
            return new ResponseEntity<>("Create Result: " + dto.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody AlertDto dto) {
        try {
            // TODO Implement Your Logic To Update Data And Return Result Through
            // ResponseEntity
            return new ResponseEntity<>("Update Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        try {
            List<String> oldAlerts = alertDb.getAlerts();
            oldAlerts.removeIf(n -> n.equals(name));
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
