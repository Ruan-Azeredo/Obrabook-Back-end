package com.projetopoo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "engineers")
public class Engineer {
    @Transient
    public static final String SEQUENCE_NAME = "engineer_sequence";

    @Id
    private long id;
    @NonNull
    private String name;
    @Indexed(unique = true)
    @NonNull
    private String cnpj;
    @NonNull
    private String phoneNo;

    @NonNull
    private long userID;
}
