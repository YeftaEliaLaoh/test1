package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Accessors(chain = true)
public class EventMessage<T, D> implements Serializable {
    protected T eventId;
    protected String eventType;
    protected String eventHandler;
    protected OffsetDateTime timestamp = OffsetDateTime.now();
    protected String createdBy;
    protected String eventReferenceId;
    protected D data;

    public EventMessage(final T id) {
        this.eventId = id;
        this.timestamp = OffsetDateTime.now();
    }

    public EventMessage(final T id, final D data) {
        this.eventId = id;
        this.timestamp = OffsetDateTime.now();
        this.eventType = toCamelCase(data.getClass().getSimpleName());
        this.eventHandler = data.getClass().getSimpleName() + "Handler";
        this.data = data;
    }

    public EventMessage(final T id, final D data, UUID eventReferenceId) {
        this.eventId = id;
        this.timestamp = OffsetDateTime.now();
        this.eventReferenceId = eventReferenceId.toString();
        this.eventType = toCamelCase(data.getClass().getSimpleName());
        this.eventHandler = data.getClass().getSimpleName() + "Handler";
        this.data = data;
    }

    public EventMessage(final T id, final D data, String eventReferenceId) {
        this.eventId = id;
        this.timestamp = OffsetDateTime.now();
        this.eventReferenceId = eventReferenceId;
        this.eventType = toCamelCase(data.getClass().getSimpleName());
        this.eventHandler = data.getClass().getSimpleName() + "Handler";
        this.data = data;
    }

    public String getEventHandler() {
        return this.eventHandler != null
                ? this.eventHandler
                : String.format("%sHandler", StringUtils.capitalize(this.eventType));
    }

    @JsonIgnore
    public String getBeanEventHandlerName() {
        return String.format("%sEventHandler", StringUtils.delete(this.getEventType(), "Event"));
    }

    protected String toCamelCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}