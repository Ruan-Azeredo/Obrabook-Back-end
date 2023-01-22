package com.projetopoo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "toolActions")
public class ToolAction {
    @Transient
    public static final String SEQUENCE_NAME = "toolAction_sequence";

    @Id
    private long id;

    @NonNull
    private boolean isReport;
    @NonNull
    private String data;
    @NonNull
    private String description;

    @NonNull
    private long toolID;
    @NonNull
    private long engineerID;
    @NonNull
    private long constructionID;

    public boolean getIsReport() { return isReport; }
}
