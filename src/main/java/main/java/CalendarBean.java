package main.java;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleRangeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("rawtypes")
@Named
@ViewScoped
public class CalendarBean implements Serializable {

    private ScheduleModel scheduleModel = new DefaultScheduleModel();
    private int selectedYear;

    private List<String> selectedKalenders;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private List<ScheduleEvent> allEvents = new ArrayList<>();

    public ScheduleEvent getEvent() {
        return event;
    }

    private LocalDateTime tempValidation;

    private boolean validationFailed;


    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    // Getter und Setter für scheduleModel und selectedYear

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }

    public void addEvent() {
        if (isValidationFailed()) {
            return;
        }
        if (event.getId() == null) {
            scheduleModel.addEvent(event);
            getAllEvents().add(event);
            event = new DefaultScheduleEvent();
        } else {
            scheduleModel.updateEvent(event);
            event = new DefaultScheduleEvent();
        }
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
                if (Objects.equals(_event.getData(), "K1")){
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K2")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K2")){
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K3")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K3")){
                    scheduleModel.addEvent(_event);
                }
            }
        }
        if (selectedKalenders.contains("K4")) {
            for (ScheduleEvent _event : getAllEvents()) {
                if (Objects.equals(_event.getData(), "K4")){
                    scheduleModel.addEvent(_event);
                }
            }
        }
    }

    public List<ScheduleEvent> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(List<ScheduleEvent> allEvents) {
        this.allEvents = allEvents;
    }

    public void validateEndDateInput(FacesContext context, UIComponent component, LocalDateTime value) {
        if (Objects.equals(getTempValidation(), null)) {
            setValidationFailed(true);
            context.validationFailed();
        } else if(value.isBefore(getTempValidation())) {
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

    public void setTempValidation(LocalDateTime tempValidation) {
        this.tempValidation = tempValidation;
    }

    public boolean isValidationFailed() {
        return validationFailed;
    }

    public void setValidationFailed(boolean validationFailed) {
        this.validationFailed = validationFailed;
    }
}