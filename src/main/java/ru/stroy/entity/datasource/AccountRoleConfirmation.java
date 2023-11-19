package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "account_role_confirmation")
@RequiredArgsConstructor
@Getter
@Setter
public class AccountRoleConfirmation extends TimeManagedEntity {

    @Column(name = "signature")
    @JsonIgnore
    private String signature;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "confirm_document", referencedColumnName = "id", nullable = false)
    private Document confirmDocument;
}
