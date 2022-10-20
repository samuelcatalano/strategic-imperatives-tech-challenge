package co.uk.imperatives.exercise.entity;

import co.uk.imperatives.exercise.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "billing")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Billing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "provider", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Provider provider;

    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Location location;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}