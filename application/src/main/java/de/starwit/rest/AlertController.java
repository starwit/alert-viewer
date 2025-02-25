package de.starwit.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static final Logger LOG = LoggerFactory.getLogger(AlertController.class);

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

    @GetMapping("/marker")
    public ResponseEntity<?> findAllMarker() {
        try {
            return new ResponseEntity<>(alertDb.getMarker(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}/{lat}/{lng}")
    public ResponseEntity<?> find(@PathVariable String name, @PathVariable String lat, @PathVariable String lng) {
        try {
            if (lat != null && lng != null) {
                AlertDto alert = new AlertDto(name, Double.parseDouble(lat), Double.parseDouble(lng));
                alertDb.getAlerts().add(alert);
                alertDb.getMarker().add(alert);
                return new ResponseEntity<>("GetOne Result: " + alert.getName(), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("Error parsing geocoordinates.", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Scheduled(fixedDelayString = "${alert.delay:60s}")
    public void executeActions() {
        List<AlertDto> alerts = new ArrayList<>();
        List<AlertDto> oldAlerts = alertDb.getAlerts();
        alerts.addAll(oldAlerts);
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
            return new ResponseEntity<>("Update Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        try {
            List<AlertDto> oldAlerts = alertDb.getAlerts();
            List<AlertDto> toDelete = new ArrayList<>();
            if (oldAlerts != null && oldAlerts.size() > 0) {
                for (AlertDto alertDto : oldAlerts) {
                    if (name != null && name.equals(alertDto.getName())) {
                        toDelete.add(alertDto);
                    }
                }
                oldAlerts.removeAll(toDelete);
            }
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
