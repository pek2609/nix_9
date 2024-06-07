package ua.com.alevel.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "route", uniqueConstraints = {@UniqueConstraint(columnNames = {"departure_town_id", "arrival_town_id"})})
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Data
public class RouteV2 extends BaseEntity {

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_town_id", nullable = false)
    private Town departureTown;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_town_id", nullable = false)
    private Town arrivalTown;

    @Column(name = "image_path")
    private String imagePath;
}
