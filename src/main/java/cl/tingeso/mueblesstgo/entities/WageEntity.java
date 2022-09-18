package cl.tingeso.mueblesstgo.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "wage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private LocalDate date;

    @OneToMany(mappedBy = "wage", fetch = FetchType.LAZY)
    private List<WageDetailEntity> detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
