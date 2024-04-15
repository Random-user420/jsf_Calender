package main.java;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleRangeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class CalendarBean implements Serializable {

    @Getter
    @Setter
    private ScheduleModel scheduleModel = new DefaultScheduleModel();

    private List<String> selectedKalenders;

    @Setter
    @Getter
    private ScheduleEvent event = new DefaultScheduleEvent();

    private final List<ScheduleEvent> allEvents = new ArrayList<>();


    @Setter
    private LocalDateTime tempValidation;

    @Setter
    private boolean validationFailed;

    public void addEvent() {
        if (isValidationFailed()) {
            return;
        }
        if (event.getId() == null) {
            scheduleModel.addEvent(event);
            getAllEvents().add(event);
        } else {
            scheduleModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
        updateViewCalenders();
    }

    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
        setEvent(selectEvent.getObject());
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        setEvent(DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build());
    }

    public void onRangeSelect(ScheduleRangeEvent selectRange) {
        setEvent(DefaultScheduleEvent.builder().startDate(selectRange.getStartDate()).endDate(selectRange.getEndDate()).build());
    }

    public List<String> getSelectedKalenders() {
        return selectedKalenders;
    }

    public void setSelectedKalenders(List<String> selectedKalenders) {
        this.selectedKalenders = selectedKalenders;
        updateViewCalenders();
    }

    public void updateViewCalenders() {
        scheduleModel.clear();
        if (selectedKalenders.contains("K1")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K1")) {
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K2")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K2")) {
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K3")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K3")) {
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K4")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K4")) {
                    scheduleModel.addEvent(_event);
                }
            }
        }
    }

    public List<ScheduleEvent> getAllEvents() {
        return allEvents;
    }

    public void validateEndDateInput(FacesContext context, UIComponent component, LocalDateTime value) {
        if (Objects.equals(getTempValidation(), null)) {
            setValidationFailed(true);
            context.validationFailed();
        } else if (value.isBefore(getTempValidation())) {
            setTempValidation(null);
            setValidationFailed(true);
            context.validationFailed();
        } else {
            setTempValidation(null);
            setValidationFailed(false);
        }
    }

    public void validateStartDateInput(FacesContext context, UIComponent component, LocalDateTime value) {
        setTempValidation(value);
    }

    public LocalDateTime getTempValidation() {
        return tempValidation;
    }

    public boolean isValidationFailed() {
        return validationFailed;
    }

}