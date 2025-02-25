package de.starwit.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import de.starwit.rest.AlertDto;

@ApplicationScope
@Component
public class AlertDb {

    private List<AlertDto> alerts = new ArrayList<>();

    private List<AlertDto> marker = new ArrayList<>();

    public List<AlertDto> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertDto> alerts) {
        this.alerts = alerts;
    }

    public List<AlertDto> getMarker() {
        return marker;
    }

    public void setMarker(List<AlertDto> marker) {
        this.marker = marker;
    }

}
