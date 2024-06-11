package ua.com.alevel.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.type.TownAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "town")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Data
public class Town extends BaseEntity {

    public Town(Long id) {
        this.setId(id);
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alias", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TownAlias alias;
}
